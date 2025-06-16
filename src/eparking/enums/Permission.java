package eparking.enums;

public enum Permission {
	USER(0, "usuario"),
	ADMIN(1, "administrador");
	
	private int key;
	private String name;
	
	private Permission(int key, String name){
		this.key = key;
		this.name = name;
	}
	
	// getters
	public int getKey() {
		return key;
	}
	
	@Override
    public String toString(){
        return name;
    }

	public static Permission fromName(String name) {
        for (Permission p : Permission.values()) {
            if (p.name.equalsIgnoreCase(name)) {
                return p;
            }
        }
        throw new IllegalArgumentException("No enum constant interfaces.Permission." + name);
    }
}
