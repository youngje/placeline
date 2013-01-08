package com.nhn.placeline.vo;

import java.util.ArrayList;

public class Group {
	private String id;
	private String name;
	private ArrayList<User>	members;
	private int groupMapId;
	
	public Group(String id, String name, int groupMapId) {
		super();
		this.id = id;
		this.name = name;
		this.members = new ArrayList<User>();
		this.groupMapId = groupMapId;
		//나중에 지워야 하는 부분
		members.add(new User("oskar", "윤영제", "016-9611-7061"));
		members.add(new User("sdf", "백준선", "016-9611-7061"));
		
	}

	public Group() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", members=" + members.size()
				+ "]";
	}
	
	
	
	
}
