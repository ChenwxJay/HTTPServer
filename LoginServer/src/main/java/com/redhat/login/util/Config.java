package com.redhat.login.util;

import com.redhat.login.util.Property;

/**
 * @author littleredhat
 * 
 *         Config������
 */
public class Config {
	// Login��ҳ�����˿�
	public static final int LoginNetPort = Integer.parseInt(Property.getData("LoginNetPort"));
	// Login���ض˿�
	public static final int LoginLocalPort = Integer.parseInt(Property.getData("LoginLocalPort"));
	// Login ip
	public static final String LoginIp = Property.getData("LoginIp");

	// Gate��ҳ�����˿�
	public static final int GateNetPort = Integer.parseInt(Property.getData("GateNetPort"));
	// Gate���ض˿�
	public static final int GateLocalPort = Integer.parseInt(Property.getData("GateLocalPort"));
	// Gate ip
	public static final String GateIp = Property.getData("GateIp");

	// Node��ҳ�����˿�
	public static final int NodeNetPort = Integer.parseInt(Property.getData("NodeNetPort"));
	// Node���ض˿�
	public static final int NodeLocalPort = Integer.parseInt(Property.getData("NodeLocalPort"));
	// Node ip
	public static final String NodeIp = Property.getData("NodeIp");

	// Redis����
	public static final String RedisIp = Property.getData("RedisIp");
	public static final int RedisPort = Integer.parseInt(Property.getData("RedisPort"));;
	public static final String RedisPassword = Property.getData("RedisPassword"); // ��Ȩ����
	public static final int RedisTimeOut = Integer.parseInt(Property.getData("RedisTimeOut"));; // ��ʱʱ��
	public static final int RedisMaxActive = Integer.parseInt(Property.getData("RedisMaxActive"));; // �������
	public static final int RedisMaxIdle = Integer.parseInt(Property.getData("RedisMaxIdle"));; // ����������
	public static final int RedisMaxWait = Integer.parseInt(Property.getData("RedisMaxWait"));; // ���ȴ�ʱ��
	public static final boolean RedisTestOnBorrow = Boolean.getBoolean(Property.getData("RedisTestOnBorrow")); // ��ȡ����ʱ����м���
	public static final boolean RedisTestOnReturn = Boolean.getBoolean(Property.getData("RedisTestOnReturn")); // �黹����ʱ����м���
	public static final boolean RedisTestWhileIdle = Boolean.getBoolean(Property.getData("RedisTestWhileIdle")); // ����ʱ����м���

	// �Ƿ�ʹ����ҳ����
	public static final boolean USE_NET = Boolean.getBoolean(Property.getData("USE_NET"));
	// �Ƿ�ʹ��AES����
	public static final boolean USE_AES = Boolean.getBoolean(Property.getData("USE_AES"));
	// AES��Կ
	// ��ò�Ҫ���ı��� ���Խ���Կ���ܷ����ļ���ȡ
	public static final String AES_KEY = Property.getData("AES_KEY");
}
