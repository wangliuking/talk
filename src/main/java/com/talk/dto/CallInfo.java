package com.talk.dto;

import java.util.Date;

/**
 *呼叫信息实体类
 */

public class CallInfo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String callId;
	private String callingId;
	private String calledId;
	private String pttId;
	private int callStatus;
	private int callType;
	private Date startTime;
	private Date endTime;
	private String endReason;
	private String filePath;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCallId() {
		return callId;
	}
	public void setCallId(String callId) {
		this.callId = callId;
	}
	public String getCallingId() {
		return callingId;
	}
	public void setCallingId(String callingId) {
		this.callingId = callingId;
	}
	public String getCalledId() {
		return calledId;
	}
	public void setCalledId(String calledId) {
		this.calledId = calledId;
	}
	public String getPttId() {
		return pttId;
	}
	public void setPttId(String pttId) {
		this.pttId = pttId;
	}
	public int getCallStatus() {
		return callStatus;
	}
	public void setCallStatus(int callStatus) {
		this.callStatus = callStatus;
	}
	public int getCallType() {
		return callType;
	}
	public void setCallType(int callType) {
		this.callType = callType;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getEndReason() {
		return endReason;
	}
	public void setEndReason(String endReason) {
		this.endReason = endReason;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "CallInfo [id=" + id + ", callId=" + callId + ", callingId=" + callingId + ", calledId=" + calledId
				+ ", pttId=" + pttId + ", callStatus=" + callStatus + ", callType=" + callType + ", startTime="
				+ startTime + ", endTime=" + endTime + ", endReason=" + endReason + ", filePath=" + filePath + "]";
	}
	

}
