package com.nhn.placeline.vo;

import java.util.Calendar;

public class Pin {
	private int pinId;
	private int groupId;
	private float xLocation;
	private float yLocation;
	private String registeredDate;
	private User writer;
	private String pinTitle;
	private int pinThumnail;
	private String pinContent;
	
	public Pin(int pinId, int groupId, float xLocation, float yLocation,
			String registeredDate, User writer, String pinTitle,
			int pinThumnail, String pinContent) {
		super();
		this.pinId = pinId;
		this.groupId = groupId;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.registeredDate = registeredDate;
		this.writer = writer;
		this.pinTitle = pinTitle;
		this.pinThumnail = pinThumnail;
		this.pinContent = pinContent;
	}

	public Pin(String pinTitle, String pinContent, int groupId, User writer, float xLocation, float yLocation, int pinThumnail){
		this.pinTitle = pinTitle;
		this.pinContent = pinContent;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.registeredDate = calendarToString(Calendar.getInstance());
		this.writer = writer;
		this.pinThumnail = pinThumnail;
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
	
	public int getPinThumnail() {
		return pinThumnail;
	}

	public void setPinThumnail(int pinThumnail) {
		this.pinThumnail = pinThumnail;
	}
	public String getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}

	public void setxLocation(float xLocation) {
		this.xLocation = xLocation;
	}

	public void setyLocation(float yLocation) {
		this.yLocation = yLocation;
	}
	public String calendarToString(Calendar calendar){
		String year = Integer.toString(calendar.get(Calendar.YEAR));
		String month = Integer.toString(calendar.get(Calendar.MONTH)+1);
		String date = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		String minute = Integer.toString(calendar.get(Calendar.MINUTE));
		
		return  year + "년" + month + "월" + date + "일 " + hour + ":" + minute;
	}
	
	public String getPinContent() {
		return pinContent;
	}

	public void setPinContent(String pinContent) {
		this.pinContent = pinContent;
	}

}
