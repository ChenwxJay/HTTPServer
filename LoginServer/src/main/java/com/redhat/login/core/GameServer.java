package com.redhat.login.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.login.server.HttpServer;

/**
 * @author littleredhat
 * 
 *         ����˹�����
 */
public class GameServer implements GameServerMBean {
	private static GameServer instance = null;
	private static Logger logger = LoggerFactory.getLogger(GameServer.class);
	private boolean shutdown = true;

	private GameServer() {
	}

	public static GameServer getInstance() {
		if (instance == null) {
			instance = new GameServer();
		}
		return instance;
	}

	/**
	 * ��ȡshutdown
	 */
	public boolean getShutdown() {
		return shutdown;
	}

	/**
	 * ����shutdown
	 */
	public void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}

	/**
	 * ����������
	 */
	public void startServer() {
		logger.info("================����������================");
		HttpServer.getInstance().start();
		shutdown = false;
		logger.info("================����������================");
	}

	/**
	 * �رշ�����
	 */
	public void shutServer() {
		logger.info("================�رշ�����================");
		HttpServer.getInstance().shut();
		shutdown = true;
		logger.info("================�������ر�================");
	}
}
