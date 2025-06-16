package eparking.views;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import eparking.controllers.AuthController;
import eparking.utils.RootData;
import eparking.utils.ThemeStyles;
import eparking.views.components.Brand;
import eparking.views.components.Footer;
import eparking.views.components.MainLayoutPanel;
import eparking.views.components.MainMenu;
import eparking.views.components.WelcomeMessage;

public class HomeView extends JFrame{

	private static final long serialVersionUID = 1L;

	public HomeView() {
		setTitle(RootData.AppTitle + " - Bienvenido");
		setSize(700, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ThemeStyles.favicon));
		
		// components
		JMenuBar mainMenu = new MainMenu(this);
		WelcomeMessage welcomeMessage = new WelcomeMessage("Bienvenido\n"+ AuthController.getLoggedUser().getName() +"!", "Estas listo para hacer una reserva.");
		Footer footer = new Footer();		
		Brand brand = new Brand();
		
		MainLayoutPanel mainLayout 	= new MainLayoutPanel();
		mainLayout.addRight(new JLabel("[DASHBOARD]"));
		mainLayout.addLeftTop(brand);
		mainLayout.addLeftBottom(welcomeMessage);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.add(mainLayout);
		
		JLabel displayPanel = new JLabel();
		displayPanel.setOpaque(true);
		displayPanel.setIcon(new ImageIcon(getClass().getResource(RootData.sourcePath + "background.png")));
		displayPanel.setHorizontalAlignment(SwingConstants.CENTER);
		displayPanel.setBackground(ThemeStyles.$white);		
		displayPanel.setLayout(new BorderLayout());
		displayPanel.add(centerPanel, BorderLayout.CENTER);
		displayPanel.add(footer, BorderLayout.SOUTH);
		 
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,1));
		mainPanel.setBackground(ThemeStyles.$white);	
		mainPanel.add(displayPanel);
		
		setJMenuBar(mainMenu);
		setContentPane(mainPanel);
	}
}
