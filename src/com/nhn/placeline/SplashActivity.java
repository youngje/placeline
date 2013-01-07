package com.nhn.placeline;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;

public class SplashActivity extends Activity {

	Handler mHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				SplashActivity.this.finish();
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
			}
		};
		
		mHandler.sendEmptyMessageDelayed(0, 3000);
	}
	
	
	

}
