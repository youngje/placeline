package com.nhn.placeline.Activity;

import com.nhn.placeline.dao.DatabaseService;
import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.User;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("HandlerLeak")
public class AddGroupActivity extends Activity {
	
	private DatabaseService dbService;
	private int userId;
	private int groupId;
	private User user;
	Handler mHandler;
	private boolean isSetting;
	private ImageView confirm;
	private ImageView imagePrev;
	private ImageView colorImage1;
	private ImageView colorImage2;
	private ImageView colorImage3;
	private EditText groupTitleEdit;
	private TextView groupTitle;
	private Group newGroup;
	private int groupImageColor = R.drawable.group_map_image_2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_group);
		
		Intent intent = this.getIntent();
		isSetting = intent.getBooleanExtra("isSetting", false);
		
		userId = intent.getIntExtra("userId", -1);
		groupId = intent.getIntExtra("groupId", -1);
		
		dbService = new DatabaseService(this);
		
		confirm = (ImageView)findViewById(R.id.addgroup_confirm);
		imagePrev = (ImageView)findViewById(R.id.addgroup_image_preview);
		colorImage1 = (ImageView)findViewById(R.id.addgroup_color_1);
		colorImage2 = (ImageView)findViewById(R.id.addgroup_color_2);
		colorImage3 = (ImageView)findViewById(R.id.addgroup_color_3);
		groupTitleEdit = (EditText)findViewById(R.id.addgroup_edittext);
		groupTitle = (TextView)findViewById(R.id.addgroup_group_title);
		
		if(isSetting) {
			newGroup = dbService.getGroupById(groupId);
			
			imagePrev.setImageResource(newGroup.getGroupMapId());
			groupTitleEdit.setText(newGroup.getName());
			groupTitle.setText(newGroup.getName());
			((RelativeLayout)findViewById(R.id.addgroup_status_bar)).setBackgroundResource(R.drawable.group_setting_upbar);
			
			
			confirm.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(groupTitle.getText().toString().isEmpty()) {
						Toast.makeText(AddGroupActivity.this, "그룹 이름을 입력해 주세요", Toast.LENGTH_SHORT).show();
						return;
					}
					
					dbService.updateGroup(new Group(newGroup.getId(), groupTitle.getText().toString(), newGroup.getMembers(), groupImageColor, newGroup.getCreator()));
					AddGroupActivity.this.finish();
				}
			});
		} else {
			user = dbService.getUserById(userId);
			
			confirm.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
						
					newGroup = new Group(groupTitle.getText().toString(), user, groupImageColor);
					
					if(newGroup.getName().isEmpty()) {
						Toast.makeText(AddGroupActivity.this, "그룹 이름을 입력해 주세요", Toast.LENGTH_SHORT).show();
						return;
					}
					
					dbService.addGroupToDB(newGroup);
					newGroup.setId(dbService.getGroupId());
					dbService.addUserToGroup(user, newGroup);
					
					AddGroupActivity.this.setResult(RESULT_OK);
					AddGroupActivity.this.finish();
				}
			});
		}
		
		
		colorImage1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mHandler.sendEmptyMessage(1);
			}
		});
		colorImage2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mHandler.sendEmptyMessage(2);
			}
		});
		colorImage3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mHandler.sendEmptyMessage(3);
			}
		});
		groupTitleEdit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence title, int arg1, int arg2, int arg3) {
				Message msg = Message.obtain();
				msg.what = 10;
				msg.obj = title.toString();
				mHandler.sendMessage(msg);
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});
		groupTitleEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus) {
					Message msg = Message.obtain();
					msg.what = 10;
					msg.obj = ((EditText)v).getText().toString();
					mHandler.sendMessage(msg);
				}
			}
		});
		
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				switch(msg.what) {
				case 1:
					imagePrev.setImageResource(R.drawable.group_1);
					groupImageColor = R.drawable.group_map_image_1;
					break;
				case 2:
					imagePrev.setImageResource(R.drawable.group_2);
					groupImageColor = R.drawable.group_map_image_2;
					break;
				case 3:
					imagePrev.setImageResource(R.drawable.group_3);
					groupImageColor = R.drawable.group_map_image_3;
					break;
				case 10:
					if(msg.obj.toString().isEmpty()){
						//groupTitle.setText(R.string.addgroup_text_hint);
						groupTitle.setTextColor(0xffd1d1e2);
					}
					else{
						groupTitle.setText(msg.obj.toString());
						groupTitle.setTextColor(0xff424251);
					}
					
					break;
				}	
			}
		};
		
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		dbService.closeDb();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_group, menu);
		return true;
	}
	
}
