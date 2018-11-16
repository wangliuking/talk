package com.talk.dto;

/**
 * user实体类
 * @author 12878
 * @date 2017-4-16
 *
 */
public class User{
	private String userId;
	private String userName;
	private String password;
	private String authenticateCode;
	private String priority;
	private String type;
	private String loginTime;
	private String loginStatus;
	private String scanEn;
	private String browserType;
	private String level;
	private String power;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthenticateCode() {
		return authenticateCode;
	}
	public void setAuthenticateCode(String authenticateCode) {
		this.authenticateCode = authenticateCode;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getScanEn() {
		return scanEn;
	}
	public void setScanEn(String scanEn) {
		this.scanEn = scanEn;
	}
	public String getBrowserType() {
		return browserType;
	}
	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", authenticateCode="
				+ authenticateCode + ", priority=" + priority + ", type="
				+ type + ", loginTime=" + loginTime + ", loginStatus="
				+ loginStatus + ", scanEn=" + scanEn + ", browserType="
				+ browserType + ", level=" + level + ", power=" + power + "]";
	}
	
}
