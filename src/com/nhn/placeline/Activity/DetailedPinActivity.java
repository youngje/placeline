package com.nhn.placeline.Activity;

import java.sql.Wrapper;
import java.util.ArrayList;

import com.nhn.placeline.Activity.R;
import com.nhn.placeline.Activity.R.id;
import com.nhn.placeline.constants.Constants;
import com.nhn.placeline.util.GroupAdapter;
import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.PinReply;
import com.nhn.android.mapviewer.NMapViewer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailedPinActivity extends Activity {

	private ArrayList<PinReply> replyList;
	private String userid = "oskar";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed_pin);
		
		replyList = new ArrayList<PinReply>();

		LinearLayout replyItem = (LinearLayout) findViewById(R.id.itemReplyList);
		replyItem.setVisibility(1);
		
		
		for(int i=0; i<3; i++){
			LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = mInflater.inflate(R.layout.item_replylist, null);
			TextView text = (TextView) view.findViewById(id.name);
			text.setText("ㅋㅋㅋㅋㅋ : "+i);
			
			replyItem.addView(view);
		}
	}
}
