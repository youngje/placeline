package com.nhn.placeline.dao;

import java.util.ArrayList;

import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.Pin;
import com.nhn.placeline.vo.PinReply;
import com.nhn.placeline.vo.User;

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
		db.execSQL("insert into member(userId, userName, userPhone, thumnail) values(?, '"+user.getName()+"','"+user.getPhoneNumber()+"','"+user.getThumnail()+"')");
	}
	
	public void addGroupToDB(Group group) {
		db.execSQL("insert into placegroup(groupId, groupName, groupCreator, groupMapId) values(?, '"+group.getName()+"','"+group.getCreator().getId()+"','"+group.getGroupMapId()+"')");
	}
	
	public void addPinToDB(Pin pin) {
		db.execSQL("insert into pin(pinId, pinName,pinDate,pinX,pinY, groupId, pinThumnail) values(?,'"+pin.getPinTitle()+"', CURRENT_TIMESTAMP,'"+pin.getxLocation()+"','"+pin.getyLocation()+"','"+pin.getGroupId()+"','"+pin.getPinThumnail()+"')");
	}
	
	public void addReplyToDB(PinReply reply){
		db.execSQL("insert into reply(replyId, pinId, replyDate, replyCreator, replyContent) values(?, '"+reply.getPinId()+"', CURRENT_TIMESTAMP, '"+reply.getWriter().getId()+"', '"+reply.getComments()+"')");
	}
	
	//관계테이블
	public void addUserToGroup(User user, Group group) {
		db.execSQL("insert into userIdToGroupId(userId,groupId) values ('"+user.getId()+"','"+group.getId()+"')");
	}
	
	public void addPictureToPin(int picture, Pin pin) {
		db.execSQL("insert into pinIdToPicture(pinId, picture) values ('"+pin.getPinId()+"', '"+picture+"')");
	}
	
	//select 
	public User getUserById(int userId) {
		Cursor result = db.rawQuery("SELECT * FROM member WHERE userId='"+userId+"'", null);
		result.moveToFirst();
		return new User(result.getInt(0), result.getString(1), result.getString(2), result.getInt(3));
	}
	
	public Group getGroupById(int groupId) {
		Cursor result = db.rawQuery("SELECT * FROM placegroup WHERE groupId='"+groupId+"'", null);
		result.moveToFirst();
		return new Group(result.getInt(0), result.getString(1), getMembersByGroupId(groupId), result.getInt(3), getUserById(result.getInt(2)));
	}
	
	public ArrayList<User> getMembersByGroupId(int groupId){
		ArrayList<User> members = new ArrayList<User>();
		
		Cursor result = db.rawQuery("SELECT userId From userIdToGroupId WHERE groupId='"+groupId+"'", null);
		result.moveToFirst();
		while(!result.isAfterLast()){
			members.add(getUserById(result.getInt(0)));
			result.moveToNext();
		}
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
		return groupList;
	}
	
	
	
	
}
