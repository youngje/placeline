package com.nhn.placeline.db;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.Pin;
import com.nhn.placeline.vo.PinContent;

public class DatabaseConnector{
	
	SQLiteDatabase db;
	DatabaseHelper dbHelper;

	//userId를 주면 그 사용자가 이용중인 Group List를 주마.
	public ArrayList<Group> getGroupList(String userId) {
		dbHelper = new DatabaseHelper(null);
		db = dbHelper.getWritableDatabase();
		
		//쿼리문으로 결과 완성
		ArrayList<Group> result = null; //미완
		
		return result;
	}
	
	//groupId를 주면 해당 그룹의 각 Pin List를 주마.
	public ArrayList<Pin> getPinList(String groupId) {
		
		ArrayList<Pin> result = null;
		return result;
	}
	
	//pinId를 주면 해당 핀의 PinContent를 주마.
	public PinContent getPinContent(String pinId) {
		
		PinContent result = new PinContent();		
		return result;
	}
	
	//pinId를 주면 해당 핀의 Reply List를 주마.
	public ArrayList<PinReply> getPinReplyList(String pinId) {
		
		ArrayList<PinReply> result = null;		
		return result;
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
