package com.nhn.placeline.vo;

import java.util.Calendar;

public class PinReply {
	int pinId;
	User writer;
	String comments;
	Calendar registeredDate;
	
	public PinReply(int pinId, User writer, String comments){
		this.pinId = pinId;
		this.writer = writer;
		this.comments = comments;
		this.registeredDate = Calendar.getInstance();
	}
	public int getPinId() {
		return pinId;
	}
	public void setPinId(int pinId) {
		this.pinId = pinId;
	}
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
		this.writer = writer;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public String getDateToString(){
		String year = Integer.toString(registeredDate.get(Calendar.YEAR));
		String month = Integer.toString(registeredDate.get(Calendar.MONTH)+1);
		String date = Integer.toString(registeredDate.get(Calendar.DAY_OF_MONTH));
		String hour = Integer.toString(registeredDate.get(Calendar.HOUR_OF_DAY));
		String minute = Integer.toString(registeredDate.get(Calendar.MINUTE));
		
		return  year + "년" + month + "월" + date + "일 " + hour + ":" + minute;
	}
}
