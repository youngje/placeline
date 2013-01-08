package com.nhn.placeline.util;

import java.util.ArrayList;

import com.nhn.placeline.Activity.R;
import com.nhn.placeline.vo.Group;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GroupAdapter extends BaseAdapter {

	private Context mContext;
	public ArrayList<Group> groups;
	/*public Integer[] mThumbIds = {
			R.drawable.group_map_image_1, 
			R.drawable.group_map_image_2
	};*/
	
	public GroupAdapter(Context c, ArrayList<Group> groups) {
		mContext = c;
		this.groups = groups;
		groups.add(new Group("AddGroup", "그룹 추가", R.drawable.groupmapadd));
		/*for(Group group : groups){
			group.setPosition(position++);
		}*/
	}
	
	@Override
	public int getCount() {
		//return mThumbIds.length;
		return groups.size();
	}

	@Override
	public Object getItem(int position) {
		//return mThumbIds[position];
		return groups.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View groupMapImage, ViewGroup parent) {
		RelativeLayout relativeView = new RelativeLayout(mContext);
		
		
		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(groups.get(position).getGroupMapId());
		imageView.setLayoutParams(new GridView.LayoutParams(165, 400));
		
		return imageView;
	}
	/*public View getView(int position, View groupMapImage, ViewGroup parent) {
		ImageView imageView = new ImageView(mContext);
		//imageView.setImageResource(mThumbIds[position]);
		imageView.setImageResource(groups.get(position).getGroupMapId());
		imageView.setLayoutParams(new GridView.LayoutParams(165, 400));
		
		return imageView;
	}*/

}
