package eparking.views.components;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import eparking.utils.ThemeStyles;

public class MainDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public MainDialog() {
		setSize(500, 245);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(ThemeStyles.$primaryColor);
		setIconImage(
			Toolkit.getDefaultToolkit().getImage(ThemeStyles.favicon)
		);
	}
	
	public void showView(JPanel view) {
		// general panel settings
		view.setBorder(new EmptyBorder(15, 15, 15, 15));
		view.setOpaque(false);
		
		getContentPane().removeAll();
		getContentPane().add(view);
		pack();
		setVisible(true);
	}
	
	public void closeDialog() {
		this.dispose();
	}
}