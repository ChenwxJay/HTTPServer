package com.redhat.rpc.mapper;

import com.redhat.rpc.model.InfoModel;

/**
 * @author littleredhat
 */
public interface InfoMapper {

	// ��ȡ�û���Ϣ
	public InfoModel getInfo(int userId);

	// �����û���Ϣ
	public boolean setInfo(InfoModel model);
}
