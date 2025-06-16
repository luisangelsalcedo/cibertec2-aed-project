package eparking.controllers;

import eparking.dao.UserDAO;
import eparking.enums.Permission;
import eparking.models.User;

public class AuthController {
	private UserDAO userDao = new UserDAO();
	
	private static int maxAttempts;
	private static User loggedUser;
	private static String errorMessage;
	
	static {
		maxAttempts = 5;
		setLoggedUser(null);
		setErrorMessage(null);
	}
	
	// getters
	public static User getLoggedUser() {
		return loggedUser;
	}
	public static String getErrorMessage() {
		return errorMessage;
	}
	
	// setters
	private static void setLoggedUser(User loggedUser) {
		AuthController.loggedUser = loggedUser;
	}
	private static void setErrorMessage(String errorMessage) {
		AuthController.errorMessage = errorMessage;
	}
	
	// methods
	public boolean login(String userName, String password) {
		User userFound = userDao.findUserByUserName(userName);
		
		if(userFound == null) {
			setErrorMessage("El usuario " + userName + " no existe");
			return false;
		}
		if(hasTooManyAttempts(userFound)) {
			setErrorMessage("Su usuario " + userName + " ha sido bloqueado\nComunicate con el administrador para activarlo");
			return false;
		}
		if(!verifyPassword(userFound, password)) {
			setErrorMessage("El usuario y la contraseña no coinciden");
			return false;
		}
		
		userFound.loginAttempt = 0; //reset attempt
		setLoggedUser(userFound);
		userDao.updateUser(userFound);
		setErrorMessage(null);
		return true;
	}
	
	public void logout() {
		setLoggedUser(null);
	}
		
	private boolean verifyPassword(User user, String password) {
		if(user.getPassword().equals(password)) return true;
		return false;
	}
	
	private boolean hasTooManyAttempts(User user) {
		if(user.getPermission() == Permission.ADMIN) return false;
		
		user.loginAttempt++;
		userDao.updateUser(user);
		
		if(user.loginAttempt < maxAttempts) return false;
		
		user.setUserLock(true); //user lock
		userDao.updateUser(user);
		return true;	
	}
	

	
}
