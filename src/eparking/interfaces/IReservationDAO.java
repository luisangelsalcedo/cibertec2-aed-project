package eparking.interfaces;

import java.time.LocalDate;
import java.util.List;

import eparking.models.Reservation;
import eparking.models.User;

public interface IReservationDAO {
	void insertReservation(Reservation reservation);
	void updateReservation(Reservation reservation);
	List<Reservation> getAllReservations();
	List<Reservation> getAllReservationsByUser(int userID);
	List<Reservation> getAllReservationsByDate(LocalDate date);
	Reservation getReservationsById(int id);
}
