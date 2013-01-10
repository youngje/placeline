package com.nhn.placeline.dao;

import java.util.ArrayList;

import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.Pin;
import com.nhn.placeline.vo.PinReply;
import com.nhn.placeline.vo.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseService {
	
	public SQLiteDatabase db;
	public DatabaseHelper dbHelper;
	public Context mContext;
	
	public DatabaseService(Context context) {
		this.mContext = context;
		this.dbHelper = new DatabaseHelper(context);
		this.db = dbHelper.getWritableDatabase();
	}
	
	//기본 정보 데이터베이스 입력
	public void addUserToDB(User user) {
		ContentValues values = new ContentValues();
		values.put("userName", user.getName());
		values.put("userPhone", user.getPhoneNumber());
		values.put("thumnail", user.getThumnail());
		
		db.insert(DatabaseHelper.DB_NAME, null, values);
		//db.execSQL("insert into member(userId, userName, userPhone, thumnail) values(?, '"+user.getName()+"','"+user.getPhoneNumber()+"',"+user.getThumnail()+")");
	}
	
	public void addGroupToDB(Group group) {
		db.execSQL("insert into placegroup(groupId, groupName, groupCreator, groupMapId) values(?, '"+group.getName()+"',"+group.getCreator().getId()+","+group.getGroupMapId()+")");
	}
	
	public void addPinToDB(Pin pin) {
		db.execSQL("insert into pin(pinId, pinName,pinDate,pinX,pinY, groupId, pinThumnail) values(?,'"+pin.getPinTitle()+"', '"+pin.getRegisteredDate()+"','"+pin.getxLocation()+"','"+pin.getyLocation()+"',"+pin.getGroupId()+","+pin.getPinThumnail()+")");
	}
	
	public void addReplyToDB(PinReply reply){
		db.execSQL("insert into reply(replyId, pinId, replyDate, replyCreator, replyContent) values(?, "+reply.getPinId()+", '"+reply.getRegisteredDate()+"', "+reply.getWriter().getId()+", '"+reply.getComments()+"')");
	}
	
	//관계테이블
	public void addUserToGroup(User user, Group group) {
		db.execSQL("insert into userIdToGroupId(userId,groupId) values ("+user.getId()+","+group.getId()+")");
	}
	
	public void addPictureToPin(int picture, Pin pin) {
		db.execSQL("insert into pinIdToPicture(pinId, picture) values ("+pin.getPinId()+",'"+picture+")");
	}
	
	//select 
	public User getUserById(int userId) {
		Cursor result = db.rawQuery("SELECT * FROM member WHERE userId='"+userId+"'", null);
		result.moveToFirst();
		User user = new User(Integer.parseInt(result.getString(0)), result.getString(1), result.getString(2), Integer.parseInt(result.getString(3)));
		result.close();
		
		return user;
	}
	
	public Group getGroupById(int groupId) {
		Cursor result = db.rawQuery("SELECT * FROM placegroup WHERE groupId='"+groupId+"'", null);
		result.moveToFirst();
		Group group = new Group(result.getInt(0), result.getString(1), getMembersByGroupId(groupId), result.getInt(3), getUserById(result.getInt(2)));
		result.close();
		
		return group;
	}
	
	public PinReply getReplyById(int replyId){
		Cursor result = db.rawQuery("SELECT * FROM reply WHERE replyId='"+replyId+"'", null);
		result.moveToFirst();
		PinReply reply = new PinReply(result.getInt(0), resulte.get);
	}
	
	public ArrayList<User> getMembersByGroupId(int groupId){
		ArrayList<User> members = new ArrayList<User>();
		
		Cursor result = db.rawQuery("SELECT userId From userIdToGroupId WHERE groupId='"+groupId+"'", null);
		result.moveToFirst();
		while(!result.isAfterLast()){
			members.add(getUserById(result.getInt(0)));
			result.moveToNext();
		}
		result.close();
		
		return members;
	}
	
	public ArrayList<Group> getGroupListByUser(User user) {
		Cursor result = db.rawQuery("SELECT groupId " +
				                    "FROM userIdToGroupId " +
				                    "WHERE userId = '"+user.getId()+"'", null);
		
		//쿼리문으로 결과 완성
		ArrayList<Group> groupList = new ArrayList<Group>();

		result.moveToFirst();
		while (!result.isAfterLast()){
			groupList.add(getGroupById(result.getInt(0)));
			result.moveToNext();
		}
		result.close();
		return groupList;
	}

	public ArrayList<PinReply> replies getReplyListBy(Pin pin){
		Cursor result = db.rawQuery("SELECT * FROM reply WHERE pinId='"+pin.getPinId()+"'", null);
		
		ArrayList<PinReply> replies = new ArrayList<PinReply>();
		
		result.moveToFirst();
		while(!result.isAfterLast()){
			replies.add()
		}
	}
	//member table set
	/*
		sample query for pinToPicture table (미완성)

		insert into pinIdToPicture(pinId,picture) values ('1',null)

		sample query for pinToReply table (미완성)

		insert into pinIdToPicture(pinId,replyDate,replyCreator,replyContent) values ('1','CURRENT_TIMESTAMP,'abc123','첫번째 댓글은 내가 담!')


		*/
	
	public void closeDb(){
		db.close();
	}
	
	
}
