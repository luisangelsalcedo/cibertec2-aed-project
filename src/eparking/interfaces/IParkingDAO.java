package eparking.interfaces;

import java.util.List;
import eparking.models.Parking;

public interface IParkingDAO {
	void insertParking(Parking parking);	
	void updateParking(Parking parking);
	List<Parking> getAllParkings();
	Parking findParkingById(int id);
}