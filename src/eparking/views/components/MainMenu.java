package eparking.views.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eparking.utils.ThemeStyles;

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
	
	public MainMenu(){
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
		
		if(source == mntmReservar) {
			System.out.print("Reservar estacionamiento\n");
		}
		if(source == mntmReservaActual) {
			System.out.print("Reserva actual\n");
		}
		if(source == mntmMisReservas) {
			System.out.print("Mis reservas\n");
		}
		if(source == mntmAcercaDeLaApp) {
			System.out.print("Acerca de la Aplicacion\n");
		}
		if(source == mntmVerUsuario) {
			System.out.print("Ver usuario\n");
		}
		if(source == mntmListarUsuarios) {
			System.out.print("Listar usuarios\n");
		}
		if(source == mntmAgregarUsuario) {
			System.out.print("Agregar usuario\n");
		}
		if(source == mntmCerrarSesion) {
			System.out.print("Cerrar sesion usuario\n");
		}
	}
}
