package com.talk.dto;

public class System implements java.io.Serializable{

	/**
	 * 系统参数实体类
	 * @author 12878
	 */
	private static final long serialVersionUID = 1L;
	private String callTime;
	private String pttOnTime;
	private String pttSilentTime;
	private String gpsReportInterval;
	private String appHeartInterval;
	private String audioHeartInterval;
	private String tcpListenPort;
	private String appVoicePort;
	private String bdId;
	private String bdListenPort;
	private String bdVoicePort;
	private String gpsServerPort;
	private String dbSynPort;
	private String g729RecPath;
	private String wavRecPath;
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	public String getPttOnTime() {
		return pttOnTime;
	}
	public void setPttOnTime(String pttOnTime) {
		this.pttOnTime = pttOnTime;
	}
	public String getPttSilentTime() {
		return pttSilentTime;
	}
	public void setPttSilentTime(String pttSilentTime) {
		this.pttSilentTime = pttSilentTime;
	}
	public String getGpsReportInterval() {
		return gpsReportInterval;
	}
	public void setGpsReportInterval(String gpsReportInterval) {
		this.gpsReportInterval = gpsReportInterval;
	}
	public String getAppHeartInterval() {
		return appHeartInterval;
	}
	public void setAppHeartInterval(String appHeartInterval) {
		this.appHeartInterval = appHeartInterval;
	}
	public String getAudioHeartInterval() {
		return audioHeartInterval;
	}
	public void setAudioHeartInterval(String audioHeartInterval) {
		this.audioHeartInterval = audioHeartInterval;
	}
	public String getTcpListenPort() {
		return tcpListenPort;
	}
	public void setTcpListenPort(String tcpListenPort) {
		this.tcpListenPort = tcpListenPort;
	}
	public String getAppVoicePort() {
		return appVoicePort;
	}
	public void setAppVoicePort(String appVoicePort) {
		this.appVoicePort = appVoicePort;
	}
	public String getBdId() {
		return bdId;
	}
	public void setBdId(String bdId) {
		this.bdId = bdId;
	}
	public String getBdListenPort() {
		return bdListenPort;
	}
	public void setBdListenPort(String bdListenPort) {
		this.bdListenPort = bdListenPort;
	}
	public String getBdVoicePort() {
		return bdVoicePort;
	}
	public void setBdVoicePort(String bdVoicePort) {
		this.bdVoicePort = bdVoicePort;
	}
	public String getGpsServerPort() {
		return gpsServerPort;
	}
	public void setGpsServerPort(String gpsServerPort) {
		this.gpsServerPort = gpsServerPort;
	}
	public String getDbSynPort() {
		return dbSynPort;
	}
	public void setDbSynPort(String dbSynPort) {
		this.dbSynPort = dbSynPort;
	}
	public String getG729RecPath() {
		return g729RecPath;
	}
	public void setG729RecPath(String g729RecPath) {
		this.g729RecPath = g729RecPath;
	}
	public String getWavRecPath() {
		return wavRecPath;
	}
	public void setWavRecPath(String wavRecPath) {
		this.wavRecPath = wavRecPath;
	}
	@Override
	public String toString() {
		return "System [callTime=" + callTime + ", pttOnTime=" + pttOnTime
				+ ", pttSilentTime=" + pttSilentTime + ", gpsReportInterval="
				+ gpsReportInterval + ", appHeartInterval=" + appHeartInterval
				+ ", audioHeartInterval=" + audioHeartInterval
				+ ", tcpListenPort=" + tcpListenPort + ", appVoicePort="
				+ appVoicePort + ", bdId=" + bdId + ", bdListenPort="
				+ bdListenPort + ", bdVoicePort=" + bdVoicePort
				+ ", gpsServerPort=" + gpsServerPort + ", dbSynPort="
				+ dbSynPort + ", g729RecPath=" + g729RecPath + ", wavRecPath="
				+ wavRecPath + "]";
	}
	
	
	
}
