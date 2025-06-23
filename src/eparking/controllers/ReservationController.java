package eparking.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import eparking.dao.ParkingDAO_txt;
import eparking.dao.ReservationDAO_txt;
import eparking.enums.ParkingStatus;
import eparking.enums.ReservationStatus;
import eparking.interfaces.IParkingDAO;
import eparking.interfaces.IReservationDAO;
import eparking.models.Parking;
import eparking.models.Reservation;

public class ReservationController {
	private IParkingDAO parkingDao = new ParkingDAO_txt();
	private IReservationDAO reservationDao = new ReservationDAO_txt();
	
	public List<Parking> getParkingListWithReservations(LocalDate current){
		List<Reservation> reservationList = new ArrayList<>(reservationDao.getAllReservationsByDate(current));
		List<Parking> parkingList = new ArrayList<>(parkingDao.getAllParkings());
		List<Parking> parkingListWithReservations = new ArrayList<>();
		
		for(Parking parking:parkingList) {
			Parking cloneParking = new Parking(parking);
			
			for(Reservation reservation:reservationList) {
				if(cloneParking.getId() == reservation.getParkingId()) {
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
	
	private boolean hasOtherPenddingReservation(Reservation reservation) {
    	for(Reservation current : reservationDao.getAllReservationsByUser(reservation.getUserId())) {
    		if(current.getStatus().equals(ReservationStatus.PENDING) &&
    		current.getCreationDate().equals(reservation.getCreationDate())) return true;
    	}
    	return false;
    }
}
