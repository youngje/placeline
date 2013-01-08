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
	
	public PinContent(){
		this.registeredDate = Calendar.getInstance();
		replyList = new ArrayList<PinReply>();
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
