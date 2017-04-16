package com.redhat.rpc.service;

import com.alibaba.fastjson.JSON;
import com.redhat.rpc.manager.InfoManager;
import com.redhat.rpc.model.InfoModel;
import com.redhat.rpc.model.InfoReq;
import com.redhat.rpc.protocol.HttpMsg;
import com.redhat.rpc.protocol.ResultCode;

/**
 * @author littleredhat
 */
public class InfoService {

	/**
	 * ��ȡ�û���Ϣ
	 */
	public HttpMsg getInfo(HttpMsg msg) {
		// ��ȡ��������
		InfoReq req = JSON.parseObject(msg.getData(), InfoReq.class);

		// ��ȡ�û���Ϣ
		InfoModel model = InfoManager.getInstance().getInfo(req.getUserId());

		if (model != null) {
			// ���سɹ���Ӧ
			return new HttpMsg(ResultCode.SUCCESS, JSON.toJSONString(model));
		} else {
			// ����ʧ����Ӧ
			return new HttpMsg(ResultCode.S2C_Info_No, null);
		}
	}

	/**
	 * �����û���Ϣ
	 */
	public HttpMsg updateInfo(HttpMsg msg) {
		// ��ȡ��������
		InfoModel model = JSON.parseObject(msg.getData(), InfoModel.class);

		// ��ȡ���ý��
		boolean res = InfoManager.getInstance().setInfo(model);

		if (res) {
			// ���سɹ���Ӧ
			return new HttpMsg(ResultCode.SUCCESS, null);
		} else {
			// ����ʧ����Ӧ
			return new HttpMsg(ResultCode.S2C_Info_No, null);
		}
	}
}
