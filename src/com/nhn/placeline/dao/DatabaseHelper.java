package com.nhn.placeline.dao;

import com.nhn.placeline.Activity.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static final String DB_NAME = "placelineDB";
	public static final int DB_VERSION = 1;
	
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
				                    "groupId INTEGER, "+
				                    "writerId TEXT, " +
				                    "pinContent TEXT, "+
				                    "pinThumnail INTEGER);");

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
		
		initDB(db);
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
	
	@Override
	public void onOpen(SQLiteDatabase db){
		super.onOpen(db);
		/*db.execSQL("DROP TABLE IF EXISTS pinIdToPicture");
		db.execSQL("DROP TABLE IF EXISTS userIdToGroupId");
		db.execSQL("DROP TABLE IF EXISTS reply");
		db.execSQL("DROP TABLE IF EXISTS pin");
		db.execSQL("DROP TABLE IF EXISTS placegroup");
		db.execSQL("DROP TABLE IF EXISTS member");
		onCreate(db);*/
		
		
	}
	
	public void initDB(SQLiteDatabase db) {
		//member
		db.execSQL("insert into member(userId,userName,userPhone, thumnail) values(?,'김에이','010-1111-2666', "+R.drawable.user_1+")");
		db.execSQL("insert into member(userId,userName,userPhone, thumnail) values(?,'김이','010-2222-2666', "+R.drawable.user_2+")");
		db.execSQL("insert into member(userId,userName,userPhone, thumnail) values(?,'최씨','010-3333-2666', "+R.drawable.user_3+")");
		db.execSQL("insert into member(userId,userName,userPhone, thumnail) values(?,'박디','010-4444-2666', "+R.drawable.user_4+")");
		db.execSQL("insert into member(userId,userName,userPhone, thumnail) values(?,'고에프','010-5555-2666', "+R.drawable.user_5+")");
		db.execSQL("insert into member(userId,userName,userPhone, thumnail) values(?,'홍쥐','010-6666-2666', "+R.drawable.user_6+")");
		db.execSQL("insert into member(userId,userName,userPhone, thumnail) values(?,'한아이','010-7777-2666', "+R.drawable.user_7+")");
		
		//group
		db.execSQL("insert into placegroup(groupId,groupName,groupCreator,groupMapId) values (?,'매력학과5기',1,"+R.drawable.group_map_image_1+")");
		db.execSQL("insert into placegroup(groupId,groupName,groupCreator,groupMapId) values (?,'NHN신입교육',2,"+R.drawable.group_map_image_2+")");
		db.execSQL("insert into placegroup(groupId,groupName,groupCreator,groupMapId) values (?,'라인동호회',6,"+R.drawable.group_map_image_3+")");
		
		//group & user
		db.execSQL("insert into userIdToGroupId(userId,groupId) values (1,1)");
		db.execSQL("insert into userIdToGroupId(userId,groupId) values (3,1)");
		db.execSQL("insert into userIdToGroupId(userId,groupId) values (4,1)");
		db.execSQL("insert into userIdToGroupId(userId,groupId) values (1,2)");
		db.execSQL("insert into userIdToGroupId(userId,groupId) values (2,2)");
		db.execSQL("insert into userIdToGroupId(userId,groupId) values (5,2)");
		db.execSQL("insert into userIdToGroupId(userId,groupId) values (6,2)");
		db.execSQL("insert into userIdToGroupId(userId,groupId) values (1,3)");
		db.execSQL("insert into userIdToGroupId(userId,groupId) values (4,3)");
		db.execSQL("insert into userIdToGroupId(userId,groupId) values (6,3)");
		db.execSQL("insert into userIdToGroupId(userId,groupId) values (7,3)");
		
		//pin
		db.execSQL("insert into pin(pinId,pinName,pinDate,pinX,pinY, groupId, writerId, pinContent, pinThumnail) values (?,'서울근처A',CURRENT_TIMESTAMP,'126.4085f','33.2480f', '1', '1', '으하하하하하하하하하하하하하하', '"+R.drawable.photo_1+"')");
		db.execSQL("insert into pin(pinId,pinName,pinDate,pinX,pinY, groupId, writerId, pinContent, pinThumnail) values (?,'서울근처A',CURRENT_TIMESTAMP,'126.4015f','33.2412f', '1', '2', '이게 뭔가요?', '"+R.drawable.photo_2+"')");
		db.execSQL("insert into pin(pinId,pinName,pinDate,pinX,pinY, groupId, writerId, pinContent, pinThumnail) values (?,'서울근처A',CURRENT_TIMESTAMP,'126.4025f','33.2490f', '1', '3', '여기 좋았음. 맛집 인정', '"+R.drawable.photo_3+"')");
		db.execSQL("insert into pin(pinId,pinName,pinDate,pinX,pinY, groupId, writerId, pinContent, pinThumnail) values (?,'서울근처A',CURRENT_TIMESTAMP,'126.4056f','33.2435f', '1', '4', '다음엔 다 같이 오자 ', '"+R.drawable.photo_4+"')");
		
		/*"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'서울근처B',CURRENT_TIMESTAMP,'100','100','임시본문','1')"
		"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'서울근처C',CURRENT_TIMESTAMP,'100','100','임시본문','1')"
		"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'서울근처D',CURRENT_TIMESTAMP,'100','100','임시본문','1')"
		"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'올레길탐방A',CURRENT_TIMESTAMP,'100','100','임시본문','2')"
		"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'올레길탐방B',CURRENT_TIMESTAMP,'100','100','임시본문','2')"
		"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'올레길탐방C',CURRENT_TIMESTAMP,'100','100','임시본문','2')"
		"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'올레길탐방D',CURRENT_TIMESTAMP,'100','100','임시본문','2')"
		"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'서울근처',CURRENT_TIMESTAMP,'100','100','임시본문','3')"
		"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'대전근처',CURRENT_TIMESTAMP,'100','100','임시본문','3')"
		"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'대구근처',CURRENT_TIMESTAMP,'100','100','임시본문','3')"
		"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'광주근처',CURRENT_TIMESTAMP,'100','100','임시본문','3')"
		"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'부산근처',CURRENT_TIMESTAMP,'100','100','임시본문','3')"
		"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'제주근처',CURRENT_TIMESTAMP,'100','100','임시본문','3')"*/

		
		
	}
}
