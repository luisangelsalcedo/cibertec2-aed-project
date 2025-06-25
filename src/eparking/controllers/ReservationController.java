package eparking.controllers;

import java.time.LocalDate;
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
		
		List<Reservation> reservationList = new ArrayList<>(reservationDao.getAllReservationsByDate(currentDate));
		List<Parking> parkingList = new ArrayList<>(parkingDao.getAllParkings());
		List<Parking> parkingListWithReservations = new ArrayList<>();
		
		for(Parking parking:parkingList) {
			Parking cloneParking = new Parking(parking);
			
			for(Reservation reservation:reservationList) {
				if(cloneParking.getId() == reservation.getParkingId() && !reservation.getStatus().equals(ReservationStatus.COMPLETE)) {
					cloneParking.setStatus(ParkingStatus.BUSY);
				}
			}
			parkingListWithReservations.add(cloneParking);
		}
		return parkingListWithReservations;
	} 
	
	public void registerReservation(Reservation reservation) {
		if(!hasOtherPenddingReservation(reservation)) {	
			reservationDao.insertReservation(reservation);
		} else throw new IllegalArgumentException("No puedes registrar más de una reserva por día.\n" 
												+ "Ya tienes una reserva activa para esta fecha.");
	}
	
	public void updateReservation(Reservation reservation) {
		reservationDao.updateReservation(reservation);		
	}

	public int getTodayInProgressReservationsCount(){
		return reservationDao.getAllReservationsByDate(LocalDate.now()).stream()
				.filter(reservation -> reservation.getStatus().equals(ReservationStatus.INPROGRESS))
				.collect(Collectors.toList()).size();
	}

	public int getTodayCurrentReservationsCount(){
		return reservationDao.getAllReservationsByDate(LocalDate.now()).size();
	}

	public int getTodayAvailableParkingCount(){
		return parkingDao.getAllParkings().size() - reservationDao.getAllReservationsByDate(LocalDate.now()).size();
	}
	
	public Reservation getCurrentReservation() {
		List<Reservation> reservations = reservationDao.getAllReservationsByUser(AuthController.getLoggedUser().getId());
		return reservations.stream().filter(current -> isActive(current))
			.sorted(Comparator.comparing(Reservation::getCreationDate)) 
			.findFirst()
			.orElse(null);
	}
	
	public ReservationDTO getReservationDetails(Reservation reservation) {
		User reservationUser = userDao.findUserById(reservation.getUserId());
		Parking reservationParking = parkingDao.findParkingById(reservation.getParkingId());
		
		return new ReservationDTO(reservation, reservationUser, reservationParking);
	}
	
	private boolean hasOtherPenddingReservation(Reservation reservation) {
    	for(Reservation current : reservationDao.getAllReservationsByUser(reservation.getUserId())) {
    		if(isActive(current) &&	hasAnotherOnSameDate(current, reservation)) return true;
    	}
    	return false;
    }
	
	private boolean isActive(Reservation reservation) {
		// PENDING OR INPROGRESS
		return (reservation.getStatus().equals(ReservationStatus.PENDING) || reservation.getStatus().equals(ReservationStatus.INPROGRESS));
	}
	private boolean hasAnotherOnSameDate(Reservation current, Reservation compare) {
		return current.getCreationDate().equals(compare.getCreationDate());
	}
}
