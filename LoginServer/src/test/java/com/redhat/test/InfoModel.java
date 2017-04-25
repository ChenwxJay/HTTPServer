package com.redhat.test;

/**
 * @author littleredhat
 */
public class InfoModel {

	// ����
	private String name;
	// ����
	private int age;
	// ��ʦ
	private int teacher;
	// �Ա� true boy false girl
	private boolean sex;

	public InfoModel() {
	}

	public InfoModel(String name, int age, int teacher, boolean sex) {
		this.name = name;
		this.age = age;
		this.teacher = teacher;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getTeacher() {
		return teacher;
	}

	public void setTeacher(int teacher) {
		this.teacher = teacher;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}
}
