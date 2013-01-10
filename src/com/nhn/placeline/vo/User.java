package com.nhn.placeline.vo;

public class User {
	private int id;
	private String name;
	private String phoneNumber;
	private int thumnail;
	
	public User(int id, String name, String phoneNumber, int thumnail) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.thumnail = thumnail;
	}

	public User(String name, String phoneNumber, int thumnail) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.thumnail = thumnail;
	}
	
	public User(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	public int getThumnail() {
		return thumnail;
	}

	public void setThumnail(int thumnail) {
		this.thumnail = thumnail;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
}
