package com.redhat.test;

/**
 * @author littleredhat
 */
public class InfoReq {

	// �û�id
	private int userId;

	public InfoReq() {
	}

	public InfoReq(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
