package com.redhat.rpc.mapper;

import java.util.List;

import com.redhat.rpc.model.MailModel;

/**
 * @author littleredhat
 */
public interface MailMapper {

	// ��ȡ�ʼ�
	public List<MailModel> getMail(int userId);

	// �����ʼ�
	public boolean sendMail(MailModel model);
}
