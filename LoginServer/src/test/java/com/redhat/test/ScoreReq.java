package com.redhat.test;

/**
 * @author littleredhat
 */
public class ScoreReq {

	// �û�id
	private int userId;

	public ScoreReq() {
	}

	public ScoreReq(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
