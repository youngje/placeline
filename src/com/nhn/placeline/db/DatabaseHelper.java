package com.nhn.placeline.db;

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
				                       "userPhone TEXT);");
		
		db.execSQL("CREATE TABLE group(groupId INTEGER PRIMARY KEY AUTOINCREMENT, " +
				                      "groupName TEXT, " +
				                      "groupCreator INTEGER, " +
				                      "groupCustom TEXT);");
		
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
		
		db.execSQL("CREATE TABLE pinIdToReply(pinId INTEGER, " +
				                             "picture TEXT);");
		
		db.execSQL("CREATE TABLE pinIdToPicture(pinId INTEGER, " +
				                               "replyDate TIMESTAMP, " +
				                               "replyCreator INTEGER, " +
				                               "replyContent TEXT);");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS member");
		db.execSQL("DROP TABLE IF EXISTS group");
		db.execSQL("DROP TABLE IF EXISTS pin");
		db.execSQL("DROP TABLE IF EXISTS userIdToReply");
		db.execSQL("DROP TABLE IF EXISTS pinIdToReply");
		db.execSQL("DROP TABLE IF EXISTS pinIdToPicture");
		onCreate(db);
	}
}
