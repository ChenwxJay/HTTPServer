package com.redhat.login.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

/**
 * @author littleredhat
 */
public interface UserMapper {

	// ��ȡ�û�id
	public int getId(String username);

	// ��ȡ�û�����
	public String getPassword(int id);

	// ����û�
	public boolean addUser(@Param("username") String username, @Param("password") String password);

	// �����û���¼ʱ��
	public boolean updateOnline(@Param("id") int id, @Param("date") Date date);

	// ��ȡ�û���¼ʱ��
	public Date getOnline(int id);

	// �����û���¼ʱ��
	public boolean updateEnable(@Param("id") int id, @Param("enable") boolean enable);

	// ��ȡ�Ƿ������¼
	public boolean getEnable(int id);
}
