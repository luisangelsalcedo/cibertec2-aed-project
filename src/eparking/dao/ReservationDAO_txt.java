package eparking.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import eparking.controllers.AuthController;
import eparking.enums.ReservationStatus;
import eparking.interfaces.IReservationDAO;
import eparking.models.Reservation;
import eparking.models.User;

public class ReservationDAO_txt implements IReservationDAO {
	
    private final String filePath = "data/reservaciones.txt";
	private final String headers = "Id,UserId,ParkingId,Status,CreationDate,StartTime,EndTime";
	private List<Reservation> reservationList;

	public ReservationDAO_txt(){
		reservationList = new ArrayList<>();
		loadDataFromFile();	
	}

	@Override
	public List<Reservation> getAllReservations() {
		return new ArrayList<>(reservationList);
	}

	@Override
	public List<Reservation> getAllReservationsByUser(int userID) {
		return reservationList.stream()
				.filter(reservation -> reservation.getUserId() == userID)
				.collect(Collectors.toList());
	}

	@Override
	public List<Reservation> getAllReservationsByDate(LocalDate date) {
		return reservationList.stream()
				.filter(reservation -> reservation.getCreationDate().equals(date))
				.collect(Collectors.toList());	
	}

	@Override
	public Reservation getReservationsById(int id) {
        return reservationList.stream()
        		.filter(reservation -> reservation.getId() == id)
        		.findFirst()
        		.orElse(null);
	}

	@Override
	public void insertReservation(Reservation reservation) {
		reservation.setId(generateNewId());				
		reservationList.add(reservation);
		writeDataToFile();
	}

	@Override
	public void updateReservation(Reservation reservation) {
		reservationList.replaceAll(current -> current.getId() == reservation.getId() ? reservation : current);
	}

	private int generateNewId() {	
	    return reservationList.stream().mapToInt(Reservation::getId).max().orElse(0) + 1;
	}

	private void loadDataFromFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        String txtOneLine = br.readLine(); 
	        while ((txtOneLine = br.readLine()) != null) {
	            String[] fields = txtOneLine.split(",");
	            
	            Reservation reservation = new Reservation();
				reservation.setId(Integer.parseInt(fields[0]));
				reservation.setUserId(Integer.parseInt(fields[1]));
				reservation.setParkingId(Integer.parseInt(fields[2]));
				reservation.setStatus(ReservationStatus.fromTo(fields[3]));
				reservation.setCreationDate(LocalDate.parse(fields[4]));
				reservation.setStartTime(!"null".equals(fields[5]) ? LocalTime.parse(fields[5]): null);
				reservation.setEndTime(!"null".equals(fields[6]) ? LocalTime.parse(fields[6]): null);
	            
	            reservationList.add(reservation);
	        }
	    } catch (IOException e) {
	        System.out.println("Error leyendo archivo: " + e.getMessage());
	    }		
	}

	private void writeDataToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(headers);
			for(Reservation reservation:reservationList) {
				writer.newLine();
				writer.write(String.join(",",
				String.valueOf(reservation.getId()),
				String.valueOf(reservation.getUserId()),
				String.valueOf(reservation.getParkingId()),
				reservation.getStatus().toString(),
				String.valueOf(reservation.getCreationDate()),
				String.valueOf(reservation.getStartTime()),
				String.valueOf(reservation.getEndTime())
				));
			}			
	    } catch (IOException e) {
	        System.out.println("Error guardando usuario: " + e.getMessage());
	    }	
	}

	
}
