package com.redhat.rpc.protocol;

/**
 * @author littleredhat
 * 
 *         ��Ӧ
 */
public class Response {

	// ��Ӧ��
	public short responseCode;
	// ����
	public String data;

	public Response() {
	}

	public Response(short responseCode, String data) {
		this.responseCode = responseCode;
		this.data = data;
	}

	public short getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(short responseCode) {
		this.responseCode = responseCode;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
