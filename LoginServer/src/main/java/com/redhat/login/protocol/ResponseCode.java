package com.redhat.login.protocol;

/**
 * @author littleredhat
 * 
 *         ��Ӧ��
 */
public class ResponseCode {
	public static final short SUCCESS = 1000; // �ɹ�

	public static final short COMMON_ERR = 5000; // Ĭ�ϴ���
	public static final short SERVER_ERR = 5001; // ����������
	public static final short AUTH_ERR = 5002; // ��֤����
	public static final short PROTO_ERR = 5003; // Э�����

	public static final short Login_Username_Err = 10001; // �û���������
	public static final short Login_Password_Err = 10002; // �û��������
	public static final short Login_Black_List = 10003; // �û����������

	public static final short Register_Username_Err = 10004; // �û����Ѵ���
	public static final short Register_Username_Ill = 10005; // �û������Ϸ�
}
