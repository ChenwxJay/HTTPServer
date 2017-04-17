package com.redhat.gate.server;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.redhat.gate.core.GameServer;
import com.redhat.gate.protocol.Consts;
import com.redhat.gate.protocol.HttpMsg;
import com.redhat.gate.protocol.ResultCode;
import com.redhat.gate.util.AES;
import com.redhat.gate.util.Authentication;
import com.redhat.gate.util.Coder;
import com.redhat.gate.util.Config;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * @author littleredhat
 * 
 *         Handle
 */
public class HttpHandler extends ChannelHandlerAdapter {
	private static Logger logger = LoggerFactory.getLogger(HttpHandler.class);

	// DEBUG ���������Ƿ�ȫ����������
	private static int cnt = 0; // ����
	private static Object xlock = new Object(); // ����

	/**
	 * Netty��ȡ��Ϣ
	 */
	public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
		// �������ѿ���
		if (!GameServer.getInstance().getShutdown()) {
			// ��ȡ����
			DefaultFullHttpRequest req = (DefaultFullHttpRequest) msg;

			// DEBUG
			for (Entry<String, String> k : req.headers().entries()) {
				logger.info(k.getKey() + " " + k.getValue());
			}

			// ����get����
			if (req.getMethod() == HttpMethod.GET) {
				getHandle(ctx, req);
			}
			// ����POST����
			if (req.getMethod() == HttpMethod.POST) {
				postHandle(ctx, req);
			}
		}
		// �������ѹر�
		else {
			writeJSON(ctx, new HttpMsg(ResultCode.SERVER_ERR, null));
		}
	}

	//////////

	// GET������
	private void getHandle(final ChannelHandlerContext ctx, DefaultFullHttpRequest req) {
		// String msg = req.content().toString(CharsetUtil.UTF_8);
		writeJSON(ctx, HttpResponseStatus.NOT_IMPLEMENTED, new HttpMsg(ResultCode.COMMON_ERR, null));
	}

	// POST������
	private void postHandle(final ChannelHandlerContext ctx, final DefaultFullHttpRequest req) {

		// ��ȡsession�ַ���
		String _session = req.headers().get(Consts.SESSION_GET);
		// ��ȡsession
		String session = _session.split(";")[0];
		// ��ȡid
		int id = Integer.parseInt(_session.split(";")[1]);
		// ��ȡ��֤���
		boolean res = Authentication.getInstancce().checkSession(id, session);
		// ��֤ʧ��
		if (!res) {
			writeJSON(ctx, new HttpMsg(ResultCode.AUTH_ERR, null));
			return;
		}

		// ��ȡ��Ϣ
		String msg = req.content().toString(CharsetUtil.UTF_8);
		if (msg != null) {
			try {
				// ���� % ��ҪURL����
				msg = msg.contains("%") ? URLDecoder.decode(msg, Consts.UTF8) : msg;
				// ���� trueʹ��AES falseʹ��Base64
				msg = Config.UseAES ? new String(AES.AESDecode(Consts.AES_KEY, msg))
						: new String(Coder.decodeFromBase64(msg));

				// DEBUG
				synchronized (xlock) {
					logger.info("��Ϣ " + ++cnt + " : " + msg);
				}

				// Route����
				Router.getInstance().route(msg, ctx);
			} catch (UnsupportedEncodingException e) {
				writeJSON(ctx, new HttpMsg(ResultCode.COMMON_ERR, null));
			}
		}
	}

	//////////

	/**
	 * ctx status msg
	 */
	public static void writeJSON(ChannelHandlerContext ctx, HttpResponseStatus status, Object msg) {
		String sentMsg = null;
		if (msg instanceof String) { // String
			sentMsg = (String) msg;
		} else { // JSON
			sentMsg = JSON.toJSONString(msg);
		}
		// ���� trueʹ��AES falseʹ��Base64
		sentMsg = Config.UseAES ? AES.AESEncode(Consts.AES_KEY, sentMsg) : Coder.encodeToBase64(sentMsg);
		writeJSON(ctx, status, Unpooled.copiedBuffer(sentMsg, CharsetUtil.UTF_8));
	}

	/**
	 * ctx msg
	 */
	public static void writeJSON(ChannelHandlerContext ctx, Object msg) {
		String sentMsg = null;
		if (msg instanceof String) { // String
			sentMsg = (String) msg;
		} else { // JSON
			sentMsg = JSON.toJSONString(msg);
		}
		// ���� trueʹ��AES falseʹ��Base64
		sentMsg = Config.UseAES ? AES.AESEncode(Consts.AES_KEY, sentMsg) : Coder.encodeToBase64(sentMsg);
		writeJSON(ctx, HttpResponseStatus.OK, Unpooled.copiedBuffer(sentMsg, CharsetUtil.UTF_8));
	}

	/**
	 * ����JSON��Ϣ
	 */
	private static void writeJSON(ChannelHandlerContext ctx, HttpResponseStatus status,
			ByteBuf content /* , boolean isKeepAlive */) {
		if (ctx.channel().isWritable()) {
			FullHttpResponse msg = null;
			// ���ݷǿ�
			if (content != null) {
				msg = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, content);
				// Http�̶�ͷ��
				msg.headers().set(HttpHeaders.Names.CONTENT_TYPE, Consts.CONTENT_TYPE);
			} else {
				msg = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
			}
			// ���ݷǿ�
			if (msg.content() != null) {
				msg.headers().set(HttpHeaders.Names.CONTENT_LENGTH, msg.content().readableBytes());
			}
			// ������������
			ctx.write(msg).addListener(ChannelFutureListener.CLOSE);
			ctx.flush();
		}
	}
	/////

	/**
	 * Netty�׳��쳣
	 */
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error(cause.getMessage());
	}
}
