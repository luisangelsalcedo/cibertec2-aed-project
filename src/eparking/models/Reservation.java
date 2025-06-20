package eparking.models;

import java.time.LocalDate;
import java.time.LocalTime;
import eparking.controllers.AuthController;
import eparking.enums.ReservationStatus;

public class Reservation {
    private int id;
    private int userId;
    private int parkingId;
    private ReservationStatus status;
    private LocalDate creationDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public Reservation() {}
    
    public Reservation(int parkingId){
        this.setUserId(1); // logged user
        this.setParkingId(parkingId);
        status = ReservationStatus.PENDING;
        creationDate = LocalDate.now();
    }

    // getters
	public int getId() {
		return id;
	}
	public int getUserId() {
		return userId;
	}
	public int getParkingId() {
		return parkingId;
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
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setParkingId(int parkingId) {
		this.parkingId = parkingId;
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
