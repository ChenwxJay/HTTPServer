package com.redhat.login.protocol;

import java.io.Serializable;

/**
 * @author littleredhat
 * 
 *         Http��Ϣ��
 */
public class HttpMsg implements Serializable {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	// Э����
	private int code;
	// ����
	public String data;

	public HttpMsg() {
	}

	public HttpMsg(int code, String data) {
		this.code = code;
		this.data = data;
	}

	////////// get set //////////

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
