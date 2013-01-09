package com.nhn.placeline.dao;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nhn.placeline.Activity.R;
import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.Pin;
import com.nhn.placeline.vo.PinContent;
import com.nhn.placeline.vo.PinReply;
import com.nhn.placeline.vo.User;

public class DatabaseConnector{
	
	SQLiteDatabase db;
	DatabaseHelper dbHelper;

	//userId를 주면 그 사용자가 이용중인 Group List를 주마.
	
	public ArrayList<Group> getGroupList(String userId) {
		dbHelper = new DatabaseHelper(null);
		db = dbHelper.getWritableDatabase();
		
		//userId가 참여하고 있는 groupId 목록을 불러옴
		Cursor result = db.rawQuery("SELECT * " +
				                    "FROM member, userIdToGroupId " +
				                    "WHERE member.groupId = userIdToGroupId.groupId , userId = '"+userId+"'", null);
		
		//쿼리문으로 결과 완성
		ArrayList<Group> groupList = null;

		result.moveToFirst();
		/*while (!result.isAfterLast()){
			   groupList.add(new Group(result.getString(2), result.getString(3) , result.getInt(4)));
			   result.moveToNext();
		}*/
		
		return groupList;
	}
	
	
//	SELECT A1.region_name REGION, SUM(A2.Sales) SALES
//	FROM Geography A1, Store_Information A2
//	WHERE A1.store_name = A2.store_name
//	GROUP BY A1.region_name
	
	//groupId를 주면 해당 그룹의 각 Pin List를 주마.
	public ArrayList<Pin> getPinList(String groupId) {
		dbHelper = new DatabaseHelper(null);
		db = dbHelper.getWritableDatabase();
		
		ArrayList<Pin> pinList = null;

		return pinList;
	}
	
	//pinId를 주면 해당 핀의 PinContent를 주마.
	public PinContent getPinContent(String pinId) {
		dbHelper = new DatabaseHelper(null);
		db = dbHelper.getWritableDatabase();
		User user = new User("백준선", "010-6848-3855", R.drawable.user_3);
		PinContent content = new PinContent(0, "생애 첫교육", "살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 흐하", user);
		return content;
	}
	
	//pinId를 주면 해당 핀의 Reply List를 주마.
	public ArrayList<PinReply> getPinReplyList(String pinId) {
		dbHelper = new DatabaseHelper(null);
		db = dbHelper.getWritableDatabase();
		
		ArrayList<PinReply> replyList = null;		
		return replyList;
	}
	
	
	
//	protected void onCreate(Bundle savedInstanceState) {
//		
//		//주의 할 부분, 임의로 null 넣음.
//		dbHelper = new DatabaseHelper(null);
//		db = dbHelper.getWritableDatabase();
//			
//		Intent intent = getIntent();
//        if(intent != null) {
//        	double total_point = intent.getDoubleExtra("point", 0);
//        	if(intent.getBooleanExtra("hasSong", false)){
//            	Song song = intent.getExtras().getParcelable("song");
//            	String name = intent.getStringExtra("name");
//            	db.execSQL("INSERT INTO rank(songTitle, points, date, name) VALUES('"+song.name+"', '"+(int)total_point+"', '"+date+"', '"+name+"');");
//        	}
//        }
//        
//		Cursor result = db.rawQuery("SELECT * FROM rank ORDER BY points desc limit 15", null);
//		result.moveToFirst();
//		int i=1;
//		while (!result.isAfterLast()){
//			   rankList.add(new Points(result.getString(1), result.getString(2), result.getString(3), result.getString(4), i));
//			   result.moveToNext();
//			   i++;
//		}
//		result.close();
//		db.close();
//		
//	}

}
