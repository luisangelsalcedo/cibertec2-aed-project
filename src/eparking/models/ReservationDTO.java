package eparking.models;
public class ReservationDTO extends Reservation{

    private User user;
    private Parking parking;
    
    public ReservationDTO(Reservation reservation, User user, Parking parking){
        super(reservation);
        setUser(user);
        setParking(parking);
    }
    
    public ReservationDTO() {}

    // getters
	public User getUser() {
		return user;
	}
	public Parking getParking() {
		return parking;
	}
	
	// setters	
	public void setUser(User user) {
		this.user = user;
	}
	public void setParking(Parking parking) {
		this.parking = parking;
	}
}