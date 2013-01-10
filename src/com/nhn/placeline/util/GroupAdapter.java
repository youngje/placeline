package com.nhn.placeline.util;

import java.util.ArrayList;

import com.nhn.placeline.Activity.AddGroupActivity;
import com.nhn.placeline.Activity.GroupActivity;
import com.nhn.placeline.Activity.R;
import com.nhn.placeline.constants.Constants;
import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.User;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class GroupAdapter extends BaseAdapter {

	private Context mContext;
	public ArrayList<Group> groups;
	
	public GroupAdapter(Context c, ArrayList<Group> groups) {
		mContext = c;
		this.groups = groups;
		groups.add(new Group(" ", new User("그룹추가", " ", 0), R.drawable.groupmapadd));
	}
	
	@Override
	public int getCount() {
		return groups.size();
	}

	@Override
	public Object getItem(int position) {
		return groups.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View groupMapImage, ViewGroup parent) {
		RelativeLayout relativeLayout = new RelativeLayout(mContext);
		
		RelativeLayout.LayoutParams imageViewParam = new RelativeLayout.LayoutParams(165, 400);
		imageViewParam.leftMargin = 30;

		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(groups.get(position).getGroupMapId());
		imageView.setLayoutParams(new GridView.LayoutParams(165, 400));
		imageView.setId(Constants.GROUPMAP_IMAGE_ID);
		imageView.setAdjustViewBounds(true);
		
		RelativeLayout.LayoutParams settingButtonParam = new RelativeLayout.LayoutParams(50, 50);
		settingButtonParam.leftMargin = 30;
		settingButtonParam.topMargin = 220;
		
		ImageView settingButton = new ImageView(mContext);
		settingButton.setId(Constants.SETTING_BUTTON);
		settingButton.setTag(R.layout.splash, groups.get(position).getId());
		settingButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), AddGroupActivity.class);
				intent.putExtra("isSetting", true);
				intent.putExtra("groupId", Integer.parseInt(v.getTag(R.layout.splash)+""));
				v.getContext().startActivity(intent);
			}
		});
		
		RelativeLayout.LayoutParams textViewParam = new RelativeLayout.LayoutParams(153, 142);
		textViewParam.leftMargin = 42;
		textViewParam.topMargin = 270;
		
		TextView textView = new TextView(mContext);
		textView.setText(groups.get(position).getName());
		textView.setTextSize(23);
		textView.setTextColor(0xff424251);

		relativeLayout.addView(imageView, imageViewParam);
		relativeLayout.addView(settingButton, settingButtonParam);
		relativeLayout.addView(textView, textViewParam);
		
		return relativeLayout;
	}
}
