package com.redhat.login.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

/**
 * @author littleredhat
 */
public interface UserMapper {

	// ��ȡ�û�id
	public long getId(String username);

	// ��ȡ�û�����
	public String getPassword(long id);

	// ����û�
	public boolean addUser(@Param("username") String username, @Param("password") String password);

	// �����û���¼ʱ��
	public boolean updateOnline(@Param("id") long id, @Param("date") Date date);

	// ��ȡ�û���¼ʱ��
	public Date getOnline(long id);

	// �����û���¼ʱ��
	public boolean updateEnable(@Param("id") long id, @Param("enable") boolean enable);

	// ��ȡ�Ƿ������¼
	public boolean getEnable(long id);
}
