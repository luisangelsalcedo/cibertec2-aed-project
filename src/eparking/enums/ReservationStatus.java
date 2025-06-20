package eparking.enums;

public enum ReservationStatus {
	PENDING(0, "pendiente"),
	INPROGRESS(1, "en progreso"),
	CANCELED(2, "cancelado"),
	COMPLETE(3, "completa");
	
	private int key;
	private String name;
	
	private ReservationStatus (int key, String name) {
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

	public static ReservationStatus fromTo(String status) {
		for(ReservationStatus rs : ReservationStatus.values()){
			if(rs.toString().equalsIgnoreCase(status)) return rs;
		}
		throw new IllegalArgumentException("No enum constant interfaces.ReservationStatus." + status);
	}
	
}
