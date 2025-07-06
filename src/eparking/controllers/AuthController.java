package eparking.controllers;

import eparking.enums.Permission;
import eparking.interfaces.IUserDAO;
import eparking.models.User;

public class AuthController {
	private IUserDAO userDao;
	
	private final static int maxAttempts;
	private static User loggedUser;
	
	static {
		maxAttempts = 5;
		setLoggedUser(null);
	}
	
	public AuthController(IUserDAO userDao) {
		this.userDao = userDao;
	}
	
	// getters
	public static User getLoggedUser() {
		return loggedUser;
	}

	// setters
	private static void setLoggedUser(User loggedUser) {
		AuthController.loggedUser = loggedUser;
	}
	
	// methods
	public void login(User user) {
		User userFound = userDao.findUserByUserName(user.getUserName());
		
		if(userFound == null) {
			throw new IllegalArgumentException("El usuario " + user.getUserName() + " no existe");
		}
		if(hasTooManyAttempts(userFound)) {
			throw new IllegalArgumentException("Su usuario " + user.getUserName() + " ha sido bloqueado\nComunicate con el administrador para activarlo");
		}
		if(!verifyPassword(userFound, user.getPassword())) {
			throw new IllegalArgumentException("El usuario y la contraseña no coinciden");
		}
		
		userFound.loginAttempt = 0; //reset attempt
		setLoggedUser(userFound);
		userDao.updateUser(userFound);
	}
	
	public static void logout() {
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
