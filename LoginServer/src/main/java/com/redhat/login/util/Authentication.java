package com.redhat.login.util;

import java.util.Date;

import com.redhat.login.util.Coder;
import com.redhat.login.util.Redis;

/**
 * @author littleredhat
 * 
 *         Authnentication������
 */
public class Authentication {
	private static String TOKEN_KEY = "KEY";

	/**
	 * token = md5(KEY + _ + id + _ + ʱ���)
	 */
	public static String getToken(long id) {
		StringBuilder token = new StringBuilder(TOKEN_KEY);
		token.append("_");
		token.append(id);
		token.append("_");
		token.append(new Date().getTime() / 1000);
		return Coder.encodeToMD5(token.toString());
	}

	public static void setToken(long id, String token) {
		// keyX
		String key = TOKEN_KEY + id;
		// �����ڲ�����
		// token����ʱ��Ϊ900s
		Redis.getInstance().set_time(0, token, key, "NX");
	}

	/**
	 * �ж�id��token��Ӧid�Ƿ�һ��
	 */
	public static boolean checkToken(long id, String token) {
		// keyX
		String key = TOKEN_KEY + id;
		// keyX
		String key_server = getKey(token);
		// key_server����Ϊnull
		if (key.equals(key_server)) {
			// ���ڲ�����
			// ˢ��token����ʱ��
			Redis.getInstance().set_time(0, token, key_server, "XX");
			return true;
		}
		return false;
	}

	// ����token��ȡkey
	private static String getKey(String token) {
		boolean res = Redis.getInstance().exists(0, token);
		if (res) {
			return Redis.getInstance().get(0, token);
		}
		return null;
	}
}
