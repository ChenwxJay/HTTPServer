package com.redhat.login.handler;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.redhat.login.manager.ShieldManager;
import com.redhat.login.manager.UserManager;
import com.redhat.login.model.LoginModel;
import com.redhat.login.model.UserModel;
import com.redhat.login.protocol.Response;
import com.redhat.login.protocol.ResponseCode;
import com.redhat.login.util.Authentication;
import com.redhat.login.util.Config;

/**
 * @author littleredhat
 */
public class UserHandler {

	public static Response login(UserModel model) {
		// ��ȡ����
		String username = model.getUsername();
		String password = model.getPassword();

		// id
		long id = UserManager.getInstance().getId(username);
		if (id == -1) {
			System.out.println("�û���������!!!");
			return new Response(ResponseCode.Login_Username_Err, null);
		}

		// enable
		boolean enable = UserManager.getInstance().getEnable(id);
		if (!enable) {
			System.out.println("�û����������!!!");
			return new Response(ResponseCode.Login_Black_List, null);
		}

		// password
		String password_server = UserManager.getInstance().getPassword(id);
		if (!password.equals(password_server)) {
			System.out.println("�û��������!!! " + password + " != " + password_server);
			return new Response(ResponseCode.Login_Password_Err, null);
		}

		Date date = new Date();
		// online
		boolean online = UserManager.getInstance().updateOnline(id, date);
		if (!online) {
			System.out.println("����ʧ��!!!");
			return new Response(ResponseCode.COMMON_ERR, null);
		}

		System.out.println("��¼�ɹ�!!!" + date);
		// ����token
		String token = Authentication.getToken(id);
		// �洢redis
		Authentication.setToken(id, token);
		// ����ʵ��
		LoginModel res = new LoginModel(id, token, Config.GateLocalPort, Config.GateIp);

		return new Response(ResponseCode.SUCCESS, JSON.toJSONString(res));
	}

	public static Response register(UserModel model) {
		// ��ȡ����
		String username = model.getUsername();
		String password = model.getPassword();

		// id
		long id = UserManager.getInstance().getId(username);
		if (id != -1) {
			System.out.println("�û����Ѵ���!!!");
			return new Response(ResponseCode.Register_Username_Err, null);
		}

		// ill
		boolean ill = ShieldManager.getInstance().hasShield(username);
		if (ill) {
			System.out.println("�û����Ƿ�!!!");
			return new Response(ResponseCode.Register_Username_Ill, null);
		}

		// user
		boolean result = UserManager.getInstance().addUser(username, password);
		if (result) {
			System.out.println("ע��ɹ�!!!");
			return new Response(ResponseCode.SUCCESS, null);
		} else {
			System.out.println("ע��ʧ��!!!");
			return new Response(ResponseCode.COMMON_ERR, null);
		}
	}
}
