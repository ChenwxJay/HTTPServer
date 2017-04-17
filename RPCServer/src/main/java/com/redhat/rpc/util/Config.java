package com.redhat.rpc.util;

import com.redhat.rpc.util.Property;

/**
 * @author littleredhat
 * 
 *         Config������
 */
public class Config {
	// Login��ҳ�����˿�
	public static final int LoginNetPort = Integer.parseInt(Property.getData("loginNetPort"));
	// Login���ض˿�
	public static final int LoginLocalPort = Integer.parseInt(Property.getData("loginLocalPort"));
	// Login ip
	public static final String LoginIp = Property.getData("loginIp");

	// Gate��ҳ�����˿�
	public static final int GateNetPort = Integer.parseInt(Property.getData("gateNetPort"));
	// Gate���ض˿�
	public static final int GateLocalPort = Integer.parseInt(Property.getData("gateLocalPort"));
	// Gate ip
	public static final String GateIp = Property.getData("gateIp");

	// Node��ҳ�����˿�
	public static final int NodeNetPort = Integer.parseInt(Property.getData("nodeNetPort"));
	// Node���ض˿�
	public static final int NodeLocalPort = Integer.parseInt(Property.getData("nodeLocalPort"));
	// Node ip
	public static final String NodeIp = Property.getData("nodeIp");

	// Redis����
	public static final String RedisIp = Property.getData("redisIp");
	public static final int RedisPort = Integer.parseInt(Property.getData("redisPort"));;
	public static final String RedisPassword = Property.getData("redisPassword"); // ��Ȩ����
	public static final int RedisTimeOut = Integer.parseInt(Property.getData("redisTimeOut"));; // ��ʱʱ��
	public static final int RedisMaxActive = Integer.parseInt(Property.getData("redisMaxActive"));; // �������
	public static final int RedisMaxIdle = Integer.parseInt(Property.getData("redisMaxIdle"));; // ����������
	public static final int RedisMaxWait = Integer.parseInt(Property.getData("redisMaxWait"));; // ���ȴ�ʱ��
	public static boolean RedisTestOnBorrow = Boolean.getBoolean(Property.getData("redisTestOnBorrow")); // ��ȡ����ʱ����м���
	public static boolean RedisTestOnReturn = Boolean.getBoolean(Property.getData("redisTestOnReturn")); // �黹����ʱ����м���
	public static boolean RedisTestWhileIdle = Boolean.getBoolean(Property.getData("redisTestWhileIdle")); // ����ʱ����м���

	// �Ƿ�ʹ����ҳ����
	public static boolean UseNet = Boolean.getBoolean(Property.getData("useNet"));
	// �Ƿ�ʹ��AES����
	public static boolean UseAES = Boolean.getBoolean(Property.getData("useAES"));
}
