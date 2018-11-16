package com.talk.dto;
/**
 * 局向和组关联实体类
 * @author 12878
 *
 */
public class Group2Bd {
	private int id;
	private Bureaudirection bureaudirection;
	private Group group;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Bureaudirection getBureaudirection() {
		return bureaudirection;
	}
	public void setBureaudirection(Bureaudirection bureaudirection) {
		this.bureaudirection = bureaudirection;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	@Override
	public String toString() {
		return "Group2Bd [id=" + id + ", bureaudirection=" + bureaudirection
				+ ", group=" + group + "]";
	}
	

	
}
