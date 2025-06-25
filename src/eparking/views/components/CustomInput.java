package eparking.views.components;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import eparking.utils.ThemeStyles;

public class CustomInput extends JTextField{

	private static final long serialVersionUID = 1L;
	
	public CustomInput(int columns, boolean editable, boolean enabled) {
		setColumns(columns);
		setPreferredSize(new Dimension(80, 30));
		setBorder(BorderFactory.createLineBorder(ThemeStyles.$primaryColor, 2));
		setFont(ThemeStyles.lgFont);
		setEnabled(enabled);
		setEditable(editable);
	}
	public CustomInput(int columns, boolean editable) {
		this(columns, editable, true);
	}
	public CustomInput(int columns) {
		this(columns, true);
	}
	public CustomInput() {
		this(5);
	}
	
}
