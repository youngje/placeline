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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
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
		
		Log.d("### onCreate", "before showGroup");
		showGroup();
		Log.d("### onCreate", "after showGroup");
	}

	public void showGroup() {
		user = dbService.getUserById(userId);
		groups = dbService.getGroupListByUser(user);

		GridView groupGridView = (GridView) findViewById(R.id.group_gridview);

		groupGridView.setAdapter(new GroupAdapter(GroupActivity.this, groups));
		groupGridView.setSelected(false);

		groupGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				groupId = groups.get(position).getId();
				if (groups.get(position).getGroupMapId() == R.drawable.groupmapadd) {
					Intent intent = new Intent(GroupActivity.this,
							AddGroupActivity.class);
					intent.putExtra("userId", user.getId());
					startActivityForResult(intent, 1);
				} else {
					Intent intent = new Intent(GroupActivity.this,
							NMapViewer.class);
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
			displayDislog();
			return false;
		}
		return super.onKeyDown(KeyCode, event);
	}
	
	public void displayDislog(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("프로그램을 종료 하시겠습니까?");
		alertDialog.setPositiveButton("종료",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});
		alertDialog.setNegativeButton("취소",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Log.d("########## [DEBUG] ##########", "Canceled");
				}
		});
		AlertDialog newDialog = alertDialog.create();
		newDialog.show();
	}
}
