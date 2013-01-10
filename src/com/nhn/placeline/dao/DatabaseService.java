package com.nhn.placeline.dao;

import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.Pin;
import com.nhn.placeline.vo.User;

import android.content.Context;
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
	
	public void addUserToDB(User user) {
		db.execSQL("insert into member(userId, userName, userPhone, thumnail) values(?, '"+user.getName()+"','"+user.getPhoneNumber()+"','"+user.getThumnail()+"')");
	}
	
	public void addGroupToDB(Group group) {
		db.execSQL("insert into placegroup(groupId, groupName, groupCreator, groupMapId) values (?, '"+group.getName()+"','"+group.getCreator().getId()+"','"+group.getGroupMapId()+"')");
	}
	
	public void addPinToDB(Pin pin) {
		db.execSQL("insert into pin(pinId, pinName,pinDate,pinX,pinY, groupId, pinThumnail) values (?,'"+pin.getPinTitle()+"', CURRENT_TIMESTAMP,'"+pin.getxLocation()+"','"+pin.getyLocation()+"','"+pin.getGroupId()+"','"+pin.getPinThumnail()+"')");
	}
}
