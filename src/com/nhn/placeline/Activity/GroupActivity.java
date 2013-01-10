package com.nhn.placeline.Activity;

import java.util.ArrayList;

import com.nhn.placeline.Activity.R;
import com.nhn.placeline.dao.DatabaseService;
import com.nhn.placeline.util.GroupAdapter;
import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.User;
import com.nhn.android.mapviewer.NMapViewer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

@SuppressLint("HandlerLeak")
public class GroupActivity extends Activity {

	private DatabaseService dbService;

	private ArrayList<Group> groups;
	private User user;
	private int userId = 1;
	private int groupId;
	private static final int ADD_GROUP_ACTIVITY = 1;
	Handler mHandler;
	private boolean isToastUp=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);

		dbService = new DatabaseService(this);
		
		Log.d("### onCreate", "before showGroup");
		showGroup();
		Log.d("### onCreate", "after showGroup");
		
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				if(msg.what==0) {
					isToastUp = false;
				}
			}
		};
	}

	public void showGroup() {
		user = dbService.getUserById(userId);
		Log.d("####", user.toString());
		groups = dbService.getGroupListByUser(user);
		Log.d("####", groups.toString());

		GridView groupGridView = (GridView) findViewById(R.id.group_gridview);

		groupGridView.setAdapter(new GroupAdapter(GroupActivity.this, groups));
		groupGridView.setSelected(false);

		groupGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				groupId = groups.get(position).getId();
				
				if (groups.get(position).getGroupMapId() == R.drawable.groupmapadd) {
					Intent intent = new Intent(GroupActivity.this, AddGroupActivity.class);
					intent.putExtra("userId", user.getId());
					startActivityForResult(intent, ADD_GROUP_ACTIVITY);
				} else {
					Intent intent = new Intent(GroupActivity.this, NMapViewer.class);
					intent.putExtra("groupId", groupId);
					intent.putExtra("userId", user.getId());
					startActivity(intent);
				}
			}

		});
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d("### onResume", "before showGroup");
		showGroup();
		Log.d("### onResume", "after showGroup");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("### onDestroy", "before showGroup");
		dbService.closeDb();
		Log.d("### onDestroy", "after showGroup");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_group, menu);
		return true;
	}
	
	public boolean onKeyDown(int KeyCode, KeyEvent event) {
		if (KeyCode == KeyEvent.KEYCODE_BACK) {
			if(isToastUp){
				finish();
				
			} else {
				Toast.makeText(GroupActivity.this, "'뒤로'버튼을 한번 더 누르시면 종료 됩니다.", Toast.LENGTH_LONG).show();
				mHandler.sendEmptyMessageDelayed(0, 2000);
				isToastUp = true;
			}
			return false;
		}
		return super.onKeyDown(KeyCode, event);
	}
	
}
