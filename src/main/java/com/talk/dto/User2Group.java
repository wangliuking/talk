package com.talk.dto;

public class User2Group {
	private int id;
	private User user;
	private Group group;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "User2Group [id=" + id + ", user=" + user + ", group=" + group + "]";
	}

	
}
