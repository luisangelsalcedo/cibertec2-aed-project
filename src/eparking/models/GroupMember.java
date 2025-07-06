package eparking.models;

public class GroupMember {
	private String fullName;
	private String code;
	
	public GroupMember(String code, String fullName){
		setCode(code);
		setFullName(fullName);
	}
	
	public void setCode(String code) {
		if(code == null || code.isEmpty()) {
			throw new IllegalArgumentException("El código no puede ser nulo ni estar vacío");
		}
		this.code = code;
	}
	public void setFullName(String fullName) {
		if(fullName == null || fullName.isEmpty()) {
			throw new IllegalArgumentException("El nombre completo no puede ser nulo ni estar vacío");
		}
		this.fullName = fullName;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getCode() {
		return code;
	}
	
	
}
