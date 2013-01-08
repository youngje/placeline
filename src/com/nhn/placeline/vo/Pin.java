package com.nhn.placeline.vo;

import java.util.Calendar;

public class Pin {
	int pinId;
	int groupId;
	float xLocation;
	float yLocation;
	Calendar registeredDate;
	User writer;
	String pinTitle;
	
	public Pin(int pinId, User writer, float xLocation, float yLocation){
		this.pinId = pinId;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		registeredDate = Calendar.getInstance();
		this.writer = writer;
	}
	public int getPinId() {
		return pinId;
	}
	public void setPinId(int pinId) {
		this.pinId = pinId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
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
	public String getPinTitle() {
		return pinTitle;
	}
	public void setPinTitle(String pinTitle) {
		this.pinTitle = pinTitle;
	}
}
