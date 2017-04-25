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

	public boolean hasScore(long userId, int subject) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			ScoreMapper scoreMapper = sqlSession.getMapper(ScoreMapper.class);
			// ִ������
			boolean result = scoreMapper.hasScore(userId, subject);
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

	// ��ȡ��Ϣ
	public List<ScoreModel> getScore(long userId) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			ScoreMapper scoreMapper = sqlSession.getMapper(ScoreMapper.class);
			// ִ������
			List<ScoreModel> scoreList = scoreMapper.getScore(userId);
			sqlSession.commit();
			// ���ؽ��
			return scoreList;
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

	// ������Ϣ
	public boolean setScore(long userId, ScoreModel score) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			ScoreMapper scoreMapper = sqlSession.getMapper(ScoreMapper.class);
			// ִ������
			boolean result = scoreMapper.setScore(userId, score);
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

	// ������Ϣ
	public boolean updateScore(long userId, ScoreModel score) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			ScoreMapper scoreMapper = sqlSession.getMapper(ScoreMapper.class);
			// ִ������
			boolean result = scoreMapper.updateScore(userId, score);
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
