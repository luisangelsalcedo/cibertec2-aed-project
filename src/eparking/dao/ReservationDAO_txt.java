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
import eparking.enums.ReservationStatus;
import eparking.interfaces.IReservationDAO;
import eparking.models.Reservation;

public class ReservationDAO_txt extends AbstractDAO_txt implements IReservationDAO {
	
	private final String headers = "Id,UserId,ParkingId,Status,CreationDate,StartTime,EndTime";
	private List<Reservation> reservationList;

	public ReservationDAO_txt(){
		super("reservaciones.txt");
		reservationList = new ArrayList<>();
		loadDataFromFile();	
	}

	@Override
	public List<Reservation> getAllReservations() {
		return new ArrayList<>(reservationList);
	}
	public List<Reservation> getAllReservations(LocalDate date) {
		return reservationList.stream()
				.filter(reservation -> reservation.getCreationDate().equals(date))
				.collect(Collectors.toList());	
	}
	
	@Override
	public List<Reservation> getAllReservationsByUser(int userID) {
		return reservationList.stream()
				.filter(reservation -> reservation.getUserId() == userID)
				.collect(Collectors.toList());
	}	
	public List<Reservation> getAllReservationsByUser(int userID, LocalDate date) {
		return reservationList.stream()
				.filter(reservation -> reservation.getUserId() == userID)
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
		writeDataToFile();
	}

	private int generateNewId() {	
	    return reservationList.stream().mapToInt(Reservation::getId).max().orElse(0) + 1;
	}

	@Override
	public void loadDataFromFile() {
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
				if(!"null".equals(fields[5])) reservation.setStartTime(LocalTime.parse(fields[5]));
				if(!"null".equals(fields[6])) reservation.setEndTime(LocalTime.parse(fields[6]));
	            
	            reservationList.add(reservation);
	        }
	    } catch (IOException e) {
	        System.out.println("Error leyendo archivo: " + e.getMessage());
	    }		
	}
	
	@Override
	public void writeDataToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(headers);
			for(Reservation reservation : reservationList) {
				writer.newLine();
				writer.write(reservation.toString());
			}			
	    } catch (IOException e) {
	        System.out.println("Error guardando usuario: " + e.getMessage());
	    }	
	}
}
