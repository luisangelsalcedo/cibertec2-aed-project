package eparking.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import eparking.controllers.AuthController;
import eparking.controllers.ReservationController;
import eparking.enums.AlertType;
import eparking.models.ReservationDTO;
import eparking.utils.ThemeStyles;
import eparking.views.components.CustomAlert;
import eparking.views.components.CustomButton;
import eparking.views.components.MainDialog;
import eparking.views.components.ReservationItem;

public class MyReservationsView extends JPanel {

	private static final long serialVersionUID = 1L;
	ReservationController controller;
	
    public MyReservationsView() {    	
    	controller = new ReservationController();
    	List<ReservationDTO> myReservations = controller.getAllReservationsDTOByUser(AuthController.getLoggedUser().getId());
    	setOpaque(false);
    	
    	if (myReservations.isEmpty()) {
			new CustomAlert(
				"No tienes reservaciones pendientes", 
				AlertType.NOTICE
			);			
			add(new EmptyReservationView());
			
		} else {
			JPanel contentPanel = new JPanel();
			contentPanel.setLayout(new GridLayout(myReservations.size()+1, 1, 5, 5));
			contentPanel.setOpaque(false);
			contentPanel.add(new ReservationItem(new String[] {"FECHA DE RESERVA", "ESTACIONAMIENTO", "INGRESO", "SALIDA", "DURACION", "ESTADO"}, true));
	    	for(ReservationDTO reservation : myReservations) {
	    		
	    		contentPanel.add(new ReservationItem(new String[] {
	    				reservation.getCreationDate().toString(), 
	    				reservation.getParking().getLabel(), 
	    				reservation.getStartTime() == null ? "Sin iniciar" : reservation.getStartTime().format(ThemeStyles.dateFormat), //
	    				reservation.getEndTime() == null ? "Sin iniciar" : reservation.getEndTime().format(ThemeStyles.dateFormat), 
	    				reservation.getElapsedTime(), 
	    				reservation.getStatus().toString()
	    			}));
	    	}
	    	
	    	
	    	setLayout(new BorderLayout(10,10));
	    	add(contentPanel, BorderLayout.CENTER);
		}

	}
    
}