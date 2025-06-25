package eparking.views;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import eparking.controllers.AuthController;
import eparking.controllers.ReservationController;
import eparking.models.Reservation;
import eparking.utils.EventSystem;
import eparking.utils.RootData;
import eparking.utils.ThemeStyles;
import eparking.views.components.Brand;
import eparking.views.components.CustomButton;
import eparking.views.components.DashboardItem;
import eparking.views.components.Footer;
import eparking.views.components.MainDialog;
import eparking.views.components.MainLayoutPanel;
import eparking.views.components.MainMenu;
import eparking.views.components.WelcomeMessage;

public class HomeView extends JFrame{

	private static final long serialVersionUID = 1L;
	JPanel dashboardPanel;
	private ReservationController controller;

	public HomeView() {
		cleanOldReservations();
		
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
		
		dashboardPanel = new JPanel();
		dashboardPanel.setOpaque(false);
		dashboardPanel.setLayout(new GridLayout(3, 1, 20, 20));
		dashboardPanel.setBorder(new EmptyBorder(40,160,0,80));
		
		// Suscribirse al evento global de refresco del dashboard
	    EventSystem.onDashboardRefresh((ignore) -> addDashboard());
		addDashboard();
		
		CustomButton reserveBtn = new CustomButton("Si, quiero reservar");
		reserveBtn.setFont(ThemeStyles.xlFont);
		reserveBtn.addActionListener(e -> openReserveView());
		
		JPanel welcomePanel = new JPanel();
		welcomePanel.setOpaque(false);
		welcomePanel.setLayout(new BorderLayout());
		welcomePanel.add(welcomeMessage, BorderLayout.CENTER);
		welcomePanel.add(reserveBtn, BorderLayout.SOUTH);
		
		MainLayoutPanel mainLayout = new MainLayoutPanel();
		mainLayout.addRight(dashboardPanel);
		mainLayout.addLeftTop(brand);
		mainLayout.addLeftBottom(welcomePanel);
		
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
	
	private void cleanOldReservations() {
		ReservationController controller = new ReservationController();
		controller.markOldReservarionsAsCanceled();
	}

	private void openReserveView() {
		MainDialog.getInstance().showView(new ReserveView());
		MainDialog.getInstance().setTitle("Reservar estacionamiento");
	}

	private void addDashboard() {
		ImageIcon icon1 = new ImageIcon(getClass().getResource(RootData.sourcePath + "parkedIcon.png"));
		ImageIcon icon2 = new ImageIcon(getClass().getResource(RootData.sourcePath + "reservationIcon.png"));
		ImageIcon icon3 = new ImageIcon(getClass().getResource(RootData.sourcePath + "availableIcon.png"));

		controller = new ReservationController();
		
		dashboardPanel.removeAll();
		dashboardPanel.revalidate();
		dashboardPanel.repaint();
		dashboardPanel.add(new DashboardItem(controller.getTodayInProgressReservationsCount(), "Vehiculos estacionados", icon1));
		dashboardPanel.add(new DashboardItem(controller.getTodayCurrentReservationsCount(), "Reservas realizadas", icon2));
		dashboardPanel.add(new DashboardItem(controller.getTodayAvailableParkingCount(), "Lugares disponibles", icon3));
	}
}
