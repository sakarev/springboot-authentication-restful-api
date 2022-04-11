package com.sakare.vasant.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.sakare.vasant.domain.User;

/**
 * @author vasant_sakre
 *
 */

public class TokenUtil {

	private static byte[] keyBytes = { 38, 74, -22, -83, -128, -105, 34, -31, -5, 35, -36, 120, 74, 91, -1, -44 };
	private static String encryption = "AES";
	static final String HEXES = "0123456789ABCDEF";

	public static String createToken(User userDetails) throws UnsupportedEncodingException {
		try {

			String token = encrypt(userDetails);
			return token;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt(User userDetails) throws Exception {
		String encryptedText = "";

		if (null != userDetails) {
			StringBuilder signatureBuilder = new StringBuilder();
			signatureBuilder.append(userDetails.getUsername().substring(0, 2)).append(":");
			signatureBuilder.append(Math.random() * 98 + 1);
			String tokenString = signatureBuilder.toString();
			String text = new String(Base64.encodeBase64(tokenString.getBytes("UTF8")));
			SecretKeySpec key = new SecretKeySpec(keyBytes, encryption);
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptedBytes = cipher.doFinal(text.getBytes(Charset.forName("UTF-8")));
			encryptedText = byteToHex(encryptedBytes);
			encryptedText = encryptedText + "==";
		}
		return encryptedText;

	}

	public static String byteToHex(byte[] raw) {
		if (raw == null) {
			return null;
		}
		final StringBuilder hex = new StringBuilder(2 * raw.length);
		for (final byte b : raw) {
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
		}
		return hex.toString();
	}
}
