package com.redhat.rpc.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.redhat.rpc.manager.ScoreManager;
import com.redhat.rpc.model.ScoreModel;
import com.redhat.rpc.model.ScoreReq;
import com.redhat.rpc.protocol.HttpMsg;
import com.redhat.rpc.protocol.ResultCode;

/**
 * @author littleredhat
 */
public class ScoreService {

	/**
	 * ��ȡ�û��ɼ�
	 */
	public HttpMsg getScore(HttpMsg msg) {
		// ��ȡ�û�����
		ScoreReq req = JSON.parseObject(msg.getData(), ScoreReq.class);

		// ��ȡ�û��ɼ�
		List<ScoreModel> modelList = ScoreManager.getInstance().getScore(req.getUserId());

		if (modelList != null) {
			// ���سɹ���Ӧ
			return new HttpMsg(ResultCode.SUCCESS, JSON.toJSONString(modelList));
		} else {
			// ����ʧ����Ӧ
			return new HttpMsg(ResultCode.S2C_Score_No, null);
		}
	}

	/**
	 * �����û��ɼ�
	 */
	public HttpMsg setScore(HttpMsg msg) {
		// ��ȡ�û�����
		ScoreModel model = JSON.parseObject(msg.getData(), ScoreModel.class);

		// ��ȡ�û��ɼ�
		boolean res = ScoreManager.getInstance().setScore(model);

		if (res) {
			// ���سɹ���Ӧ
			return new HttpMsg(ResultCode.SUCCESS, null);
		} else {
			// ����ʧ����Ӧ
			return new HttpMsg(ResultCode.S2C_Score_No, null);
		}
	}

	/**
	 * �����û��ɼ�
	 */
	public HttpMsg updateScore(HttpMsg msg) {
		// ��ȡ��������
		ScoreModel model = JSON.parseObject(msg.getData(), ScoreModel.class);

		// ��ȡ���ý��
		boolean res = ScoreManager.getInstance().updateScore(model);

		if (res) {
			// ���سɹ���Ӧ
			return new HttpMsg(ResultCode.SUCCESS, null);
		} else {
			// ����ʧ����Ӧ
			return new HttpMsg(ResultCode.COMMON_ERR, null);
		}
	}
}
