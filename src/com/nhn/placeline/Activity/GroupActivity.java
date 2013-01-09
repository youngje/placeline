package com.nhn.placeline.Activity;

import java.util.ArrayList;

import com.nhn.placeline.Activity.R;
import com.nhn.placeline.constants.Constants;
import com.nhn.placeline.util.GroupAdapter;
import com.nhn.placeline.vo.Group;
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

	private ArrayList<Group> groups;
	private String userid = "oskar";
	private String groupId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		
		getGroupList(userid);
		GridView groupGridView = (GridView) findViewById(R.id.group_gridview);
		
		groupGridView.setAdapter(new GroupAdapter(GroupActivity.this, groups));
		groupGridView.setSelected(false);
		
		groupGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				groupId = groups.get(position).getId();	
				if(groupId.equals(Constants.ADD_GROUP)){
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_group, menu);
		return true;
	}
	
	public void getGroupList(String userid) {
		// 추후 DB연동으로 변경 예정
		groups = new ArrayList<Group>();
		groups.add(new Group("test1", "test", R.drawable.group_map_image_1));
		groups.add(new Group("test2", "한글", R.drawable.group_map_image_2));
		groups.add(new Group("test3", "6-2조", R.drawable.group_map_image_3));
	}
	
}
