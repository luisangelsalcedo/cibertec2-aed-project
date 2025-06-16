package eparking.views.components;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import eparking.utils.RootData;
import eparking.utils.ThemeStyles;

public class Brand extends JPanel{

	private static final long serialVersionUID = 1L;

	public Brand() {
		JLabel brand = new JLabel();
		brand.setIcon(new ImageIcon(getClass().getResource(RootData.sourcePath + "brandIcon.png")));
		
		JLabel name = new JLabel("eParking");
		name.setFont(new Font("Tahoma", Font.PLAIN, 25));
		name.setForeground(ThemeStyles.$primaryColor);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(false);
		setVisible(true);
		add(brand);
		add(name);
	}
}
