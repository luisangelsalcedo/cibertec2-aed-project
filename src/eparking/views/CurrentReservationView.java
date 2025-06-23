package eparking.views;

import java.awt.EventQueue;
import javax.swing.JPanel;
import eparking.views.components.CustomButton;
import eparking.views.components.MainDialog;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Color;

public class CurrentReservationView extends JPanel {

	private static final long serialVersionUID = -671525189399650072L;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
    public CurrentReservationView() {
    	setLayout(new BorderLayout(0, 0));
    	
    	JPanel panelSuperior = new JPanel();
    	panelSuperior.setBackground(Color.WHITE);
    	
    	add(panelSuperior, BorderLayout.NORTH);
    	
    	JLabel lblNewLabel_1 = new JLabel("Reserva:");
    	panelSuperior.add(lblNewLabel_1);
    	
    	textField = new JTextField();
    	panelSuperior.add(textField);
    	textField.setColumns(8);
    	
    	JLabel lblNewLabel = new JLabel("Lugar reservado:");
    	panelSuperior.add(lblNewLabel);
    	
    	textField_1 = new JTextField();
    	panelSuperior.add(textField_1);
    	textField_1.setColumns(3);
    	
    	JButton btnCancel = new JButton("Cancelar");
    	panelSuperior.add(btnCancel);
    	
    	JPanel panelCentral = new JPanel();
    	add(panelCentral, BorderLayout.CENTER);
    	panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
    	panelCentral.setBackground(Color.WHITE);
    	
    	JPanel panelSalida = new JPanel();
    	panelSalida.setBackground(Color.WHITE);
    	panelCentral.add(panelSalida);
    	panelSalida.setLayout(new BoxLayout(panelSalida, BoxLayout.X_AXIS));
    	
    	JPanel panel_2_1 = new JPanel();
    	panel_2_1.setBackground(Color.WHITE);
    	panelSalida.add(panel_2_1);
    	
    	CustomButton btnRegistrar_1_1 = new CustomButton("Registar Ingreso");
    	panel_2_1.add(btnRegistrar_1_1);
    	
    	JPanel panel_3_1 = new JPanel();
    	panel_3_1.setBackground(Color.WHITE);
    	panelSalida.add(panel_3_1);
    	
    	JLabel lblNewLabel_2_1 = new JLabel("Ingreso:");
    	panel_3_1.add(lblNewLabel_2_1);
    	
    	textField_3 = new JTextField();
    	textField_3.setColumns(10);
    	panel_3_1.add(textField_3);
    	
    	JPanel panelIngreso = new JPanel();
    	panelIngreso.setBackground(Color.WHITE);
    	panelCentral.add(panelIngreso);
    	panelIngreso.setLayout(new BoxLayout(panelIngreso, BoxLayout.X_AXIS));
    	
    	JPanel panel_2 = new JPanel();
    	panel_2.setBackground(Color.WHITE);
    	panelIngreso.add(panel_2);
    	
    	CustomButton btnSalida = new CustomButton("Registar Ingreso");
    	btnSalida.setText("Registar Salida");
    	panel_2.add(btnSalida);
    	
    	JPanel panel_3 = new JPanel();
    	panel_3.setBackground(Color.WHITE);
    	panelIngreso.add(panel_3);
    	
    	JLabel lblNewLabel_2 = new JLabel("Salida:");
    	panel_3.add(lblNewLabel_2);
    	
    	textField_2 = new JTextField();
    	panel_3.add(textField_2);
    	textField_2.setColumns(10);
    	
    	JPanel panelBoton = new JPanel();
    	add(panelBoton, BorderLayout.SOUTH);
    	panelBoton.setLayout(new BorderLayout(5, 5));
    	
    	CustomButton btnRegistrar = new CustomButton("Registar Ingreso");
    	panelBoton.add(btnRegistrar);
    	btnCancel.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		}
    	});
    	
	}    
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDialog.getInstance().showView(new CurrentReservationView());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
