package com.talk.dto;

import java.util.Date;


/**
 * app上传下载处理类
 * @author 12878
 *
 */
public class App implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String appName;
	private String appVersion;
	private String content;
	private Date uploadTime;
	private String path;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "App [id=" + id + ", appName=" + appName + ", appVersion="
				+ appVersion + ", content=" + content + ", uploadTime="
				+ uploadTime + ", path=" + path + "]";
	}

	
	
}
