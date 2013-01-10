package com.nhn.placeline.util;

import java.util.ArrayList;

import com.nhn.placeline.Activity.R;
import com.nhn.placeline.vo.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendsListAdapter extends ArrayAdapter<User> {
	
	private ArrayList<User> friendsList = null;
	private Context mContext;

	public FriendsListAdapter(Context context, int textViewResourceId, ArrayList<User> objects) {
		super(context, textViewResourceId, objects);
		mContext = context;
		friendsList = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(v == null) {
			LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.friend_list_partition, null);
		} 
		User friend = friendsList.get(position);
		if(friend != null) {
			ImageView thumnail = (ImageView)v.findViewById(R.id.friend_thumb);
			TextView name = (TextView)v.findViewById(R.id.friend_name);
			TextView phone = (TextView)v.findViewById(R.id.friend_phone);
			
			thumnail.setImageResource(friend.getThumnail());
			name.setText(friend.getName());
			phone.setText(friend.getPhoneNumber());
		}
		return v;
	}


}
