package com.redhat.rpc.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.redhat.rpc.protocol.RequestCode;
import com.redhat.rpc.service.InfoService;
import com.redhat.rpc.service.ScoreService;

/**
 * @author littleredhat
 * 
 *         RPC
 */
public class RPCServer {
	private static RPCServer instance = null;
	private static Map<String, JsonRpcServer> rpcServer = null;

	public static RPCServer getInstance() {
		if (instance == null) {
			// ˫���������
			synchronized (RPCServer.class) {
				if (instance == null) {
					instance = new RPCServer();
				}
			}
		}
		return instance;
	}

	public void initData() {
		rpcServer = new HashMap<String, JsonRpcServer>();
		// info
		JsonRpcServer infoServer = new JsonRpcServer(new InfoService(), InfoService.class);
		rpcServer.put(Integer.toString(RequestCode.INFO), infoServer);
		// score
		JsonRpcServer scoreServer = new JsonRpcServer(new ScoreService(), ScoreService.class);
		rpcServer.put(Integer.toString(RequestCode.SCORE), scoreServer);
	}

	/**
	 * RPC����
	 */
	public void handle(String type, InputStream request, OutputStream response) throws IOException {
		// �������ͻ�ȡRPC����
		JsonRpcServer server = rpcServer.get(type);
		// ����RPC����
		server.handle(request, response);
	}
}
