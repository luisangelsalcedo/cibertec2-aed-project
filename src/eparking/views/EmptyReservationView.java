package eparking.views;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import eparking.utils.ThemeStyles;
import eparking.views.components.CustomButton;
import eparking.views.components.MainDialog;

public class EmptyReservationView extends JPanel{

	private static final long serialVersionUID = 1L;

	public EmptyReservationView() {
		JLabel label = new JLabel("Realiza una reservacion");
		label.setFont(ThemeStyles.lgFont);
		
		CustomButton btn = new CustomButton("Ver estacionamientos disponibles");
		
		btn.addActionListener(e ->{
			MainDialog.getInstance().showView(new ReserveView());
			MainDialog.getInstance().setTitle("Reservar estacionamiento");
		});
		
		setLayout(new GridLayout(2, 1, 10, 10));
		setOpaque(false);
		add(label);
		add(btn);		
	}
}
