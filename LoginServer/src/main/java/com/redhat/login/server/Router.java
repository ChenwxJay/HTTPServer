package com.redhat.login.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.redhat.login.server.HttpHandler;
import com.redhat.login.protocol.ResultCode;
import com.redhat.login.handler.UserHandler;
import com.redhat.login.protocol.HttpMsg;
import com.redhat.login.protocol.RequestCode;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author littleredhat
 * 
 *         Router
 */
public class Router {
	private static Router instance = null;
	private Logger logger = LoggerFactory.getLogger(Router.class);

	private Router() {
	}

	public static Router getInstance() {
		if (instance == null) {
			// ˫���������
			synchronized (Router.class) {
				if (instance == null) {
					instance = new Router();
				}
			}
		}
		return instance;
	}

	/**
	 * Route����
	 */
	public void route(String msg, ChannelHandlerContext ctx) {
		// ��ȡ��Ϣ
		HttpMsg httpMsg = null;
		try {
			httpMsg = JSON.parseObject(msg, HttpMsg.class);
		} catch (Exception e) {
			logger.error("��ʽ����");
			HttpHandler.writeJSON(ctx, new HttpMsg(ResultCode.PROTO_ERR, null));
			return;
		}
		// ��Ϣ����
		switch (httpMsg.getCode()) {
		case RequestCode.TEST:
			logger.info("Router TEST");
			HttpHandler.writeJSON(ctx, new HttpMsg(ResultCode.SUCCESS, null));
			break;
		case RequestCode.C2S_Login: {
			logger.info("Router C2S_Login");
			HttpMsg res = UserHandler.login(httpMsg);
			HttpHandler.writeJSON(ctx, res);
			break;
		}
		case RequestCode.C2S_Register: {
			logger.info("Router C2S_Register");
			HttpMsg res = UserHandler.register(httpMsg);
			HttpHandler.writeJSON(ctx, res);
			break;
		}
		default:
			logger.error("��Э���");
			HttpHandler.writeJSON(ctx, new HttpMsg(ResultCode.PROTO_ERR, null));
			break;
		}
	}
}
