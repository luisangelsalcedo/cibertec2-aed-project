package eparking.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import eparking.enums.ParkingStatus;
import eparking.models.Parking;

public class ParkingDAO_txt implements IParkingDAO{
    
    private final String filePath =  "data/estacionamientos.txt";

	@Override
	public void insertParking(Parking parking) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            parking.setId(generarNuevoId());
			writer.write(parkingToLine(parking));
			writer.newLine();			
		} catch (Exception e) {
			System.out.println("Error guardando estacionamiento: " + e.getMessage());
		}		
	}

	@Override
	public void updateParking(Parking parking) {
		try {
	        Path path = Paths.get(filePath);
	        List<String> originalLines = Files.readAllLines(path);
	        boolean wasFound = false;

	        if (originalLines.isEmpty()) return;

	        List<String> newLines = new ArrayList<>();
	        newLines.add(originalLines.get(0)); // add headers

	        for (int i = 1; i < originalLines.size(); i++) {
	            String txtOneLine = originalLines.get(i);
	            String[] fields = txtOneLine.split(",");

	            if (Integer.parseInt(fields[0]) == parking.getId()) {
	                newLines.add(parkingToLine(parking)); //add new line
	                wasFound = true;
	            } else {
	                newLines.add(txtOneLine); // add same line
	            }
	        }

	        if (!wasFound) {
	            System.out.println("No se encontró el estacionamiento con ID: " + parking.getId());
	        }
	        Files.write(path, newLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

	    } catch (IOException e) {
	        System.out.println("Error al actualizar estacionamiento: " + e.getMessage());
	    }
		
	}

	@Override
	public List<Parking> getAllParkings() {
		List<Parking> parkingList = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        	String txtOneLine = br.readLine(); // headers
        	while ((txtOneLine = br.readLine()) != null) {
                String[] fields = txtOneLine.split(",");
                
            	parkingList.add(lineToParking(fields));               			
			}
		} catch (Exception e) {
			System.out.println("Error buscando la reservacion por id: " + e.getMessage());
		}
		return parkingList;
	}

	@Override
	public Parking findParkingById(int id) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        String txtOneLine = br.readLine(); // skip headers
	        while ((txtOneLine = br.readLine()) != null) {
	            String[] fields = txtOneLine.split(",");
	            if (fields[0].equals(String.valueOf(id))) {
	                return lineToParking(fields);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Error buscando usuario por nombre de usuario: " + e.getMessage());
	    }
	    return null;
	}

    private int generarNuevoId() {
	     List<Parking> parkings = getAllParkings();
	     if(!parkings.isEmpty()) {
	     	return parkings.get(parkings.size()-1).getId() + 1;
	     }
	    return 1;
	}

    private String parkingToLine(Parking parking) {
		return String.join(",", 
            String.valueOf(parking.getId()),
            parking.getLabel(),
            parking.getStatus().toString()
		);
	}

    private Parking lineToParking(String[] split) {
        Parking parking = new Parking(split[1]);
        parking.setId(Integer.parseInt(split[0]));
        parking.setStatus(ParkingStatus.fromTo(split[2]));
        return parking;
    } 
}
