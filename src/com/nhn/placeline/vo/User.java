package com.nhn.placeline.vo;

public class User {
	private String id;
	private String name;
	private String phoneNumber;
	private int thumnail;
	
	public int getThumnail() {
		return thumnail;
	}

	public void setThumnail(int thumnail) {
		this.thumnail = thumnail;
	}

	public User(String id, String name, String phoneNumber, int thumnail) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.thumnail = thumnail;
	}
	
	public User(){
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
}
