package com.nhn.placeline.vo;

import java.util.ArrayList;
import java.util.Calendar;

public class PinContent {
	int pinId;
	String title;
	String content;
	String writer;
	ArrayList<PinReply> replyList;
	ArrayList<String> pictures;
	Calendar registeredDate;
	
	public PinContent(int pinId, String title, String content, String writer){
		this.pinId = pinId;
		this.title = title;
		this.content = content;
		this.writer = writer;
		replyList = new ArrayList<PinReply>();
		this.registeredDate = Calendar.getInstance();
	}
	public int getPinId() {
		return pinId;
	}
	public void setPinId(int pinId) {
		this.pinId = pinId;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public PinReply getReply(int index) {
		return replyList.get(index);
	}
	public void addReplyList(PinReply pinReply) {
		replyList.add(pinReply);
	}
	public ArrayList<String> getPictures() {
		return pictures;
	}
	public void setPictures(ArrayList<String> pictures) {
		this.pictures = pictures;
	}
	
	
}
