package com.nhn.placeline.util;

import java.util.ArrayList;

import com.nhn.placeline.Activity.R;
import com.nhn.placeline.constants.Constants;
import com.nhn.placeline.vo.Group;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GroupAdapter extends BaseAdapter {

	private Context mContext;
	public ArrayList<Group> groups;
	
	public GroupAdapter(Context c, ArrayList<Group> groups) {
		mContext = c;
		this.groups = groups;
		groups.add(new Group("AddGroup", " ", R.drawable.groupmapadd));
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
		
		RelativeLayout.LayoutParams textViewParam = new RelativeLayout.LayoutParams(165, 150);
		textViewParam.leftMargin = 50;
		textViewParam.topMargin = 270;
		
		TextView textView = new TextView(mContext);
		textView.setText(groups.get(position).getName());
		textView.setTextSize(23);
		textView.setTextColor(0xff424251);
		

		relativeLayout.addView(imageView, imageViewParam);
		relativeLayout.addView(textView, textViewParam);
		
		return relativeLayout;
	}
}
