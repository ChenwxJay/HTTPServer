package com.redhat.login.protocol;

/**
 * @author littleredhat
 * 
 *         ����˳���
 */
public class Consts {
	public static final boolean USE_NET = false; // �Ƿ�ʹ����ҳ����
	public static final boolean USE_AES = false; // �Ƿ�ʹ��AES����

	// AES��Կ
	// ��ò�Ҫ���ı��� ���Խ���Կ���ܷ����ļ���ȡ
	public static final String AES_KEY = "maomao";

	public static final String SESSION_KEY = "key"; // sessionKey
	public static final String SESSION_GET = "cookie"; // session
	public static final String ID_GET = "id"; // id
	public static final String CONTENT_TYPE = "application/json; charset=utf-8"; // Http��������
}
