package com.nhn.placeline.dao;

import com.nhn.placeline.Activity.R;
import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.Pin;
import com.nhn.placeline.vo.PinReply;
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
	

	//member table set
	/*

		sample query for userIdToGroupId table (완성)

		insert into userIdToGroupId(userId,groupId) values ('abc123','1')
		insert into userIdToGroupId(userId,groupId) values ('abc345','1')
		insert into userIdToGroupId(userId,groupId) values ('abc456','1')
		insert into userIdToGroupId(userId,groupId) values ('abc123','2')
		insert into userIdToGroupId(userId,groupId) values ('abc234','2')
		insert into userIdToGroupId(userId,groupId) values ('abc567','2')
		insert into userIdToGroupId(userId,groupId) values ('abc678','2')
		insert into userIdToGroupId(userId,groupId) values ('abc123','3')
		insert into userIdToGroupId(userId,groupId) values ('abc456','3')
		insert into userIdToGroupId(userId,groupId) values ('abc678','3')
		insert into userIdToGroupId(userId,groupId) values ('abc789','3')

		sample query for pinToPicture table (미완성)

		insert into pinIdToPicture(pinId,picture) values ('1',null)

		sample query for pinToReply table (미완성)

		insert into pinIdToPicture(pinId,replyDate,replyCreator,replyContent) values ('1','CURRENT_TIMESTAMP,'abc123','첫번째 댓글은 내가 담!')


		*/
	}
	
	
	
}
