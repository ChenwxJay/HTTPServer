package com.redhat.login.protocol;

/**
 * @author littleredhat
 * 
 *         ��Ӧ��
 */
public class ResultCode {
	public static final int SUCCESS = 1000; // �ɹ�

	public static final int COMMON_ERR = 5000; // Ĭ�ϴ���
	public static final int SERVER_ERR = 5001; // ����������
	public static final int AUTH_ERR = 5002; // ��֤����
	public static final int PROTO_ERR = 5003; // Э�����

	public static final int S2C_Login_Username_Err = 10001; // �û���������
	public static final int S2C_Login_Password_Err = 10002; // �������
	public static final int S2C_Login_Black_List = 10003; // ������

	public static final int S2C_Register_Username_Err = 10004; // �û����Ѵ���
	public static final int S2C_Register_Username_Ill = 10005; // �û������Ϸ�

	public static final int S2C_Info_No = 20001; // �û���Ϣ������

	public static final int S2C_Score_No = 30001; // �ɼ�������

	public static final int S2C_Mail_No = 40001; // �ʼ�������
}
