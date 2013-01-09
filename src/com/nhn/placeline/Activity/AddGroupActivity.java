package com.nhn.placeline.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class AddGroupActivity extends Activity {
	
	Handler mHandler;
	private ImageView imagePrev;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_group);
		
		imagePrev = (ImageView)findViewById(R.id.addgroup_image_preview);
		
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				//imagePrev.
				//startActivity(new Intent(SplashActivity.this, GroupActivity.class));
			}
		};
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_group, menu);
		return true;
	}

	public void changeColor(View v) {
		int color = v.getId();
		switch(color) {
		case R.drawable.group_color_1:
			
		}
		
	}
}
