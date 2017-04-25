package com.redhat.login.server;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.redhat.login.protocol.Request;
import com.redhat.login.protocol.Response;
import com.redhat.login.protocol.ResponseCode;
import com.redhat.login.util.AES;
import com.redhat.login.util.Coder;
import com.redhat.login.util.Config;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author littleredhat
 */
public class Filter {
	private static Filter instance = null;
	private static Logger logger = LoggerFactory.getLogger(Filter.class);

	/**
	 * DEBUG
	 */
	private static Object xlock = new Object();
	private static int cnt = 0;

	public static Filter getInstance() {
		if (instance == null) {
			// ˫���������
			synchronized (Filter.class) {
				if (instance == null) {
					instance = new Filter();
				}
			}
		}
		return instance;
	}

	/**
	 * ���˲���֤
	 */
	public void doFilter(String msg, ChannelHandlerContext ctx) {
		try {
			// ����%��ҪURL����
			msg = msg.contains("%") ? URLDecoder.decode(msg, "UTF-8") : msg;
			// ���� trueʹ��AES falseʹ��Base64
			msg = Config.USE_AES ? new String(AES.AESDecode(Config.AES_KEY, msg))
					: new String(Coder.decodeFromBase64(msg));

			// DEBUG
			synchronized (xlock) {
				logger.info("��Ϣ " + ++cnt + " : " + msg);
			}

			// ����JSON
			Request req = JSON.parseObject(msg, Request.class);
			if (req == null) {
				return;
			}

			/*
			 * // ��ȡid long id = req.getId(); // ��ȡtoken String token =
			 * req.getToken(); // ��ȡ��֤��� boolean res =
			 * Authentication.checkToken(id, token); // ��֤ʧ�� if (!res) {
			 * HttpHandler.writeJSON(ctx, new Response(ResponseCode.AUTH_ERR,
			 * null)); return; }
			 */

			// Router����
			Router.getInstance().route(req, ctx);
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException���� : " + e);
			e.printStackTrace();
			HttpHandler.writeJSON(ctx, new Response(ResponseCode.PROTO_ERR, null));
		}
	}
}
