package com.redhat.rpc.manager;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.rpc.mapper.InfoMapper;
import com.redhat.rpc.model.InfoModel;
import com.redhat.rpc.util.SqlSessionFactoryUtil;

/**
 * @author littleredhat
 */
public class InfoManager {
	private static Logger logger = LoggerFactory.getLogger(InfoManager.class);
	private static InfoManager instance;

	public static InfoManager getInstance() {
		if (instance == null) {
			// ˫���������
			synchronized (InfoManager.class) {
				if (instance == null) {
					instance = new InfoManager();
				}
			}
		}
		return instance;
	}

	// ��ȡ�û���Ϣ
	public InfoModel getInfo(int userId) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			InfoMapper infoMapper = sqlSession.getMapper(InfoMapper.class);
			// ִ������
			InfoModel model = infoMapper.getInfo(userId);
			sqlSession.commit();
			// ���ؽ��
			return model;
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

	// �����û���Ϣ
	public boolean setInfo(InfoModel model) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			InfoMapper infoMapper = sqlSession.getMapper(InfoMapper.class);
			// ִ������
			boolean result = infoMapper.setInfo(model);
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
