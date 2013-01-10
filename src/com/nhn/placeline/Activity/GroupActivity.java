package com.nhn.placeline.Activity;

import java.util.ArrayList;

import com.nhn.placeline.Activity.R;
import com.nhn.placeline.dao.DatabaseService;
import com.nhn.placeline.util.GroupAdapter;
import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.User;
import com.nhn.android.mapviewer.NMapViewer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GroupActivity extends Activity {
	
	private DatabaseService dbService;
	
	private ArrayList<Group> groups;
	private User user;
	private int userId = 1;
	private int groupId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		
		dbService = new DatabaseService(this);
		
		showGroup();
		
	}

	public void showGroup(){
		user = dbService.getUserById(userId);
		groups = dbService.getGroupListByUser(user);
		
		GridView groupGridView = (GridView) findViewById(R.id.group_gridview);
		
		groupGridView.setAdapter(new GroupAdapter(GroupActivity.this, groups));
		groupGridView.setSelected(false);
		
		groupGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				groupId = groups.get(position).getId();	
				if(groups.get(position).getGroupMapId()==R.drawable.groupmapadd) {
					Intent intent = new Intent(GroupActivity.this, AddGroupActivity.class);
					intent.putExtra("userId", user.getId());
					startActivity(intent);
				}
				else {
					Intent intent = new Intent(GroupActivity.this, NMapViewer.class);
					intent.putExtra("groupId", groupId);
					intent.putExtra("userId", user.getId());
					startActivity(intent);
				}
				
			}
			
		});
	}
	
	@Override
	public void onResume(){
		super.onResume();
		showGroup();
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		dbService.closeDb();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_group, menu);
		return true;
	}
	
	
}
