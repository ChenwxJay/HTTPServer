package com.redhat.gate.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author littleredhat
 * 
 *         Property������
 */
public class Property {
	private static Properties p;

	// ��ȡ����
	public static String getData(String key) {
		if (p == null) {
			try {
				p = readProperties();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p.getProperty(key);
	}

	// ��ȡ�����ļ�
	private static Properties readProperties() throws IOException {
		Properties p = new Properties();
		InputStream in = Property.class.getResourceAsStream("/net.properties");
		Reader r = new InputStreamReader(in, Charset.forName("UTF-8"));
		p.load(r);
		in.close();
		return p;
	}
}
