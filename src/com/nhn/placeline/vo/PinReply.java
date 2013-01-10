package com.nhn.placeline.vo;

import java.util.Calendar;

public class PinReply {
	int replyId;
	int pinId;
	User writer;
	String comments;
	String registeredDate;
	
	public PinReply(int replyId, int pinId, User writer, String comments,
			String registeredDate) {
		super();
		this.replyId = replyId;
		this.pinId = pinId;
		this.writer = writer;
		this.comments = comments;
		this.registeredDate = registeredDate;
	}
	public PinReply(int pinId, User writer, String comments){
		this.pinId = pinId;
		this.writer = writer;
		this.comments = comments;
		this.registeredDate = calendarToString(Calendar.getInstance());
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
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public String getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}
	public String getDateToString(){		
		return  registeredDate;
	}
	public String calendarToString(Calendar calendar){
		String year = Integer.toString(calendar.get(Calendar.YEAR));
		String month = Integer.toString(calendar.get(Calendar.MONTH)+1);
		String date = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		String minute = Integer.toString(calendar.get(Calendar.MINUTE));
		
		return  year + "년" + month + "월" + date + "일 " + hour + ":" + minute;
	}
}
