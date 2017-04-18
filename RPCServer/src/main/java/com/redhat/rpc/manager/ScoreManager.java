package com.redhat.rpc.manager;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.rpc.mapper.ScoreMapper;
import com.redhat.rpc.model.ScoreModel;
import com.redhat.rpc.util.SqlSessionFactoryUtil;

/**
 * @author littleredhat
 */
public class ScoreManager {
	private static Logger logger = LoggerFactory.getLogger(ScoreManager.class);
	private static ScoreManager instance;

	public static ScoreManager getInstance() {
		if (instance == null) {
			// ˫���������
			synchronized (ScoreManager.class) {
				if (instance == null) {
					instance = new ScoreManager();
				}
			}
		}
		return instance;
	}

	// ��ȡ�ɼ�
	public List<ScoreModel> getScore(int userId) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			ScoreMapper scoreMapper = sqlSession.getMapper(ScoreMapper.class);
			// ִ������
			List<ScoreModel> modelList = scoreMapper.getScore(userId);
			sqlSession.commit();
			// ���ؽ��
			return modelList;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			sqlSession.rollback();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return null;
	}

	// ���óɼ�
	public boolean setScore(ScoreModel model) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			ScoreMapper scoreMapper = sqlSession.getMapper(ScoreMapper.class);
			// ִ������
			boolean result = scoreMapper.setScore(model);
			sqlSession.commit();
			// ���ؽ��
			return result;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			sqlSession.rollback();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return false;
	}

	// ���³ɼ�
	public boolean updateScore(ScoreModel model) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			ScoreMapper scoreMapper = sqlSession.getMapper(ScoreMapper.class);
			// ִ������
			boolean result = scoreMapper.updateScore(model);
			sqlSession.commit();
			// ���ؽ��
			return result;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			sqlSession.rollback();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return false;
	}
}
