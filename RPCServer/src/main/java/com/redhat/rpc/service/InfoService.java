package com.redhat.rpc.service;

import com.alibaba.fastjson.JSON;
import com.redhat.rpc.manager.InfoManager;
import com.redhat.rpc.model.InfoModel;
import com.redhat.rpc.protocol.Request;
import com.redhat.rpc.protocol.Response;
import com.redhat.rpc.protocol.ResponseCode;

/**
 * @author littleredhat
 */
public class InfoService {

	/**
	 * ��ȡ��Ϣ
	 */
	public Response getInfo(Request msg) {
		// ��ȡ���
		InfoModel model = InfoManager.getInstance().getInfo(msg.getId());

		if (model != null) {
			// ���سɹ���Ӧ
			return new Response(ResponseCode.SUCCESS, JSON.toJSONString(model));
		} else {
			// ����ʧ����Ӧ
			return new Response(ResponseCode.COMMON_ERR, null);
		}
	}

	/**
	 * ������Ϣ
	 */
	public Response updateInfo(Request msg) {
		// ��ȡ����
		long id = msg.getId();
		InfoModel model = JSON.parseObject(msg.getData(), InfoModel.class);

		// ���½��
		boolean res = InfoManager.getInstance().updateInfo(id, model);

		if (res) {
			// ���سɹ���Ӧ
			return new Response(ResponseCode.SUCCESS, JSON.toJSONString(model));
		} else {
			// ����ʧ����Ӧ
			return new Response(ResponseCode.COMMON_ERR, null);
		}
	}
}
