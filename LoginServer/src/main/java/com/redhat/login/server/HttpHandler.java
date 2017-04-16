package com.redhat.login.server;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.redhat.login.protocol.Consts;
import com.redhat.login.protocol.HttpMsg;
import com.redhat.login.protocol.ResultCode;
import com.redhat.login.util.AES;
import com.redhat.login.core.GameServer;
import com.redhat.login.util.Coder;

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

	public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
		// �������ѿ���
		if (!GameServer.getInstance().getShutdown()) {
			// ��ȡ����
			DefaultFullHttpRequest req = (DefaultFullHttpRequest) msg;
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
			writeJSON(ctx, new HttpMsg(ResultCode.SERVER_ERR, ""));
		}
	}

	//////////

	// GET������
	private void getHandle(final ChannelHandlerContext ctx, DefaultFullHttpRequest req) {
		// String msg = req.content().toString(CharsetUtil.UTF_8);
		writeJSON(ctx, HttpResponseStatus.NOT_IMPLEMENTED, new HttpMsg(ResultCode.COMMON_ERR, ""));
	}

	// POST������
	private void postHandle(final ChannelHandlerContext ctx, final DefaultFullHttpRequest req) {
		String msg = req.content().toString(CharsetUtil.UTF_8);
		if (msg != null) {
			// ���˴���
			try {
				// ���� % ��ҪURL����
				msg = msg.contains("%") ? URLDecoder.decode(msg, "UTF-8") : msg;
				// ����
				msg = new String(Coder.decodeFromBase64(msg));

				// DEBUG
				logger.info(msg);

				// Route����
				Router.getInstance().route(msg, ctx);
			} catch (UnsupportedEncodingException e) {
				writeJSON(ctx, new HttpMsg(ResultCode.COMMON_ERR, ""));
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
		sentMsg = Consts.USE_AES ? AES.AESEncode(Consts.AES_KEY, sentMsg) : Coder.encodeToBase64(sentMsg);
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
		sentMsg = Consts.USE_AES ? AES.AESEncode(Consts.AES_KEY, sentMsg) : Coder.encodeToBase64(sentMsg);
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
