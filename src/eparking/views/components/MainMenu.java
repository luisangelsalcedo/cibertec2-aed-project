package eparking.views.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import eparking.controllers.AuthController;
import eparking.enums.AlertType;
import eparking.enums.Permission;
import eparking.utils.ThemeStyles;
import eparking.views.AboutUsView;
import eparking.views.CreateUserView;
import eparking.views.CurrentReservationView;
import eparking.views.LoginView;
import eparking.views.MyReservationsView;
import eparking.views.ReserveView;
import eparking.views.UserInfoView;
import eparking.views.UserListView;

public class MainMenu extends JMenuBar implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private CustomMenuItem mntmReservar;
	private CustomMenuItem mntmReservaActual;
	private CustomMenuItem mntmMisReservas;
	private CustomMenuItem mntmAcercaDeLaApp;	
	private CustomMenuItem mntmVerUsuario;	
	private CustomMenuItem mntmListarUsuarios;	
	private CustomMenuItem mntmAgregarUsuario;	
	private CustomMenuItem mntmCerrarSesion;
	private JFrame parent;
	
	public MainMenu(JFrame parent){
		this.parent = parent;
		
		Color bgColor = ThemeStyles.$accent;
		Color fgColor = ThemeStyles.$white;
		Font menuFont = ThemeStyles.lgFont;		
		
		// set menu Items
		mntmReservar 				= new CustomMenuItem("Reservar estacionamiento");
		mntmReservaActual 			= new CustomMenuItem("Reserva actual");
		mntmMisReservas 			= new CustomMenuItem("Mis reservas");
		mntmAcercaDeLaApp 			= new CustomMenuItem("Acerca de la aplicación");
		mntmVerUsuario	 			= new CustomMenuItem("Ver Usuario");
		mntmListarUsuarios	 		= new CustomMenuItem("Listar Usuarios", Permission.ADMIN);
		mntmAgregarUsuario	 		= new CustomMenuItem("Agregar Usuarios", Permission.ADMIN);
		mntmCerrarSesion	 		= new CustomMenuItem("Cerrar sesión");
		
		// set action events
		mntmReservar.addActionListener(this);
		mntmReservaActual.addActionListener(this);
		mntmMisReservas.addActionListener(this);
		mntmAcercaDeLaApp.addActionListener(this);
		mntmVerUsuario.addActionListener(this);
		mntmListarUsuarios.addActionListener(this);
		mntmAgregarUsuario.addActionListener(this);
		mntmCerrarSesion.addActionListener(this);		
		
		// set menus		
		JMenu mnMantenimiento = new JMenu("Reservas");
		mnMantenimiento.setFont(menuFont);
		mnMantenimiento.setForeground(fgColor);
		for(CustomMenuItem item : new CustomMenuItem[] {mntmReservar, mntmReservaActual, mntmMisReservas}) {
			if(item.hasMenuPermission()) mnMantenimiento.add(item);			
		}
		
		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setFont(menuFont);
		mnAyuda.setForeground(fgColor);
		mnAyuda.add(mntmAcercaDeLaApp);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setFont(menuFont);
		mnUsuarios.setForeground(fgColor);
		for(CustomMenuItem item : new CustomMenuItem[] {mntmVerUsuario, mntmListarUsuarios, mntmAgregarUsuario, mntmCerrarSesion}) {
			if(item.hasMenuPermission()) mnUsuarios.add(item);			
		}
		
		// set menu bar
		setBackground(bgColor);
		add(mnMantenimiento);
		add(mnUsuarios);
		add(mnAyuda);
	}	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String sourceTitle = ((JMenuItem)source).getText();		
		
		if(source == mntmReservar) {
			MainDialog.getInstance().showView(new ReserveView());
		}
		if(source == mntmReservaActual) {
			MainDialog.getInstance().showView(new CurrentReservationView());
		}
		if(source == mntmMisReservas) {
			MainDialog.getInstance().showView(new MyReservationsView());
		}
		if(source == mntmAcercaDeLaApp) {
			MainDialog.getInstance().showView(new AboutUsView());
		}
		if(source == mntmVerUsuario) {
			MainDialog.getInstance().showView(new UserInfoView());
		}
		if(source == mntmListarUsuarios) {
			MainDialog.getInstance().showView(new UserListView());
		}
		if(source == mntmAgregarUsuario) {
			MainDialog.getInstance().showView(new CreateUserView());
		}
		if(source == mntmCerrarSesion) {			
			String message = "Nos vemos, " 
							+ AuthController.getLoggedUser().getName() 
							+ ". ¡Vuelve pronto!";
			
			new CustomAlert(message, AlertType.DEFAULT);
			
			AuthController.logout();
			MainDialog.getInstance().dispose(); // cerramos el MainDialog
			parent.dispose(); // cerramos el HomeView
			
			LoginView loginView = new LoginView();
			loginView.setVisible(true);
		} else {
			MainDialog.getInstance().setTitle(sourceTitle);
		}	
	}
}
