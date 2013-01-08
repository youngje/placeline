package com.nhn.placeline.vo;

import java.util.Calendar;

public class Pin {
	int id;
	float xLocation;
	float yLocation;
	Calendar registeredDate;
	User writer;
	
	public Pin(int id, User writer, float xLocation, float yLocation){
		this.id = id;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		registeredDate = Calendar.getInstance();
		this.writer = writer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getDate() {
		return registeredDate;
//		registeredDate.get(Calendar.YEAR);
//		registeredDate.get(Calendar.MONTH)+1);
//		registeredDate.get(Calendar.DAY_OF_MONTH);
	}
	public void setDate() {
		this.registeredDate = Calendar.getInstance();
	}
	public float getxLocation() {
		return xLocation;
	}
	public void setxLocation(int xLocation) {
		this.xLocation = xLocation;
	}
	public float getyLocation() {
		return yLocation;
	}
	public void setyLocation(int yLocation) {
		this.yLocation = yLocation;
	}
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
		this.writer = writer;
	}
}
