package eparking.views.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import eparking.enums.ParkingStatus;
import eparking.models.Parking;
import eparking.utils.ThemeStyles;
import eparking.views.ReserveView;

public class ParkingItem extends JButton{

	private static final long serialVersionUID = 1L;
	private JLabel label;
	public static ParkingItem selected;	

	public ParkingItem(Parking element, ReserveView parent) {
		
		label = new JLabel(element.getLabel());
		label.setFont(ThemeStyles.xlFont);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setOpaque(false);
		labelPanel.setLayout(new GridBagLayout());
		labelPanel.add(label);
	    
		setPreferredSize(new Dimension(80, 80));
		setLayout(new GridLayout(1,1));
		setEnabled(element.getStatus() == ParkingStatus.AVAILABLE);
		addActionListener(e -> {
			if(selected != null && selected != this) {
				// reset previous selection
				selected.setEnabled(true);
				setAvailableStyle(selected); 
			}			
			selected = this;
			setEnabled(false);
			setSelectedStyle(this);			
			parent.setParkingSelected(element);
		});
	
		if(element.getStatus() == ParkingStatus.AVAILABLE) {
			setAvailableStyle(this);
			// hover effect
			addMouseListener(new MouseAdapter() {			
				@Override
				public void mouseEntered(MouseEvent e) {
					if(ParkingItem.this != selected) setHoverStyle(ParkingItem.this);
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					if(ParkingItem.this != selected) setAvailableStyle(ParkingItem.this);
				}
			});
		} else {
			setBusyStyle(this);
		}		
		setFocusable(false);		
		setFocusPainted(false);
	    setBorderPainted(false);
		add(labelPanel);
	}
	
	private void setAvailableStyle(ParkingItem button) {
		button.setBackground(Color.WHITE);
		button.setLabelColor(ThemeStyles.$gray);
	}
	private void setBusyStyle(ParkingItem button) {
		button.setBackground(ThemeStyles.$dangerColor);
		button.setLabelColor(Color.WHITE);
	}
	private void setSelectedStyle(ParkingItem button) {
		button.setBackground(ThemeStyles.$primaryColor);
		button.setLabelColor(Color.WHITE);
	}
	private void setHoverStyle(ParkingItem button) {
		button.setBackground(ThemeStyles.$primaryColorLight);
		button.setLabelColor(Color.WHITE);
	}
	public void setLabelColor(Color color) {
		label.setForeground(color);
	}
}
