package com.redhat.login.core;

/**
 * @author littleredhat
 * 
 *         MBean ������ΪJMX�ӿ�
 */
public interface GameServerMBean {

	// ����shutdown
	public boolean getShutdown();

	// ��ȡshutdown
	public void setShutdown(boolean shutdown);

	// ����������
	public void startServer();

	// �رշ�����
	public void shutServer();
}
