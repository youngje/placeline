package com.nhn.placeline.vo;

import java.util.Calendar;

public class PinReply {
	int pinId;
	String writer;
	String comments;
	Calendar registeredDate;
	
	public int getPinId() {
		return pinId;
	}
	public void setPinId(int pinId) {
		this.pinId = pinId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Calendar getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Calendar registeredDate) {
		this.registeredDate = registeredDate;
	}
}
