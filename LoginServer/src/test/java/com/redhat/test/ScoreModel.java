package com.redhat.test;

/**
 * @author littleredhat
 */
public class ScoreModel {

	// �û�id
	private int userId;

	// ��Ŀ
	private String subject;

	// �ɼ�
	private int score;

	public ScoreModel() {
	}

	public ScoreModel(int userId, String subject, int score) {
		this.userId = userId;
		this.subject = subject;
		this.score = score;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userid) {
		this.userId = userid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
