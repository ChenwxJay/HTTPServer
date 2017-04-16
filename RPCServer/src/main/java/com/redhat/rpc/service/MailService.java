package com.redhat.rpc.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.redhat.rpc.manager.MailManager;
import com.redhat.rpc.model.MailModel;
import com.redhat.rpc.model.MailReq;
import com.redhat.rpc.protocol.HttpMsg;
import com.redhat.rpc.protocol.ResultCode;

/**
 * @author littleredhat
 */
public class MailService {

	/**
	 * ��ȡ�ʼ�
	 */
	public HttpMsg getMail(HttpMsg msg) {
		// ��ȡ�û�����
		MailReq req = JSON.parseObject(msg.getData(), MailReq.class);

		// ��ȡ�ʼ�
		List<MailModel> modelList = MailManager.getInstance().getMail(req.getUserId());

		if (modelList != null) {
			// ���سɹ���Ӧ
			return new HttpMsg(ResultCode.SUCCESS, JSON.toJSONString(modelList));
		} else {
			// ����ʧ����Ӧ
			return new HttpMsg(ResultCode.S2C_Mail_No, null);
		}
	}

	/**
	 * �����ʼ�
	 */
	public HttpMsg sendMail(HttpMsg msg) {
		// ��ȡ��������
		MailModel model = JSON.parseObject(msg.getData(), MailModel.class);

		// ��ȡ���ý��
		boolean res = MailManager.getInstance().sendMail(model);

		if (res) {
			// ���سɹ���Ӧ
			return new HttpMsg(ResultCode.SUCCESS, null);
		} else {
			// ����ʧ����Ӧ
			return new HttpMsg(ResultCode.S2C_Mail_No, null);
		}
	}
}
