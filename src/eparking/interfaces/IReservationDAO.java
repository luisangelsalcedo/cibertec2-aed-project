package eparking.interfaces;

import java.time.LocalDate;
import java.util.List;
import eparking.models.Reservation;

public interface IReservationDAO {
	void insertReservation(Reservation reservation);
	void updateReservation(Reservation reservation);
	List<Reservation> getAllReservations();
	List<Reservation> getAllReservations(LocalDate date);
	List<Reservation> getAllReservationsByUser(int userID);
	List<Reservation> getAllReservationsByUser(int userID, LocalDate date);
	Reservation getReservationsById(int id);
}