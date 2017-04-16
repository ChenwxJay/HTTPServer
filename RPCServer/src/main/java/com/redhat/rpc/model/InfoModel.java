package com.redhat.rpc.model;

/**
 * @author littleredhat
 */
public class InfoModel {

	// �û�id
	private int userId;

	// �ȼ�
	private int level;

	// ��Ϣ
	private String info;

	public InfoModel() {
	}

	public InfoModel(int userId, int level, String info) {
		this.userId = userId;
		this.level = level;
		this.info = info;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
