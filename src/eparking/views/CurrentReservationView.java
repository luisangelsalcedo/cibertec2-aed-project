package eparking.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalTime;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eparking.controllers.AuthController;
import eparking.controllers.ReservationController;
import eparking.enums.AlertType;
import eparking.enums.ReservationStatus;
import eparking.models.Reservation;
import eparking.models.ReservationDTO;
import eparking.utils.EventSystem;
import eparking.utils.ThemeStyles;
import eparking.views.components.CustomAlert;
import eparking.views.components.CustomButton;
import eparking.views.components.CustomInput;
import eparking.views.components.MainDialog;

public class CurrentReservationView extends JPanel {

	private static final long serialVersionUID = 1L;
	private CustomInput txtDate;
	private CustomInput txtParking;
	private CustomInput txtStartTime;
	private CustomInput txtEndTime;
	private JPanel startTimePanel;
	private JPanel endTimePanel;
	private CustomButton setStartTimeBtn;
	private CustomButton setEndTimeBtn;	
	private CustomButton cancelBtn;
	private JPanel bottomPanel;
	private ReservationController controller;
	private Reservation currentReservation;
	private ReservationDTO currentReservationDTO;

	public CurrentReservationView() {	
		
		controller = new ReservationController();		
		currentReservation = controller.getActiveReservation(AuthController.getLoggedUser().getId());
		setOpaque(false);		
		
		if (currentReservation == null) {
			new CustomAlert(
					"No tienes reservaciones pendientes", 
					AlertType.NOTICE
				);
			add(new EmptyReservationView());
			
		} else {
			setLayout(new GridLayout(3, 1, 10, 10));
			currentReservationDTO = controller.getReservationDetails(currentReservation);
			
			txtDate = new CustomInput(10, false);
			txtDate.setText(currentReservation.getCreationDate().toString());
			txtParking = new CustomInput(8, false);
			txtParking.setText(currentReservationDTO.getParking().getLabel());
			
			txtStartTime = new CustomInput(8, false);
			txtEndTime = new CustomInput(8, false);
			
			JPanel topLeftPanel = new JPanel();
			topLeftPanel.setOpaque(false);
			topLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			topLeftPanel.add(new JLabel("Fecha: "));
			topLeftPanel.add(txtDate);
			
			JPanel topRightPanel = new JPanel();
			topRightPanel.setOpaque(false);
			topRightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			topRightPanel.add(new JLabel("Lugar: "));
			topRightPanel.add(txtParking);
			
			JPanel formPanel = new JPanel();
			formPanel.setOpaque(false);
			formPanel.setLayout(new BorderLayout(10, 10));
			formPanel.add(topLeftPanel, BorderLayout.CENTER);
			formPanel.add(topRightPanel, BorderLayout.EAST);
			
			cancelBtn = new CustomButton("Cancel");
			cancelBtn.addActionListener(e -> cancelCurrentRegister());
			
			setStartTimeBtn = new CustomButton("Registrar Ingreso");
			setStartTimeBtn.setFont(ThemeStyles.lgFont);
			setStartTimeBtn.addActionListener(e -> startTimeRegister());
			
			setEndTimeBtn = new CustomButton("Registrar Salida");
			setEndTimeBtn.setFont(ThemeStyles.lgFont);
			setEndTimeBtn.addActionListener(e -> endTimeRegister());
			
			startTimePanel = new JPanel();
			startTimePanel.setOpaque(false);
			startTimePanel.add(new JLabel("Ingreso: "));
			startTimePanel.add(txtStartTime);
			startTimePanel.setVisible(false);
			
			endTimePanel = new JPanel();
			endTimePanel.setOpaque(false);
			endTimePanel.add(new JLabel("Salida: "));
			endTimePanel.add(txtEndTime);
			endTimePanel.setVisible(false);
			
			JPanel topPanel = new JPanel();
			topPanel.setOpaque(false);
			topPanel.setLayout(new BorderLayout(10, 10));
			topPanel.add(formPanel, BorderLayout.CENTER);
			topPanel.add(cancelBtn, BorderLayout.EAST);
			
			JPanel centerPanel = new JPanel();
			centerPanel.setOpaque(false);
			centerPanel.setLayout(new BorderLayout(10, 10));
			centerPanel.add(setStartTimeBtn, BorderLayout.CENTER);
			centerPanel.add(startTimePanel, BorderLayout.EAST);
			
			bottomPanel = new JPanel();
			bottomPanel.setOpaque(false);
			bottomPanel.setLayout(new BorderLayout(10, 10));
			bottomPanel.add(setEndTimeBtn, BorderLayout.CENTER);
			bottomPanel.add(endTimePanel, BorderLayout.EAST);
			bottomPanel.setVisible(false);

			add(topPanel);
			add(centerPanel);
			add(bottomPanel);
			
			if(currentReservation.getStartTime() != null) {
				showStartTime(currentReservation.getStartTime().toString());
			}
			if(currentReservation.getEndTime() != null) {
				showEndTime(currentReservation.getEndTime().toString());
			}
		}
	}
	
	private void showStartTime(String startTime) {
		setStartTimeBtn.setVisible(false);
		cancelBtn.setVisible(false);
		startTimePanel.setVisible(true);
		bottomPanel.setVisible(true);
		txtStartTime.setText(startTime);
	}
	
	private void showEndTime(String endTime) {
		setEndTimeBtn.setVisible(false);
		endTimePanel.setVisible(true);
		txtEndTime.setText(endTime);
	}

	private void startTimeRegister() {
		String startTime = LocalTime.now().format(ThemeStyles.dateFormat);
		showStartTime(startTime);
		
		// set
		currentReservation.setStartTime(LocalTime.parse(startTime));
		currentReservation.setStatus(ReservationStatus.INPROGRESS);
		controller.updateReservation(currentReservation);
		// Disparar evento global
        EventSystem.triggerDashboardRefresh();
        
		MainDialog.getInstance().pack();
	}
	
	private void endTimeRegister() {
		String endTime = LocalTime.now().format(ThemeStyles.dateFormat);
		showEndTime(endTime);
		
		//set
		currentReservation.setEndTime(LocalTime.parse(endTime));
		currentReservation.setStatus(ReservationStatus.COMPLETE);
		controller.updateReservation(currentReservation);
		// Disparar evento global
        EventSystem.triggerDashboardRefresh();
        
        new CustomAlert(
            "Tu reserva ha sido finalizada exitosamente.\n" +
            "El estacionamiento ya está disponible para otros usuarios.",
            AlertType.SUCCESS
        );
        MainDialog.getInstance().dispose(); // close
	}

	private void cancelCurrentRegister() {
		currentReservation.setStatus(ReservationStatus.CANCELED);
		controller.updateReservation(currentReservation);
		// Disparar evento global
        EventSystem.triggerDashboardRefresh();
		new CustomAlert(
		    "Tu reserva ha sido cancelada.\n" +
		    "El lugar ya no está reservado y estará disponible para otros usuarios.",
		    AlertType.NOTICE
		);		
		MainDialog.getInstance().dispose(); // close
	}	
}