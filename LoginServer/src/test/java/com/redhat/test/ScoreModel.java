package com.redhat.test;

import java.util.Date;

/**
 * @author littleredhat
 */
public class ScoreModel {

	// ��Ŀ
	public int subject;
	// �ɼ�
	public int score;
	// ����
	public Date date;

	public ScoreModel() {
	}

	public ScoreModel(int subject, int score, Date date) {
		this.subject = subject;
		this.score = score;
		this.date = date;
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
