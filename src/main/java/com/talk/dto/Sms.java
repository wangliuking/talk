package com.talk.dto;

import java.util.Date;
/**
 * 短信表处理实体类
 * @author 12878
 *
 */
public class Sms implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String srcId;
	private String tarId;
	private int type;
	private String content;
	private String sendTime;
	private String callId;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSrcId() {
		return srcId;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	public String getTarId() {
		return tarId;
	}
	public void setTarId(String tarId) {
		this.tarId = tarId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCallId() {
		return callId;
	}
	public void setCallId(String callId) {
		this.callId = callId;
	}
	@Override
	public String toString() {
		return "Sms [id=" + id + ", srcId=" + srcId + ", tarId=" + tarId
				+ ", type=" + type + ", content=" + content + ", sendTime="
				+ sendTime + ", callId=" + callId + ", status=" + status + "]";
	}
	

}
