package com.redhat.login.protocol;

/**
 * @author littleredhat
 * 
 *         RPCЭ����
 */
public class RPCCode {
	public static final String RPC_TYPE = "RPC_TYPE"; // �ڵ�����key
	public static final String TYPE_INFO = "TYPE_INFO"; // �ڵ����info
	public static final String TYPE_SCORE = "TYPE_SCORE"; // �ڵ����score
	public static final String TYPE_MAIL = "TYPE_MAIL"; // �ڵ����mail

	public static final String Get_Info = "getInfo"; // ��ȡ�û���Ϣ
	public static final String Update_Info = "updateInfo"; // �����û���Ϣ
	public static final String Get_Score = "getScore"; // ��ȡ�ɼ�
	public static final String Set_Score = "setScore"; // ���óɼ�
	public static final String Update_Score = "updateScore"; // ���³ɼ�
	public static final String Get_Mail = "getMail"; // ��ȡ�ʼ�
	public static final String Send_Mail = "sendMail"; // �����ʼ�
}
