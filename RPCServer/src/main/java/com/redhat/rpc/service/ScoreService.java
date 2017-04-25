package com.redhat.rpc.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.redhat.rpc.manager.ScoreManager;
import com.redhat.rpc.model.ScoreModel;
import com.redhat.rpc.protocol.Request;
import com.redhat.rpc.protocol.Response;
import com.redhat.rpc.protocol.ResponseCode;

/**
 * @author littleredhat
 */
public class ScoreService {

	/**
	 * ��ȡ�ɼ�
	 */
	public Response getScore(Request msg) {
		// ��ȡ���
		List<ScoreModel> scoreList = ScoreManager.getInstance().getScore(msg.getId());

		if (scoreList != null) {
			// ���سɹ���Ӧ
			return new Response(ResponseCode.SUCCESS, JSON.toJSONString(scoreList));
		} else {
			// ����ʧ����Ӧ
			return new Response(ResponseCode.COMMON_ERR, null);
		}
	}

	/**
	 * ���³ɼ�
	 */
	public Response updateScore(Request msg) {
		// ��ȡ����
		long id = msg.getId();
		ScoreModel model = JSON.parseObject(msg.getData(), ScoreModel.class);

		boolean hasExist = ScoreManager.getInstance().hasScore(id, model.subject);
		boolean res = false;
		if (hasExist) {
			// �Ѿ����������
			res = ScoreManager.getInstance().updateScore(id, model);
		} else {
			// ������������
			res = ScoreManager.getInstance().setScore(id, model);
		}

		if (res) {
			// ���سɹ���Ӧ
			return new Response(ResponseCode.SUCCESS, JSON.toJSONString(model));
		} else {
			// ����ʧ����Ӧ
			return new Response(ResponseCode.COMMON_ERR, null);
		}
	}
}
