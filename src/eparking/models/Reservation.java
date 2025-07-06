package eparking.models;

import java.time.Duration;
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
    
    public Reservation(int parkingId, LocalDate creationDate){
        this.setUserId(AuthController.getLoggedUser().getId()); // logged user
        this.setParkingId(parkingId);
        this.setCreationDate(creationDate);
        status = ReservationStatus.PENDING;
    }
    
    public Reservation(int parkingId) {
    	this(parkingId, LocalDate.now());
    }
    
    public Reservation(Reservation reservation) {
    	this(reservation.getParkingId(), reservation.getCreationDate());
    	setId(reservation.getId());
    	setUserId(reservation.getUserId());
    	setStatus(reservation.getStatus());
    	setStartTime(reservation.getStartTime());
    	setEndTime(reservation.getEndTime());
    }
    
    public Reservation() {}

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
	
	//methods
	public String getElapsedTime() {
	    if (startTime == null || endTime == null || endTime.isBefore(startTime)) {
	        return "No disponible";
	    }

	    Duration duration = Duration.between(startTime, endTime);
	    long hours = duration.toHours();
	    long minutes = duration.toMinutesPart();

	    return String.format("%d hora%s %d minuto%s",
	            hours, hours != 1 ? "s" : "",
	            minutes, minutes != 1 ? "s" : "");
	}
	

}