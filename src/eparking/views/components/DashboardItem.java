package eparking.views.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eparking.utils.ThemeStyles;

public class DashboardItem extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public DashboardItem(int value, String label, ImageIcon image) {
		JLabel lblLabel = new JLabel(label);
		lblLabel.setForeground(ThemeStyles.$primaryColor);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		labelPanel.setOpaque(false);
		labelPanel.add(lblLabel);
		
		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(image);
	
		
		JLabel lblValue = new JLabel(value < 10 ? "0"+value: String.valueOf(value));
		lblValue.setFont(new Font("Tahoma", 0, 40));
		lblValue.setForeground(ThemeStyles.$primaryColor);
		
		setLayout(new BorderLayout());

//		setBorder(BorderFactory.createLineBorder(ThemeStyles.$primaryColor, 2));
		setOpaque(false);
		add(lblValue, BorderLayout.CENTER);
		add(lblIcon, BorderLayout.EAST);
		add(labelPanel, BorderLayout.SOUTH);
	}
}
