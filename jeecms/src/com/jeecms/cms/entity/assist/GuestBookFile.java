package com.jeecms.cms.entity.assist;

import java.io.Serializable;

public class GuestBookFile implements Serializable {

	private int id;
	private String attachmentPaths;
	private String attachmentNames;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttachmentPaths() {
		return attachmentPaths;
	}
	public void setAttachmentPaths(String attachmentPaths) {
		this.attachmentPaths = attachmentPaths;
	}
	public String getAttachmentNames() {
		return attachmentNames;
	}
	public void setAttachmentNames(String attachmentNames) {
		this.attachmentNames = attachmentNames;
	}
	
	
	
}
