package com.redhat.rpc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.redhat.rpc.model.ScoreModel;

/**
 * @author littleredhat
 */
public interface ScoreMapper {

	// �Ƿ���ڳɼ�
	public boolean hasScore(@Param("userId") long userId, @Param("subject") int subject);

	// ��ȡ�ɼ�
	public List<ScoreModel> getScore(long userId);

	// ���óɼ�
	public boolean setScore(@Param("userId") long userId, @Param("score") ScoreModel score);

	// ���³ɼ�
	public boolean updateScore(@Param("userId") long userId, @Param("score") ScoreModel score);
}
