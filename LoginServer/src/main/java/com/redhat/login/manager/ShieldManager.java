package com.redhat.login.manager;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.login.mapper.ShieldMapper;
import com.redhat.login.util.SqlSessionFactoryUtil;

/**
 * @author littleredhat
 */
public class ShieldManager {
	private static Logger logger = LoggerFactory.getLogger(ShieldManager.class);
	private static ShieldManager instance;

	public static ShieldManager getInstance() {
		if (instance == null) {
			instance = new ShieldManager();
		}
		return instance;
	}

	// �Ƿ�������δ�
	public boolean hasShield(String username) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			ShieldMapper shieldMapper = sqlSession.getMapper(ShieldMapper.class);
			// ִ������
			boolean result = shieldMapper.hasShield(username);
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
		return true;
	}
}
