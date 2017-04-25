package com.redhat.login.core;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.login.util.Redis;
import com.redhat.login.core.GameServer;
import com.redhat.login.util.Config;
import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * @author littleredhat
 * 
 *         ����˳�ʼ��
 */
public class GameInit {
	private static Logger logger = LoggerFactory.getLogger(GameInit.class);

	/**
	 * Main
	 */
	public static void main(String[] args) throws Exception {
		// ����Redis
		Redis.getInstance().initData();

		if (Config.USE_NET) { // ��ҳ����
			JMXOpen();
		} else { // ����
			GameServer.getInstance().startServer();
		}
	}

	// JMX ��ҳ���� 127.0.0.1:9900
	private static void JMXOpen() throws Exception {
		// ����server
		MBeanServer server = MBeanServerFactory.createMBeanServer();

		ObjectName gameServerName = new ObjectName("GameInit:name=GameServer");
		// ע��MBean
		server.registerMBean(GameServer.getInstance(), gameServerName);

		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		adapter.setPort(Config.LoginNetPort);
		ObjectName adapterName = new ObjectName("GameInit:name=HtmlAdapter,port=" + Config.LoginNetPort);
		// ע��MBean
		server.registerMBean(adapter, adapterName);

		// ����adapter
		adapter.start();

		logger.info("GameInit at {}:{}", Config.LoginIp, Config.LoginNetPort);
	}
}
