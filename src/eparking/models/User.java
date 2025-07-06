package eparking.models;

import eparking.enums.Permission;

public class User {
	private int id;
	private String name;
	private String userName;
	private String password;
	private Permission permission;
	public int loginAttempt = 0;
	private boolean userLock = false;	
	
	private User(String name, String userName, String password, Permission permission) {
		setUserName(userName);
		setName(name);
		setPassword(password);
		setPermission(permission);
	}
	
	public User() {}
	
	public static User createAdmin(String userName, String password) {
		return new User(Permission.ADMIN.toString(), userName, password, Permission.ADMIN);
	}	
	public static User createUser(String userName, String password) {
		return new User(Permission.USER.toString(), userName, password, Permission.USER);
	}

	// getters
	public int getId() {
		return id;
	}	
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public Permission getPermission() {
		return permission;
	}	
	public int getLoginAttempt() {
        return loginAttempt;
    }
	public String getName() {
		return name;
	}	
	public boolean isUserLock() {
		return userLock;
	}

	// setters
	public void setId(int id) {
		if(id <= 0) {
			throw new IllegalArgumentException("El ID debe ser un número positivo");
		}
		this.id = id;
	}
	public void setName(String name) {
		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo ni estar vacío");
		}
		this.name = name;
	}	
	public void setUserName(String userName) {
		if(userName == null || userName.isEmpty() || userName.length() < 2) {
			throw new IllegalArgumentException("El Usuario debe tener min 2 caracteres");
		}
		this.userName = userName;
	}
	public void setPassword(String password) {
		if(password == null || password.isEmpty() || password.length() < 8) {
			throw new IllegalArgumentException("La Contraseña debe tener min 8 caracteres");
		}
		this.password = password;
	}
	public void setPermission(Permission permission) {
		if(permission == null) {
			throw new IllegalArgumentException("El permiso no puede ser nulo");
		}
		this.permission = permission;
	}
	public void setLoginAttempt(int loginAttempt) {
		if (loginAttempt < 0) {
			throw new IllegalArgumentException("El número de intentos de inicio de sesión no puede ser negativo");
		}
        this.loginAttempt = loginAttempt;
    }
	public void setUserLock(boolean userLock) {
		this.userLock = userLock;
	}
}
