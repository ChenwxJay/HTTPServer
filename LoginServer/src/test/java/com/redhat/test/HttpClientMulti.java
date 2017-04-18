package com.redhat.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.redhat.login.model.UserModel;
import com.redhat.login.model.UserReq;
import com.redhat.login.protocol.Consts;
import com.redhat.login.protocol.HttpMsg;
import com.redhat.login.protocol.RequestCode;
import com.redhat.login.protocol.ResultCode;
import com.redhat.login.util.Coder;

/**
 * @author littleredhat
 */
public class HttpClientMulti {
	// �����߳���
	private static int CNT = 1600;

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < CNT; ++i) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					int id = 0;
					String session = null;
					String ip = "127.0.0.1";
					int port = 9901;
					String username = "maomao";
					String password = "520";

					////////// ���� //////////

					// login
					UserReq user = new UserReq(username, Coder.encodeToMD5(password));
					HttpMsg msg = new HttpMsg(RequestCode.C2S_Login, JSON.toJSONString(user));
					String str = JSON.toJSONString(msg);
					String data = Coder.encodeToBase64(str);
					// ��¼���
					String res = null;
					try {
						res = task(id, session, ip, port, data);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("login ==> " + res);
					HttpMsg resMsg = JSON.parseObject(res, HttpMsg.class);
					if (resMsg.getCode() == ResultCode.SUCCESS) {
						UserModel model = JSON.parseObject(resMsg.getData(), UserModel.class);
						// ���õ�¼����
						id = model.getId();
						session = model.getSession();
						port = model.getGatePort();
						ip = model.getGateIp();

						////////// ���� //////////

						// getInfo
						InfoReq info = new InfoReq(id);
						HttpMsg msg2 = new HttpMsg(RequestCode.C2S_Get_Info, JSON.toJSONString(info));
						String str2 = JSON.toJSONString(msg2);
						String data2 = Coder.encodeToBase64(str2);
						// ��ȡ�û���Ϣ���
						String res2 = null;
						try {
							res2 = task(id, session, ip, port, data2);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						System.out.println("getInfo ==> " + res2);
						HttpMsg resMsg2 = JSON.parseObject(res2, HttpMsg.class);
						if (resMsg2.getCode() == ResultCode.SUCCESS) {
							System.out.println("��ȡ�ɹ�!!!");
						} else {
							System.out.println("��ȡʧ��!!!");
						}
					} else {
						System.out.println("��¼ʧ��!!!");
					}
				}
			});
			t.start();
		}
	}

	// ��������
	private static String task(int id, String session, final String ip, final int port, final String params)
			throws Exception {
		// ����url
		URL url = new URL("http://" + ip + ":" + port);
		// openConnection
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setDoOutput(true);
		// session�ǿ�������
		if (session != null && !session.isEmpty()) {
			String _session = session + ";" + id;
			// ����session
			http.setRequestProperty(Consts.SESSION_GET, _session);
		}

		// getOutputStream
		OutputStreamWriter out = new OutputStreamWriter(http.getOutputStream(), "UTF-8");
		if (params != null) {
			out.write(params);
		}
		out.flush();
		out.close();

		// getInputStream
		InputStreamReader in = new InputStreamReader(http.getInputStream(), "UTF-8");
		BufferedReader reader = new BufferedReader(in);
		StringBuffer res = new StringBuffer();
		String line = null;
		while ((line = reader.readLine()) != null) {
			res.append(line);
		}
		in.close();

		// disconnect
		if (http != null) {
			http.disconnect();// �ر�����
		}

		return new String(Coder.decodeFromBase64(res.toString()));
	}
}
