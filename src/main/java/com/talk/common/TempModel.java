package com.talk.common;

public class TempModel {
	private String temp;
	private String tempGroup;
	private String tempBureaudirection;
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getTempGroup() {
		return tempGroup;
	}
	public void setTempGroup(String tempGroup) {
		this.tempGroup = tempGroup;
	}
	public String getTempBureaudirection() {
		return tempBureaudirection;
	}
	public void setTempBureaudirection(String tempBureaudirection) {
		this.tempBureaudirection = tempBureaudirection;
	}
	@Override
	public String toString() {
		return "TempModel [temp=" + temp + ", tempGroup=" + tempGroup
				+ ", tempBureaudirection=" + tempBureaudirection + "]";
	}
	


}
