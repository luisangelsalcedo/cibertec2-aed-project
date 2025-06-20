package eparking.dao;

import java.util.List;

import eparking.models.Parking;

public interface IParkingDAO {
	void insertParking(Parking parking);
	
	List<Parking> getAllParkings();
	

	void updateParking(Parking parking);
	

	Parking findParkingByLabel(String label);
}
