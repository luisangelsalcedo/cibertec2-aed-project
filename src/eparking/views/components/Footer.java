package eparking.views.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import eparking.utils.RootData;
import eparking.utils.ThemeStyles;

public class Footer extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public Footer() {
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getResource(RootData.sourcePath + "komatsuLogo.png")));
		
		JLabel lblPowerBy = new JLabel();
		lblPowerBy.setFont(ThemeStyles.mdFont);
		lblPowerBy.setForeground(ThemeStyles.$primaryColor);		
		lblPowerBy.setText("Power by");
		
		JLabel lblVersion = new JLabel();
		lblVersion.setFont(ThemeStyles.mdFont);
		lblVersion.setForeground(ThemeStyles.$primaryColor);
		lblVersion.setText("Version " + RootData.version);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setOpaque(false);
		panelLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLeft.add(lblPowerBy);
		panelLeft.add(logo);
		
		JPanel panelRight = new JPanel();
		panelRight.setOpaque(false);
		panelRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelRight.add(lblVersion);
		
		setBorder(new EmptyBorder(15,15,15,15));
		setLayout(new BorderLayout());
		add(panelLeft, BorderLayout.WEST);
		add(panelRight, BorderLayout.EAST);
		setOpaque(false);
		setVisible(true);
	}
}