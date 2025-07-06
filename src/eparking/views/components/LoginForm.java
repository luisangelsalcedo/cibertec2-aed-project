package eparking.views.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import eparking.controllers.AuthController;
import eparking.dao.UserDAO_txt;
import eparking.enums.AlertType;
import eparking.models.User;
import eparking.utils.RootData;
import eparking.utils.ThemeStyles;
import eparking.views.HomeView;

public class LoginForm extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JFrame parent;
	
	public LoginForm(JFrame parent) {
		this.parent = parent;
		
		JLabel lblUserName = new JLabel("Usuario:");
		lblUserName.setForeground(ThemeStyles.$white);
		lblUserName.setFont(ThemeStyles.mdFont);
		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setForeground(ThemeStyles.$white);
		lblPassword.setFont(ThemeStyles.mdFont);
		
		txtUserName = new JTextField();
		txtUserName.setBorder(null);
		txtUserName.addActionListener(e->submitAction());
		txtUserName.requestFocus();
		
		txtPassword = new JPasswordField();
		txtPassword.setBorder(null);
		txtPassword.addActionListener(e->submitAction());
		
		JPanel userPanel = new JPanel();
		userPanel.setOpaque(false);
		userPanel.setLayout(new BorderLayout(10,0));
		userPanel.add(lblUserName, BorderLayout.WEST);
		userPanel.add(txtUserName, BorderLayout.CENTER);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setOpaque(false);
		passwordPanel.setLayout(new BorderLayout(10,0));
		passwordPanel.add(lblPassword, BorderLayout.WEST);
		passwordPanel.add(txtPassword, BorderLayout.CENTER);
		
		JPanel loginFormPanel = new JPanel();
		loginFormPanel.setOpaque(false);
		loginFormPanel.setPreferredSize(new Dimension(0,25));
		loginFormPanel.setLayout(new GridLayout(1,2,10,0));
		loginFormPanel.add(userPanel);
		loginFormPanel.add(passwordPanel);
		
		CustomButton btnSubmit = new CustomButton("Ingresar");
		btnSubmit.addActionListener(e->submitAction());
		
		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(new ImageIcon(getClass().getResource(RootData.sourcePath + "lockIcon.png")));		
		
		setBackground(ThemeStyles.$primaryColor);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(15,0));
		add(lblIcon, BorderLayout.WEST);
		add(loginFormPanel, BorderLayout.CENTER);
		add(btnSubmit, BorderLayout.EAST);
		setVisible(true);
	}

	private void submitAction() {
		String userName;
		char[] password;		
		
		try {
			userName = txtUserName.getText().trim();
			password = txtPassword.getPassword();	
			
			User user = new User();
			user.setUserName(userName);
			user.setPassword(new String(password));
			
			
			UserDAO_txt dao = new UserDAO_txt();
			AuthController controller = new AuthController(dao);
			controller.login(user);
			HomeView homeView = new HomeView();
			homeView.setVisible(true);
			parent.dispose(); // cerramos el LoginView
			
		} catch (Exception e) {
			new CustomAlert(e.getMessage(), AlertType.ERROR);
			txtUserName.requestFocus();
			cleanTextFields();
		}
	}
	
	private void cleanTextFields() {
		txtUserName.setText(""); 
		txtPassword.setText("");
	}
}
