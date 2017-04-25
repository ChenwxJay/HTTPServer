package com.redhat.rpc.core;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.rpc.core.GameServer;
import com.redhat.rpc.server.RPCServer;
import com.redhat.rpc.util.Config;
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
		// RPCServer����
		RPCServer.getInstance().initData();

		if (Config.USE_AES) { // ��ҳ����
			JMXOpen();
		} else { // ����
			GameServer.getInstance().startServer();
		}
	}

	// JMX ��ҳ���� 127.0.0.1:9920
	private static void JMXOpen() throws Exception {
		// ����server
		MBeanServer server = MBeanServerFactory.createMBeanServer();

		ObjectName gameServerName = new ObjectName("GameInit:name=GameServer");
		// ע��MBean
		server.registerMBean(GameServer.getInstance(), gameServerName);

		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		adapter.setPort(Config.NodeNetPort);
		ObjectName adapterName = new ObjectName("GameInit:name=HtmlAdapter,port=" + Config.NodeNetPort);
		// ע��MBean
		server.registerMBean(adapter, adapterName);

		// ����adapter
		adapter.start();

		logger.info("GameInit at {}:{}", Config.NodeIp, Config.NodeNetPort);
	}
}
