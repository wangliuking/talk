package com.talk.dto;

public class GpsUser implements java.io.Serializable{
	/**
	 * gps用户实体类
	 */
	private static final long serialVersionUID = 1L;
	private String gpsUserId;
	private String gpsUserPassword;
	
	public String getGpsUserId() {
		return gpsUserId;
	}

	public void setGpsUserId(String gpsUserId) {
		this.gpsUserId = gpsUserId;
	}

	public String getGpsUserPassword() {
		return gpsUserPassword;
	}

	public void setGpsUserPassword(String gpsUserPassword) {
		this.gpsUserPassword = gpsUserPassword;
	}

	@Override
	public String toString() {
		return "GpsUser [gpsUserId=" + gpsUserId + ", gpsUserPassword="
				+ gpsUserPassword + "]";
	}
	
}
