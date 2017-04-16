package com.redhat.login.handler;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.redhat.login.manager.ShieldManager;
import com.redhat.login.manager.UserManager;
import com.redhat.login.model.UserModel;
import com.redhat.login.model.UserReq;
import com.redhat.login.protocol.HttpMsg;
import com.redhat.login.protocol.ResultCode;
import com.redhat.login.util.Authentication;
import com.redhat.login.util.Config;

/**
 * @author littleredhat
 */
public class UserHandler {

	public HttpMsg login(HttpMsg msg) {
		// ��ȡ����
		String username = JSON.parseObject(msg.getData(), UserReq.class).getUsername();
		String password = JSON.parseObject(msg.getData(), UserReq.class).getPassword();

		// Id
		int id = UserManager.getInstance().getId(username);
		if (id == -1) {
			System.out.println("�û���������!!!");
			return new HttpMsg(ResultCode.S2C_Login_Username_Err, null);
		}

		// enable
		boolean enable = UserManager.getInstance().getEnable(id);
		if (!enable) {
			System.out.println("���������!!!");
			return new HttpMsg(ResultCode.S2C_Login_Black_List, null);
		}

		// password
		String password_server = UserManager.getInstance().getPassword(id);
		if (!password.equals(password_server)) {
			System.out.println("�������!!! " + password + " != " + password_server);
			return new HttpMsg(ResultCode.S2C_Login_Password_Err, null);
		}

		Date date = new Date();
		// online
		boolean online = UserManager.getInstance().updateOnline(id, date);
		if (!online) {
			System.out.println("����ʧ��!!!");
			return new HttpMsg(ResultCode.COMMON_ERR, null);
		}

		System.out.println("��¼�ɹ�!!!" + date);
		// ����session
		String session = Authentication.getSession(id);
		// �洢redis
		Authentication.getInstancce().setSession(id, session);
		// ����ʵ��
		UserModel res = new UserModel(id, session, Config.GateLocalPort, Config.GateIp);

		return new HttpMsg(ResultCode.SUCCESS, JSON.toJSONString(res));
	}

	public HttpMsg register(HttpMsg msg) {
		// ��ȡ����
		String username = JSON.parseObject(msg.getData(), UserReq.class).getUsername();
		String password = JSON.parseObject(msg.getData(), UserReq.class).getPassword();

		// id
		int id = UserManager.getInstance().getId(username);
		if (id != -1) {
			System.out.println("�û����Ѵ���!!!");
			return new HttpMsg(ResultCode.S2C_Register_Username_Err, null);
		}

		// ill
		boolean ill = ShieldManager.getInstance().hasShield(username);
		if (ill) {
			System.out.println("�û����Ƿ�!!!");
			return new HttpMsg(ResultCode.S2C_Register_Username_Ill, null);
		}

		// user
		boolean result = UserManager.getInstance().addUser(username, password);
		if (result) {
			System.out.println("ע��ɹ�!!!");
			return new HttpMsg(ResultCode.SUCCESS, null);
		} else {
			System.out.println("ע��ʧ��!!!");
			return new HttpMsg(ResultCode.COMMON_ERR, null);
		}
	}
}
