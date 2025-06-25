package eparking.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import eparking.dao.ParkingDAO_txt;
import eparking.dao.ReservationDAO_txt;
import eparking.dao.UserDAO_txt;
import eparking.enums.ParkingStatus;
import eparking.enums.ReservationStatus;
import eparking.interfaces.IParkingDAO;
import eparking.interfaces.IReservationDAO;
import eparking.interfaces.IUserDAO;
import eparking.models.Parking;
import eparking.models.Reservation;
import eparking.models.ReservationDTO;
import eparking.models.User;

public class ReservationController {
	private IUserDAO userDao = new UserDAO_txt();
	private IParkingDAO parkingDao = new ParkingDAO_txt();
	private IReservationDAO reservationDao = new ReservationDAO_txt();
	
	public List<Parking> getParkingListWithReservations(LocalDate currentDate){
		
		List<Reservation> reservations = new ArrayList<>(reservationDao.getAllReservationsByDate(currentDate));
		List<Parking> allParkings = new ArrayList<>(parkingDao.getAllParkings());
		List<Parking> parkingsWithStatus = new ArrayList<>();
		
		for(Parking parking : allParkings) {
			Parking parkingCopy = new Parking(parking);
			
			boolean hasActiveReservation = 
					 reservations.stream()
					 	.anyMatch(reservation -> 
			                reservation.getParkingId() == parkingCopy.getId() &&
			                !reservation.getStatus().equals(ReservationStatus.COMPLETE) &&
			                !reservation.getStatus().equals(ReservationStatus.CANCELED)
			            );
			 
			parkingCopy.setStatus(hasActiveReservation ? ParkingStatus.BUSY : ParkingStatus.AVAILABLE);
			parkingsWithStatus.add(parkingCopy);
		}
		return parkingsWithStatus;
	} 
	
	public void registerReservation(Reservation reservation) {
		if(hasActiveReservationOnSameDay(reservation)) {	
			throw new IllegalArgumentException(
				"No puedes registrar más de una reserva por día.\n" +  
				"Ya tienes una reserva activa para esta fecha.");
		} 
		reservationDao.insertReservation(reservation);
	}
	
	public void updateReservation(Reservation reservation) {
		reservationDao.updateReservation(reservation);		
	}

	public int getTodayInProgressReservationsCount(){
		return countTodayReservationsByStatus(ReservationStatus.INPROGRESS);
	}

	public int getTodayCurrentReservationsCount(){
		return reservationDao.getAllReservationsByDate(LocalDate.now()).size();
	}

	public int getTodayAvailableParkingCount(){
		return parkingDao.getAllParkings().size() - reservationDao.getAllReservationsByDate(LocalDate.now()).size();
	}
	
	public List<Reservation> getActiveReservations() {
		return reservationDao.getAllReservationsByUser(AuthController.getLoggedUser().getId()).stream()
			.filter(this::isActive)
			.collect(Collectors.toList());
			
	}
	
	public Reservation getActiveReservation() {
		return getActiveReservations().stream()
			.filter(this::isntOldReservations)
			.sorted(Comparator.comparing(Reservation::getCreationDate)) 
			.findFirst()
			.orElse(null);
	}
	
	public ReservationDTO getReservationDetails(Reservation reservation) {
		User reservationUser = userDao.findUserById(reservation.getUserId());
		Parking reservationParking = parkingDao.findParkingById(reservation.getParkingId());
		
		return new ReservationDTO(reservation, reservationUser, reservationParking);
	}
	
	private boolean hasActiveReservationOnSameDay(Reservation reservation) {
		return reservationDao.getAllReservationsByUser(reservation.getUserId()).stream()
	            .anyMatch(current -> isActive(current) && current.getCreationDate().equals(reservation.getCreationDate()));
    }
	
	private boolean isActive(Reservation reservation) {
		return reservation.getStatus().equals(ReservationStatus.PENDING) || 
			   reservation.getStatus().equals(ReservationStatus.INPROGRESS);
	}
	
	private int countTodayReservationsByStatus(ReservationStatus status) {
        return reservationDao.getAllReservationsByDate(LocalDate.now()).stream()
            .filter(reservation -> reservation.getStatus() == status)
            .collect(Collectors.toList()).size();
    }

	private boolean isntOldReservations(Reservation reservation){
		if(!reservation.getCreationDate().isBefore(LocalDate.now())) return true;
		return false;
	}

	public void markOldReservarionsAsCanceled() {
		System.out.println("Ejecutar limpieza");
		for(Reservation activeReservation:getActiveReservations()) {
			if(!isntOldReservations(activeReservation)) {
				System.out.println("Se cancelo tu reserva del " + activeReservation.getCreationDate());
				activeReservation.setStatus(ReservationStatus.CANCELED);
				activeReservation.setEndTime(LocalTime.MAX);
				reservationDao.updateReservation(activeReservation);
			}
		}
	}
}
