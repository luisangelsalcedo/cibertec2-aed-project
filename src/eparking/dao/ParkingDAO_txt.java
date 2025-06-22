package eparking.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import eparking.enums.ParkingStatus;
import eparking.interfaces.IParkingDAO;
import eparking.models.Parking;

public class ParkingDAO_txt implements IParkingDAO{
    
    private final String filePath =  "data/estacionamientos.txt";
	private final String headers = "Id,Label,Status";
	private List<Parking> parkingList;

	public ParkingDAO_txt(){
		parkingList = new ArrayList<>();
		loadDataFromFile();
	}

	@Override
	public List<Parking> getAllParkings() {
		return new ArrayList<>(parkingList);
	}

	@Override
	public Parking findParkingById(int id) {
		return parkingList.stream()
        		.filter(parking -> parking.getId() == id)
        		.findFirst()
        		.orElse(null);
	}

	@Override
	public void insertParking(Parking parking) {
		parking.setId(generateNewId());
		
        parkingList.add(parking);
		writeDataToFile();
	}

	@Override
	public void updateParking(Parking parking) {
		parkingList.replaceAll(current -> current.getId() == parking.getId() ? parking : current);
	}	

    private int generateNewId() {	
	    return parkingList.stream().mapToInt(Parking::getId).max().orElse(0) + 1;
	}

	private void loadDataFromFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        String txtOneLine = br.readLine(); 
	        while ((txtOneLine = br.readLine()) != null) {
	            String[] fields = txtOneLine.split(",");
	            
	            Parking parking = new Parking(fields[1]);
				parking.setId(Integer.parseInt(fields[0]));
				parking.setStatus(ParkingStatus.fromTo(fields[2]));
	            
	            parkingList.add(parking);
	        }
	    } catch (IOException e) {
	        System.out.println("Error leyendo archivo: " + e.getMessage());
	    }		
	}

	private void writeDataToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(headers);
			for(Parking parking:parkingList) {
				writer.newLine();
				writer.write(String.join(",", 
				String.valueOf(parking.getId()),
				parking.getLabel(),
				parking.getStatus().toString()
				));
			}			
	    } catch (IOException e) {
	        System.out.println("Error guardando usuario: " + e.getMessage());
	    }	
	}
}
