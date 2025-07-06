package eparking.views.components;

import javax.swing.JMenuItem;
import eparking.controllers.AuthController;
import eparking.enums.Permission;
import eparking.utils.ThemeStyles;

public class CustomMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;
	private Permission role;
	
	
	public CustomMenuItem(String label, Permission role){
		super(label);
		this.setRole(role); 
		
		setFont(ThemeStyles.lgFont);
	}	
	public CustomMenuItem(String label){
		this(label, Permission.USER);
	}
	public CustomMenuItem(){
		this("", Permission.USER);
	}
	
	
	//methods
	public boolean isAdmin() {
		return role.equals(Permission.ADMIN) ? true : false;
	}
	
	public boolean hasMenuPermission() {
		if(AuthController.getLoggedUser().getPermission().equals(Permission.ADMIN)) {
			return true;
		}
		if(AuthController.getLoggedUser().getPermission().equals(Permission.USER)) {
			if(getRole().equals(Permission.USER))return true;
		}
		return false;
	}
	
	
	//getters
	public Permission getRole() {
		return role;
	}	
	//setters
	public void setRole(Permission role) {
		this.role = role;
	}
}