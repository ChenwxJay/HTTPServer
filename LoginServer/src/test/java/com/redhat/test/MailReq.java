package com.redhat.test;

/**
 * @author littleredhat
 */
public class MailReq {

	// �û�id
	private int userId;

	public MailReq() {
	}

	public MailReq(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
