package com.redhat.login.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author littleredhat
 * 
 *         AES������
 */
@SuppressWarnings("restriction")
public class AES {

	/**
	 * AES����
	 */
	public static String AESEncode(String rule, String content) {
		try {
			// 1.������Կ������
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			// 2.��ʼ����Կ������
			// ����һ��128λ�������
			keygen.init(128, new SecureRandom(rule.getBytes()));
			// 3.���ԭʼ�Գ���Կ
			SecretKey original_key = keygen.generateKey();
			// 4.�����Կ�ֽ�����
			byte[] raw = original_key.getEncoded();
			// 5.������Կ
			SecretKey key = new SecretKeySpec(raw, "AES");
			// 6.����������
			Cipher cipher = Cipher.getInstance("AES");
			// 7.��ʼ��������
			// ���� Encrypt_mode
			// ���� Decrypt_mode
			// �ڶ�������Ϊʹ�õ�KEY
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// 8.��ȡ���������ֽ�����
			byte[] byte_encode = content.getBytes("utf-8");
			// 9.�����������ĳ�ʼ����ʽ����
			byte[] byte_AES = cipher.doFinal(byte_encode);
			// 10.�����ܺ������ת��Ϊ�ַ���
			String AES_encode = new String(new BASE64Encoder().encode(byte_AES));
			// 11.�����ַ���
			return AES_encode;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES����
	 */
	public static String AESDecode(String rule, String content) {
		try {
			// 1.������Կ������
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			// 2.��ʼ����Կ������
			// ����һ��128λ�������
			keygen.init(128, new SecureRandom(rule.getBytes()));
			// 3.���ԭʼ�Գ���Կ
			SecretKey original_key = keygen.generateKey();
			// 4.�����Կ�ֽ�����
			byte[] raw = original_key.getEncoded();
			// 5.������Կ
			SecretKey key = new SecretKeySpec(raw, "AES");
			// 6.����������
			Cipher cipher = Cipher.getInstance("AES");
			// 7.��ʼ��������
			// ���� Encrypt_mode
			// ���� Decrypt_mode
			// �ڶ�������Ϊʹ�õ�KEY
			cipher.init(Cipher.DECRYPT_MODE, key);
			// 8.��ȡ���������ֽ�����
			byte[] byte_content = new BASE64Decoder().decodeBuffer(content);
			// 9.�����������ĳ�ʼ����ʽ����
			byte[] byte_decode = cipher.doFinal(byte_content);
			// 10.�����ܺ������ת��Ϊ�ַ���
			String AES_decode = new String(byte_decode, "utf-8");
			// 11.�����ַ���
			return AES_decode;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
