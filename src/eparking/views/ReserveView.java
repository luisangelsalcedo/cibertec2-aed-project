package eparking.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import eparking.controllers.ReservationController;
import eparking.enums.AlertType;
import eparking.models.Parking;
import eparking.models.Reservation;
import eparking.utils.EventSystem;
import eparking.utils.ThemeStyles;
import eparking.views.components.CustomAlert;
import eparking.views.components.CustomButton;
import eparking.views.components.MainDialog;
import eparking.views.components.ParkingItem;

public class ReserveView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private final int maxNumColumns = 7;
	private final int gridGap = 10;
	private List<Parking> parkingList;
	private JComboBox<LocalDate> cmbDate;
	private ReservationController controller;
	private JTextField txtValue;
	private JPanel parkingPanel;
	private LocalDate now;;
	private Parking parkingSelected;
	
	public ReserveView() {		
		controller = new ReservationController();
		parkingList = controller.getParkingListWithReservations(LocalDate.now());
		parkingPanel = new JPanel();
		parkingPanel.setLayout(calculateGridLayout());
		parkingPanel.setBorder(new EmptyBorder(gridGap ,gridGap ,gridGap ,gridGap));
		renderGrid();
		
		now = LocalDate.now();
		
		LocalDate[] dateArr = {now, now.plusDays(1), now.plusDays(2)};		
		cmbDate = new JComboBox<>(dateArr);
		cmbDate.addActionListener(e -> setFilter(e));
		
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(new JLabel("Fecha de reserva"));
		topPanel.add(cmbDate);
		
		txtValue = new JTextField();
		txtValue.setColumns(5);
		txtValue.setPreferredSize(new Dimension(80, 30));
		txtValue.setBorder(BorderFactory.createLineBorder(ThemeStyles.$primaryColor, 2));
		txtValue.setFont(ThemeStyles.lgFont);
		txtValue.setEditable(false);
		
		CustomButton submitBtn = new CustomButton("Reservar");
		submitBtn.setFont(ThemeStyles.lgFont);
		submitBtn.addActionListener(e -> submitAction());
				
		JPanel bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.add(new JLabel("Lugar reservado"));
		bottomPanel.add(txtValue);
		bottomPanel.add(submitBtn);
		
    	setLayout(new BorderLayout(10,10));
    	add(topPanel, BorderLayout.NORTH);
    	add(parkingPanel, BorderLayout.CENTER);
    	add(bottomPanel, BorderLayout.SOUTH);
	}
	
	private void setFilter(ActionEvent e) {
		LocalDate date = (LocalDate) cmbDate.getSelectedItem();
	    parkingList = controller.getParkingListWithReservations(date);
	    
	    parkingPanel.removeAll();
	    parkingPanel.setLayout(calculateGridLayout());
	    renderGrid();

	    parkingPanel.revalidate();
	    parkingPanel.repaint();
	}

	private void submitAction() {
		if(parkingSelected.getId() != 0) {						
			Reservation newReservation = new Reservation(parkingSelected.getId(), (LocalDate)cmbDate.getSelectedItem());
			ReservationController controller = new ReservationController();
			try {
				controller.registerReservation(newReservation);
				new CustomAlert("Tu reservacion ha sido realizada con exito.\n"
								+ "Has reservado el estacionamiento " 
								+ parkingSelected.getLabel(), AlertType.SUCCESS);
				
				// Disparar evento global
		        EventSystem.triggerDashboardRefresh();
				
				MainDialog.getInstance().dispose();
			} catch (Exception e) {
				new CustomAlert(e.getMessage(), AlertType.ERROR);				
			}			
		} else {
			new CustomAlert("Selecciona un estacionamiento disponible", AlertType.NOTICE);
		}	
	}

	private void renderGrid() {
		for(Parking parking:parkingList) {
			ParkingItem item = new ParkingItem(parking, this);
	        parkingPanel.add(item);
		}
	}

	private GridLayout calculateGridLayout() {
		int numElements = parkingList.size();
		int columns = numElements;
		int rows = 1;
		
		if(numElements > maxNumColumns) {
			rows = (int) Math.ceil((double) numElements / maxNumColumns);
			columns = (int) Math.ceil((double) numElements / rows);	
		}		
		return new GridLayout(rows, columns, gridGap, gridGap);
	}
	
	public void setParkingSelected(Parking selected) {
		txtValue.setText(selected.getLabel());
		parkingSelected = selected;
	}
}
