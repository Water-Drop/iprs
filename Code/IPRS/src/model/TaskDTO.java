/*
 * Author	: Zhou Cheng
 * Project	: iprs
 * Filename	: TaskDTO.java
 * Date		: May 4, 2014
 */
package model;

public class TaskDTO {
	private String Tid;
	private String Title;
	private int isEdit;
	private String DownUrl;
	
	public String getTid() {
		return Tid;
	}
	public void setTid(String tid) {
		Tid = tid;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public int getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(int isEdit) {
		this.isEdit = isEdit;
	}
	public String getDownUrl() {
		return DownUrl;
	}
	public void setDownUrl(String downUrl) {
		DownUrl = downUrl;
	}
}
