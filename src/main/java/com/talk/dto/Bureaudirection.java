package com.talk.dto;

public class Bureaudirection implements java.io.Serializable{
	/**
	 * 局向实体类
	 */
	private static final long serialVersionUID = 1L;
	private String bdId;
	private String address;
	private String csPort;
	private String voicePort;
	private String bdName;
	
	public String getBdId() {
		return bdId;
	}

	public void setBdId(String bdId) {
		this.bdId = bdId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCsPort() {
		return csPort;
	}

	public void setCsPort(String csPort) {
		this.csPort = csPort;
	}

	public String getVoicePort() {
		return voicePort;
	}

	public void setVoicePort(String voicePort) {
		this.voicePort = voicePort;
	}

	public String getBdName() {
		return bdName;
	}

	public void setBdName(String bdName) {
		this.bdName = bdName;
	}

	@Override
	public String toString() {
		return "Bureaudirection [bdId=" + bdId + ", address=" + address
				+ ", csPort=" + csPort + ", voicePort=" + voicePort
				+ ", bdName=" + bdName + "]";
	}
	
}
