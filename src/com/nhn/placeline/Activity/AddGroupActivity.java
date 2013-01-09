package com.nhn.placeline.Activity;

import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.User;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.TextView;

public class AddGroupActivity extends Activity {
	
	private String userId;
	private User user;
	Handler mHandler;
	private ImageView confirm;
	private ImageView imagePrev;
	private ImageView colorImage1;
	private ImageView colorImage2;
	private ImageView colorImage3;
	private EditText groupTitleEdit;
	private TextView groupTitle;
	private Group newGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_group);
		
		Intent intent = this.getIntent();
		userId = intent.getCharSequenceExtra("userId").toString();
		
		confirm = (ImageView)findViewById(R.id.addgroup_confirm);
		imagePrev = (ImageView)findViewById(R.id.addgroup_image_preview);
		colorImage1 = (ImageView)findViewById(R.id.addgroup_color_1);
		colorImage2 = (ImageView)findViewById(R.id.addgroup_color_2);
		colorImage3 = (ImageView)findViewById(R.id.addgroup_color_3);
		groupTitleEdit = (EditText)findViewById(R.id.addgroup_edittext);
		groupTitle = (TextView)findViewById(R.id.addgroup_group_title);
		
		confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//디비에 업로드 작업 추가
				newGroup = new Group(groupTitle.getText().toString(), user, Integer.parseInt(imagePrev.getTag().toString()));
				Intent intent = new Intent(AddGroupActivity.this, GroupActivity.class);
				startActivity(intent);
				finish();
			}
		});
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
					imagePrev.setTag(R.drawable.group_map_image_1);
					break;
				case 2:
					imagePrev.setImageResource(R.drawable.group_2);
					imagePrev.setTag(R.drawable.group_map_image_2);
					break;
				case 3:
					imagePrev.setImageResource(R.drawable.group_3);
					imagePrev.setTag(R.drawable.group_map_image_3);
					break;
				case 10:
					Log.d("test", msg.obj.toString());
					if(msg.obj.toString().isEmpty()){
						groupTitle.setText(R.string.addgroup_text_hint);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_group, menu);
		return true;
	}
	
}
