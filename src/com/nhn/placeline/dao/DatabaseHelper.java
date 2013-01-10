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
		
		db.execSQL("CREATE TABLE placegroup(groupId INTEGER PRIMARY KEY AUTOINCREMENT, " +
				                           "groupName TEXT, " +
				                           "groupCreator INTEGER, " +
				                           "groupMapId INTEGER);");
		
		db.execSQL("CREATE TABLE pin(pinId INTEGER PRIMARY KEY AUTOINCREMENT, " +
				                    "pinName TEXT, " +
				                    "pinDate TIMESTAMP, " +
				                    "pinX TEXT, " +
				                    "pinY TEXT, " +
				                    "pinContent TEXT, " +
				                    "pinThumnail INTEGER"+
				                    "groupId INTEGER);");

		db.execSQL("CREATE TABLE reply(replyId INTEGER PRIMARY KEY AUTOINCREMENT, " +
				                    "pinId INTEGER, " +
					                "replyDate TIMESTAMP, " +
					                "replyCreator INTEGER, " +
					                "replyContent TEXT);");

		//참조용 테이블
		db.execSQL("CREATE TABLE userIdToGroupId(userId INTEGER, " +
				                                "groupId INTEGER);");
		
		db.execSQL("CREATE TABLE pinIdToPicture(pinId INTEGER, " +
				                               "picture INTEGER);");
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS pinIdToPicture");
		db.execSQL("DROP TABLE IF EXISTS userIdToGroupId");
		db.execSQL("DROP TABLE IF EXISTS reply");
		db.execSQL("DROP TABLE IF EXISTS pin");
		db.execSQL("DROP TABLE IF EXISTS placegroup");
		db.execSQL("DROP TABLE IF EXISTS member");
		onCreate(db);
	}
}
