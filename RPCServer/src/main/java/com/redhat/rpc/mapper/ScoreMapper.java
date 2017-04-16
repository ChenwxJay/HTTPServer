package com.redhat.rpc.mapper;

import java.util.List;

import com.redhat.rpc.model.ScoreModel;

/**
 * @author littleredhat
 */
public interface ScoreMapper {

	// ��ȡ�ɼ�
	public List<ScoreModel> getScore(int userId);

	// ���óɼ�
	public boolean setScore(ScoreModel model);

	// ���³ɼ�
	public boolean updateScore(ScoreModel model);
}
