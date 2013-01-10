package com.nhn.placeline.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "placelineDB";
	private static final int DB_VERSION = 1;
	
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		//주 테이블
		db.execSQL("CREATE TABLE member(userId INTEGER PRIMARY KEY AUTOINCREMENT, " +
				                       "userName TEXT," +
				                       "userPhone TEXT," +
				                       "thumnail INTEGER);");
		
		db.execSQL("CREATE TABLE placegroup(groupId INTEGER PRIMARY KEY AUTOINCREMENT, groupName TEXT, groupCreator INTEGER, groupMapId INTEGER);");
		
		db.execSQL("CREATE TABLE pin(pinId INTEGER PRIMARY KEY AUTOINCREMENT, " +
				                    "pinName TEXT, " +
				                    "pinDate TIMESTAMP, " +
				                    "pinX TEXT, " +
				                    "pinY TEXT, " +
				                    "pinContent TEXT, " +
				                    "groupId INTEGER);");
		//참조용 테이블
		db.execSQL("CREATE TABLE userIdToGroupId(userId INTEGER, " +
				                                "groupId INTEGER);");
		
		db.execSQL("CREATE TABLE pinIdToPicture(pinId INTEGER, " +
				                               "picture TEXT);");
		
		db.execSQL("CREATE TABLE pinIdToReply(pinId INTEGER, " +
				                             "replyDate TIMESTAMP, " +
				                             "replyCreator INTEGER, " +
				                             "replyContent TEXT);");
		
		//샘플 데이터 삽입
		
		/*//member
		
		//group
		db.execSQL("insert into group(groupId,groupName,groupCreator,groupCustom)" +
	                         "values ('1','NHN신입사원교육','abc123','1')");
		//pin
		db.execSQL("insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId)" +
	                       "values ('1','올레길탐방',CURRENT_TIMESTAMP,'100','100','올레길 시작지점','1')");
		//userId > groupId
		db.execSQL("insert into userIdToGroupId(userId,groupId)" +
	                                   "values ('abc123','1')");
		//pinId > picture
		db.execSQL("insert into pinIdToPicture(pinId,picture)" +
                                      "values ('1',null)");
		//pinId > reply
		db.execSQL("insert into pinIdToPicture(pinId,replyDate,replyCreator,replyContent)" +
                                      "values ('1','CURRENT_TIMESTAMP,'abc123','첫번째 댓글은 내가 담!')");
		*/
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS userIdToReply");
		db.execSQL("DROP TABLE IF EXISTS pinIdToPicture");
		db.execSQL("DROP TABLE IF EXISTS pinIdToReply");
		db.execSQL("DROP TABLE IF EXISTS pin");
		db.execSQL("DROP TABLE IF EXISTS placegroup");
		db.execSQL("DROP TABLE IF EXISTS member");
		onCreate(db);
	}
}
