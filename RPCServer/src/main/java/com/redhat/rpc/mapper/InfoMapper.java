package com.redhat.rpc.mapper;

import org.apache.ibatis.annotations.Param;

import com.redhat.rpc.model.InfoModel;

/**
 * @author littleredhat
 */
public interface InfoMapper {

	// ��ȡ��Ϣ
	public InfoModel getInfo(long id);

	// ������Ϣ
	public boolean updateInfo(@Param("id") long id, @Param("info") InfoModel info);
}
