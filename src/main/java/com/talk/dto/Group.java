package com.talk.dto;
/**
 * 组表实体类
 * @author 12878
 *
 */
public class Group implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String groupId;
	private String groupName;
	private int type;
	private int pttSilentTime;
	private int callTime;
	private int pttOnTime;
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPttSilentTime() {
		return pttSilentTime;
	}
	public void setPttSilentTime(int pttSilentTime) {
		this.pttSilentTime = pttSilentTime;
	}
	public int getCallTime() {
		return callTime;
	}
	public void setCallTime(int callTime) {
		this.callTime = callTime;
	}
	public int getPttOnTime() {
		return pttOnTime;
	}
	public void setPttOnTime(int pttOnTime) {
		this.pttOnTime = pttOnTime;
	}
	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName
				+ ", type=" + type + ", pttSilentTime=" + pttSilentTime
				+ ", callTime=" + callTime + ", pttOnTime=" + pttOnTime + "]";
	}
	

	

}
