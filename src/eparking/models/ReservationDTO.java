package eparking.models;

import java.time.LocalDate;
import java.time.LocalTime;
import eparking.enums.ReservationStatus;

public class ReservationDTO {
    private int id;
    private User user;
    private Parking parking;
    private ReservationStatus status;
    private LocalDate creationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    
    public ReservationDTO(Reservation reservation, User user, Parking parking){
        setId(reservation.getId());       
        setStatus(reservation.getStatus());
        setCreationDate(reservation.getCreationDate());
        setStartTime(reservation.getStartTime());
        setEndTime(reservation.getEndTime());
        setUser(user);
        setParking(parking);
    }
    
    public ReservationDTO() {}

    // getters
	public int getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public Parking getParking() {
		return parking;
	}
	public ReservationStatus getStatus() {
		return status;
	}
	public LocalDate getCreationDate() {
		return creationDate;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}

	// setters
	public void setId(int id) {
		this.id = id;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setParking(Parking parking) {
		this.parking = parking;
	}
	public void setStatus(ReservationStatus status) {
		this.status = status;
	}
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
}