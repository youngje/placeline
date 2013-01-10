package com.nhn.placeline.Activity;

import java.util.ArrayList;

import com.nhn.placeline.Activity.R;
import com.nhn.placeline.constants.Constants;
import com.nhn.placeline.dao.DatabaseHelper;
import com.nhn.placeline.dao.DatabaseService;
import com.nhn.placeline.util.GroupAdapter;
import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.User;
import com.nhn.android.mapviewer.NMapViewer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GroupActivity extends Activity {
	
	private DatabaseService dbService;
	
	private ArrayList<Group> groups;
	private User user;
	private String userid = "oskar";
	private int groupId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		
		dbService = new DatabaseService(this);
		
		initDataBase();
		
		getGroupList(userid);
		GridView groupGridView = (GridView) findViewById(R.id.group_gridview);
		
		groupGridView.setAdapter(new GroupAdapter(GroupActivity.this, groups));
		groupGridView.setSelected(false);
		
		groupGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				groupId = groups.get(position).getId();	
				if(groupId==Constants.ADD_GROUP) {
					Intent intent = new Intent(GroupActivity.this, AddGroupActivity.class);
					intent.putExtra("userId", userid);
					startActivity(intent);
				}
				else {
					Intent intent = new Intent(GroupActivity.this, NMapViewer.class);
					intent.putExtra("groupId", groupId);
					intent.putExtra("userId", userid);
					startActivity(intent);
				}
				
			}
			
		});
	}

	private void initDataBase() {
		user = new User("윤영제", "016-9611-7061", R.drawable.user_4);
		
		dbService.addUserToDB(new User("윤영제", "016-9611-7061", R.drawable.user_4));
		dbService.addUserToDB(new User("김성호", "010-8824-2666", R.drawable.user_2));
		dbService.addUserToDB(new User("백준선", "010-6848-3855", R.drawable.user_3));
		dbService.addUserToDB(new User("윤홍경", "010-9788-0411", R.drawable.user_1));
		
		Group group = new Group("우리가족", user, R.drawable.group_map_image_1);
		
		dbService.addGroupToDB(group);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_group, menu);
		return true;
	}
	
	public void getGroupList(String userid) {
		// 추후 DB연동으로 변경 예정
		groups = new ArrayList<Group>();
		groups.add(new Group("test", user, R.drawable.group_map_image_1));
		groups.add(new Group("한글", user, R.drawable.group_map_image_2));
		groups.add(new Group("C조   6-2조", user, R.drawable.group_map_image_3));
	}
	
}
