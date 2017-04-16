package com.redhat.rpc.protocol;

/**
 * @author littleredhat
 * 
 *         ������
 */
public class RequestCode {
	public static final int TEST = 10000; // ����

	public static final int C2S_Login = 10001; // ��¼
	public static final int C2S_Register = 10002; // ע��

	public static final int C2S_Get_Info = 20001; // ��ȡ�û���Ϣ
	public static final int C2S_Update_Info = 20002; // �����û���Ϣ

	public static final int C2S_Get_Score = 30001; // ��ȡ�ɼ�
	public static final int C2S_Set_Score = 30002; // ���óɼ�
	public static final int C2S_Update_Score = 30003; // ���³ɼ�

	public static final int C2S_Get_Mail = 40001; // ��ȡ�ʼ�
	public static final int C2S_Send_Mail = 40002; // �����ʼ�
}
