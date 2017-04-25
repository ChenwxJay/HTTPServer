package com.redhat.login.manager;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.login.mapper.UserMapper;
import com.redhat.login.util.SqlSessionFactoryUtil;

/**
 * @author littleredhat
 */
public class UserManager {
	private static Logger logger = LoggerFactory.getLogger(UserManager.class);
	private static UserManager instance;

	public static UserManager getInstance() {
		if (instance == null) {
			// ˫���������
			synchronized (UserManager.class) {
				if (instance == null) {
					instance = new UserManager();
				}
			}
		}
		return instance;
	}

	// ��ȡ�û�Id
	public long getId(String username) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// ִ������
			long result = userMapper.getId(username);
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
		return -1;
	}

	// ��ȡ�û�����
	public String getPassword(long id) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// ִ������
			String result = userMapper.getPassword(id);
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
		return null;
	}

	// ����û�
	public boolean addUser(String username, String password) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// ִ������
			boolean result = userMapper.addUser(username, password);
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

	// ��������½ʱ��
	public boolean updateOnline(long id, Date date) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// ִ������
			boolean result = userMapper.updateOnline(id, date);
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

	// ��ȡ����½ʱ��
	public Date getOnline(long id) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// ִ������
			Date result = userMapper.getOnline(id);
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
		return null;
	}

	// �����Ƿ������¼
	public boolean updateEnable(long id, boolean enable) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// ִ������
			boolean result = userMapper.updateEnable(id, enable);
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

	// ��ȡ�Ƿ������¼
	public boolean getEnable(long id) {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			// ��ȡӳ��
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// ִ������
			boolean result = userMapper.getEnable(id);
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
