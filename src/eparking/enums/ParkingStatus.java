package eparking.enums;

public enum ParkingStatus {
	AVAILABLE(0, "disponible"),
	BUSY(1, "ocupado");
	
	private int key;
	private String name;
	
	private ParkingStatus(int key, String name) {
		this.key = key;
		this.name = name;
	}

	public int getKey() {
		return key;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public static ParkingStatus fromTo(String status) {
		for(ParkingStatus rs : ParkingStatus.values()){
			if(rs.toString().equalsIgnoreCase(status)) return rs;
		}
		throw new IllegalArgumentException("No enum constant interfaces.ParkingStatus." + status);
	}
}
