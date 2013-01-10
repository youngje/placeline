package com.nhn.placeline.vo;

import java.util.ArrayList;

import com.nhn.placeline.Activity.R;

public class Group {
	private int id;
	private String name;
	private ArrayList<User>	members;
	private int groupMapId;
	private User creator;
	
	public Group(int id, String name, ArrayList<User> members, int groupMapId,
			User creator) {
		super();
		this.id = id;
		this.name = name;
		this.members = members;
		this.groupMapId = groupMapId;
		this.creator = creator;
	}

	public Group(String name, int groupMapId) {
		super();
		this.name = name;
		this.members = new ArrayList<User>();
		members.add(creator);
		this.groupMapId = groupMapId;
		
		//나중에 지워야 하는 부분
		//members.add(new User("윤영제", "016-9611-7061", R.drawable.user_4));
		//members.add(new User("백준선", "016-9611-7061", R.drawable.user_3));
	}
	
	public Group(String name, User creator, int groupMapId ) {
		super();
		this.name = name;
		this.members = new ArrayList<User>();
		members.add(creator);
		this.creator = creator;
		this.groupMapId = groupMapId;
	}

	public Group() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<User> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}

	public int getGroupMapId() {
		return groupMapId;
	}

	public void setGroupMapId(int groupMapId) {
		this.groupMapId = groupMapId;
	}
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", members=" + members.size()
				+ "]";
	}
	
	
	
	
}
