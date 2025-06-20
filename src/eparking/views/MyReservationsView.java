package eparking.views;

import java.awt.EventQueue;

import javax.swing.JPanel;

import eparking.views.components.CustomButton;
import eparking.views.components.MainDialog;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;

public class MyReservationsView extends JPanel {
	private JLabel lblDisponibleColor;
	private JTextField textField;
    public MyReservationsView() {
    	setBackground(new Color(192, 192, 192));
    	setToolTipText("");
    	setLayout(null);
    	
    	JPanel R = new JPanel();
    	R.setBounds(10, 88, 430, 112);
    	add(R);
    	R.setLayout(null);
    	
    	JButton btnNewButton = new JButton("01");
    	btnNewButton.setForeground(new Color(192, 192, 192));
    	btnNewButton.setFont(new Font("Arial", Font.PLAIN, 15));
    	btnNewButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		}
    	});
    	btnNewButton.setBackground(new Color(255, 255, 255));
    	btnNewButton.setBounds(2, 0, 71, 56);
    	R.add(btnNewButton);
    	
    	JButton btn2 = new JButton("02");
    	btn2.setBackground(new Color(255, 255, 255));
    	btn2.setForeground(new Color(192, 192, 192));
    	btn2.setFont(new Font("Arial", Font.PLAIN, 15));
    	btn2.setBounds(73, 0, 71, 56);
    	btn2.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		}
    	});
    	R.add(btn2);
    	
    	JButton btn3 = new JButton("03");
    	btn3.setForeground(new Color(192, 192, 192));
    	btn3.setFont(new Font("Arial", Font.PLAIN, 15));
    	btn3.setBackground(new Color(255, 255, 255));
    	btn3.setBounds(144, 0, 71, 56);
    	R.add(btn3);
    	
    	JButton btn4 = new JButton("04");
    	btn4.setBackground(new Color(255, 255, 255));
    	btn4.setForeground(new Color(192, 192, 192));
    	btn4.setFont(new Font("Arial", Font.PLAIN, 15));
    	btn4.setBounds(215, 0, 71, 56);
    	R.add(btn4);
    	
    	JButton btn5 = new JButton("05");
    	btn5.setForeground(new Color(192, 192, 192));
    	btn5.setFont(new Font("Arial", Font.PLAIN, 15));
    	btn5.setBackground(new Color(255, 255, 255));
    	btn5.setBounds(286, 0, 71, 56);
    	R.add(btn5);
    	
    	JButton btn6 = new JButton("06");
    	btn6.setBackground(new Color(255, 255, 255));
    	btn6.setForeground(new Color(192, 192, 192));
    	btn6.setFont(new Font("Arial", Font.PLAIN, 15));
    	btn6.setBounds(357, 0, 71, 56);
    	btn6.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		}
    	});
    	R.add(btn6);
    	
    	JButton btn7 = new JButton("07");
    	btn7.setBackground(new Color(255, 255, 255));
    	btn7.setFont(new Font("Arial", Font.PLAIN, 15));
    	btn7.setForeground(new Color(192, 192, 192));
    	btn7.setBounds(2, 56, 71, 56);
    	R.add(btn7);
    	
    	JButton btn8 = new JButton("08");
    	btn8.setBackground(new Color(255, 255, 255));
    	btn8.setForeground(new Color(192, 192, 192));
    	btn8.setFont(new Font("Arial", Font.PLAIN, 15));
    	btn8.setBounds(73, 56, 71, 56);
    	R.add(btn8);
    	
    	JButton btn9 = new JButton("09");
    	btn9.setBackground(new Color(255, 255, 255));
    	btn9.setFont(new Font("Arial", Font.PLAIN, 15));
    	btn9.setForeground(new Color(192, 192, 192));
    	btn9.setBounds(144, 56, 71, 56);
    	R.add(btn9);
    	
    	JButton btn10 = new JButton("10");
    	btn10.setBackground(new Color(255, 255, 255));
    	btn10.setForeground(new Color(192, 192, 192));
    	btn10.setFont(new Font("Arial", Font.PLAIN, 15));
    	btn10.setBounds(215, 56, 71, 56);
    	R.add(btn10);
    	
    	JButton btn11 = new JButton("11");
    	btn11.setBackground(new Color(255, 255, 255));
    	btn11.setFont(new Font("Arial", Font.PLAIN, 15));
    	btn11.setForeground(new Color(192, 192, 192));
    	btn11.setBounds(286, 56, 71, 56);
    	R.add(btn11);
    	
    	JButton btn12 = new JButton("12");
    	btn12.setBackground(new Color(255, 255, 255));
    	btn12.setForeground(new Color(192, 192, 192));
    	btn12.setFont(new Font("Arial", Font.PLAIN, 15));
    	btn12.setBounds(357, 56, 71, 56);
    	R.add(btn12);
    	
    	JLabel lblFechaReserva = new JLabel("Fecha de reserva: ");
    	lblFechaReserva.setBounds(18, 35, 111, 18);
    	add(lblFechaReserva);
    	
    	JComboBox comboBox = new JComboBox();
    	comboBox.setBounds(128, 31, 75, 22);
    	add(comboBox);
    	
    	JLabel lblDisponible = new JLabel(" Disponible ");
    	lblDisponible.setBounds(251, 53, 77, 14);
    	add(lblDisponible);
    	
    	lblDisponibleColor = new JLabel("");
    	lblDisponibleColor.setBackground(new Color(255, 255, 255));
    	lblDisponibleColor.setOpaque(true);
    	lblDisponibleColor.setBounds(307, 53, 21, 14);
    	add(lblDisponibleColor);
    	
    	JLabel lblNewLabel = new JLabel("Ocupado");
    	lblNewLabel.setBounds(349, 53, 67, 14);
    	add(lblNewLabel);
    	
    	JLabel lblDisponibleColor_1 = new JLabel("");
    	lblDisponibleColor_1.setOpaque(true);
    	lblDisponibleColor_1.setBackground(new Color(255, 128, 128));
    	lblDisponibleColor_1.setBounds(406, 53, 21, 14);
    	add(lblDisponibleColor_1);
    	
    	CustomButton btnNewButton_11 = new CustomButton("Reservar");
    	btnNewButton_11.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		}
    	});
    	btnNewButton_11.setBounds(338, 247, 89, 23);
    	add(btnNewButton_11);
    	
    	textField = new JTextField();
    	textField.setBackground(new Color(192, 192, 192));
    	textField.setOpaque(false);
    	textField.setSelectedTextColor(new Color(192, 192, 192));
    	textField.setBounds(266, 248, 62, 20);
    	add(textField);
    	textField.setColumns(10);
    	
    	JLabel lblNewLabel_1 = new JLabel("Lugar reservado");
    	lblNewLabel_1.setForeground(new Color(64, 0, 128));
    	lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 11));
    	lblNewLabel_1.setBounds(151, 251, 105, 14);
    	add(lblNewLabel_1);
		
	}
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDialog.getInstance().showView(new MyReservationsView());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}