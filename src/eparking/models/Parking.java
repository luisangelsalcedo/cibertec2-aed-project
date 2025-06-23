package eparking.models;

import eparking.enums.ParkingStatus;

public  class Parking {
	private int id;
	private String label;
	private ParkingStatus status;
	
	
	public Parking(int id, String label, ParkingStatus status) {
		this.id = id;
		this.label = label;
		this.status = status;
	}
	
	public Parking(String label) {
		this(1, label, ParkingStatus.AVAILABLE);
	}	
	
	public Parking(Parking p) {
		this(p.getId(), p.getLabel(), p.getStatus());
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public ParkingStatus getStatus() {
		return status;
	}
	public void setStatus(ParkingStatus status) {
		this.status = status;
	}
	
}
