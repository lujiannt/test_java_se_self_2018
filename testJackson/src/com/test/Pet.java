package com.test;

public class Pet {
	private int petId;
	private String petName;
	private String masterName;
	
	
	public Pet() {
		
	}
	
	public Pet(int petId, String petName, String masterName) {
		super();
		this.petId = petId;
		this.petName = petName;
		this.masterName = masterName;
	}
	
	public int getPetId() {
		return petId;
	}
	public void setPetId(int petId) {
		this.petId = petId;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
}
