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
	private JMenuItem mntmReservar;
	private JMenuItem mntmReservaActual;
	private JMenuItem mntmMisReservas;
	private JMenuItem mntmAcercaDeLaApp;	
	private JMenuItem mntmVerUsuario;	
	private JMenuItem mntmListarUsuarios;	
	private JMenuItem mntmAgregarUsuario;	
	private JMenuItem mntmCerrarSesion;
	private JFrame parent;
	
	public MainMenu(JFrame parent){
		this.parent = parent;
		
		Color bgColor = ThemeStyles.$accent;
		Color fgColor = ThemeStyles.$white;
		Font menuFont = ThemeStyles.lgFont;
		
		
		// set menu Items

		mntmReservar 				= new JMenuItem("Reservar estacionamiento");
		mntmReservaActual 			= new JMenuItem("Reserva actual");
		mntmMisReservas 			= new JMenuItem("Mis reservas");
		mntmAcercaDeLaApp 			= new JMenuItem("Acerca de la aplicación");
		mntmVerUsuario	 			= new JMenuItem("Ver Usuario");
		mntmListarUsuarios	 		= new JMenuItem("Listar Usuarios");
		mntmAgregarUsuario	 		= new JMenuItem("Agregar Usuarios");
		mntmCerrarSesion	 		= new JMenuItem("Cerrar sesión");
		
		// set submenu font
		mntmReservar.setFont(ThemeStyles.lgFont);
		mntmReservaActual.setFont(ThemeStyles.lgFont);
		mntmMisReservas.setFont(ThemeStyles.lgFont);
		mntmAcercaDeLaApp.setFont(ThemeStyles.lgFont);
		mntmVerUsuario.setFont(ThemeStyles.lgFont);
		mntmListarUsuarios.setFont(ThemeStyles.lgFont);
		mntmAgregarUsuario.setFont(ThemeStyles.lgFont);
		mntmCerrarSesion.setFont(ThemeStyles.lgFont);
		
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
		mnMantenimiento.add(mntmReservar);
		mnMantenimiento.add(mntmReservaActual);		
		mnMantenimiento.add(mntmMisReservas);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setFont(menuFont);
		mnAyuda.setForeground(fgColor);
		mnAyuda.add(mntmAcercaDeLaApp);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setFont(menuFont);
		mnUsuarios.setForeground(fgColor);
		mnUsuarios.add(mntmVerUsuario);
		mnUsuarios.add(mntmListarUsuarios);
		mnUsuarios.add(mntmAgregarUsuario);
		mnUsuarios.add(mntmCerrarSesion);
		
		// set menu bar
		setBackground(bgColor);
		add(mnMantenimiento);
		add(mnUsuarios);
		add(mnAyuda);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub  
		Object source = e.getSource();
		String sourceTitle = ((JMenuItem)source).getText();
		
		MainDialog dialog = new MainDialog();
		dialog.setTitle(sourceTitle);
		// debug
		System.out.print(sourceTitle + "\n");
		
		if(source == mntmReservar) {
			dialog.showView(new ReserveView(dialog));
		}
		if(source == mntmReservaActual) {
			dialog.showView(new CurrentReservationView(dialog));
		}
		if(source == mntmMisReservas) {
			dialog.showView(new MyReservationsView(dialog));
		}
		if(source == mntmAcercaDeLaApp) {
			dialog.showView(new AboutUsView(dialog));
		}
		if(source == mntmVerUsuario) {
			dialog.showView(new UserInfoView(dialog));
		}
		if(source == mntmListarUsuarios) {
			dialog.showView(new UserListView(dialog));
		}
		if(source == mntmAgregarUsuario) {
			dialog.showView(new CreateUserView(dialog));
		}
		if(source == mntmCerrarSesion) {
			System.out.print("Cerrar sesion usuario\n");
			AuthController controller = new AuthController();
			
			String message = "Nos vemos, " + AuthController.getLoggedUser().getName() + ". ¡Vuelve pronto!";
			new CustomAlert(message, AlertType.DEFAULT);
			
			controller.logout();
			parent.dispose(); // cerramos el HomeView
			
			LoginView loginView = new LoginView();
			loginView.setVisible(true);
		}		
				
	}
}
