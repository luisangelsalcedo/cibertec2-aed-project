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
import eparking.controllers.AuthController;
import eparking.enums.ReservationStatus;
import eparking.models.Reservation;
import eparking.models.User;

public class ReservationDAO_txt implements IReservationDAO {
	
    private final String filePath =  "data/reservaciones.txt";

	@Override
	public void insertReservation(Reservation reservation) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			reservation.setId(generarNuevoId());
			

			if(!hasOtherPenddingReservation(AuthController.getLoggedUser())) {		
				writer.write(reservationToLine(reservation));
				writer.newLine();
			} else System.out.println("Ya tienes una reservacion pendiente.");
			
	    } catch (IOException e) {
	        System.out.println("Error guardando la reservacion: " + e.getMessage());
	    }
	}

	@Override
	public void updateReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Reservation> getAllReservations() {
		List<Reservation> reservationList = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        	String txtOneLine = br.readLine(); // headers
        	while ((txtOneLine = br.readLine()) != null) {
                String[] fields = txtOneLine.split(",");
                
            	reservationList.add(lineToReservation(fields));               			
			}
		} catch (Exception e) {
			System.out.println("Error buscando la reservacion por id: " + e.getMessage());
		}
		return reservationList;
	}
	
	@Override
	public List<Reservation> getAllReservationsByUser(User user) {
		List<Reservation> reservationList = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        	String txtOneLine = br.readLine(); // headers
        	while ((txtOneLine = br.readLine()) != null) {
                String[] fields = txtOneLine.split(",");
                if(fields[1].equals(String.valueOf(user.getId()))){
                	reservationList.add(lineToReservation(fields));
                }				
			}
		} catch (Exception e) {
			System.out.println("Error buscando la reservacion por id: " + e.getMessage());
		}
		return reservationList;
	}

	@Override
	public List<Reservation> getAllReservationsByDate(LocalDate date) {
		List<Reservation> reservationList = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        	String txtOneLine = br.readLine(); // headers
        	while ((txtOneLine = br.readLine()) != null) {
                String[] fields = txtOneLine.split(",");
                if(fields[4].equals(String.valueOf(date))){
                	reservationList.add(lineToReservation(fields));
                }				
			}
		} catch (Exception e) {
			System.out.println("Error buscando la reservacion por id: " + e.getMessage());
		}
		return reservationList;
	}

	@Override
	public Reservation getReservationsById(int id) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        	String txtOneLine = br.readLine(); // headers
        	while ((txtOneLine = br.readLine()) != null) {
                String[] fields = txtOneLine.split(",");
                if(fields[0].equals(String.valueOf(id))){
                    return lineToReservation(fields);
                }				
			}
		} catch (Exception e) {
			System.out.println("Error buscando la reservacion por id: " + e.getMessage());
		}
		return null;
	}

	private int generarNuevoId() {
	     List<Reservation> reservations = getAllReservations();
	     if(!reservations.isEmpty()) {
	     	return reservations.get(reservations.size()-1).getId() + 1;
	     }
	    return 1;
	}

    private String reservationToLine(Reservation reservation){
        return String.join(",",
            String.valueOf(reservation.getId()),
            String.valueOf(reservation.getUserId()),
            String.valueOf(reservation.getParkingId()),
            reservation.getStatus().toString(),
            String.valueOf(reservation.getCreationDate()),
            String.valueOf(reservation.getStartTime()),
            String.valueOf(reservation.getEndTime())
        );
    }
    
    private Reservation lineToReservation(String[] split) {
    	Reservation reservation = new Reservation();
    	reservation.setId(Integer.parseInt(split[0]));
    	reservation.setUserId(Integer.parseInt(split[1]));
    	reservation.setParkingId(Integer.parseInt(split[2]));
    	reservation.setStatus(ReservationStatus.fromTo(split[3]));
    	reservation.setCreationDate(LocalDate.parse(split[4]));
    	reservation.setStartTime(!"null".equals(split[5]) ? LocalTime.parse(split[5]): null);
    	reservation.setEndTime(!"null".equals(split[6]) ? LocalTime.parse(split[6]): null);
		return reservation;
	}
    
    private boolean hasOtherPenddingReservation(User user) {
    	for(Reservation reservation : getAllReservationsByUser(user)) {
    		if(reservation.getStatus().equals(ReservationStatus.PENDING)) return true;
    	}
    	return false;
    }
}
