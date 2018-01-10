package com.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @projectName qipai_niuyouquan_common
 * @fileName MD5Tools.java
 * @description
 * @author lifl
 * @time 2017下午3:10:22
 *
 */

public class MD5Tools {
	/**
	 * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合
	 */
	protected static char hexDigits[] = { '0', '1', '4', '7', '2', '5', '8',
			'3', '6', '9', 'b', 'c', 'a', 'd', 'e', 'f' };
	// protected static MessageDigest messagedigest = null;

	private static ThreadLocal<MessageDigest> threadLocal = new ThreadLocal<MessageDigest>() {
		@Override
		protected MessageDigest initialValue() {
			MessageDigest messagedigest = null;
			try {
				messagedigest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				JarLogTools.jarLog.error("initialValue error", e);
			}
			return messagedigest;
		}
	};

	public static String getFileMD5String(java.io.File file) throws IOException {
		InputStream fis;
		fis = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		int numRead = 0;
		MessageDigest messagedigest = threadLocal.get();
		while ((numRead = fis.read(buffer)) > 0) {
			messagedigest.update(buffer, 0, numRead);
		}
		fis.close();
		return bufferToHex(messagedigest.digest());
	}

	// public static String getStringMD5(String str) {
	// byte[] buffer = str.getBytes();
	// MessageDigest messagedigest = threadLocal.get();
	// messagedigest.update(buffer);
	// return bufferToHex(messagedigest.digest());
	// }

	public static String getStringMD5(String str) {
		byte[] buffer;
		try {
			buffer = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JarLogTools.jarLog.error("getStringMD5 error", e);
			return "";
		}
		MessageDigest messagedigest = threadLocal.get();
		messagedigest.update(buffer);

		return byte2HexStr(messagedigest.digest());

	}

	public static String getStringMD5_pay(String str) {
		byte[] buffer;
		try {
			buffer = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JarLogTools.jarLog.error("getStringMD5_pay error", e);
			return "";
		}
		MessageDigest messagedigest = threadLocal.get();
		messagedigest.update(buffer);

		return byte2HexStr(messagedigest.digest());

	}

	public static String getStringMD5(String str, String charset) {
		byte[] buffer;
		try {
			buffer = str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JarLogTools.jarLog.error("getStringMD5_pay error", e);
			return "";
		}
		MessageDigest messagedigest = threadLocal.get();
		messagedigest.update(buffer);

		return byte2HexStr(messagedigest.digest());

	}

	public static String md5Digest(String src) throws Exception {
		// 定义数字签名方法, 可用：MD5, SHA-1
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(src.getBytes("utf-8"));

		return byte2HexStr(b);
	}

	private static String byte2HexStr(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			String s = Integer.toHexString(b[i] & 0xFF);
			if (s.length() == 1) {
				sb.append("0");
			}

			sb.append(s.toLowerCase());
		}

		return sb.toString();
	}

	public static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换
		// 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
		char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	/***
	 * MD5加密 生成32位md5码
	 * 
	 * @param 待加密字符串
	 * @return 返回32位md5码
	 */
	public static String md5Encode(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			JarLogTools.jarLog.error("md5Encode1 error", e);
			return "";
		}

		byte[] byteArray;
		StringBuffer hexValue = new StringBuffer();
		try {
			byteArray = inStr.getBytes("UTF-8");
			byte[] md5Bytes = md5.digest(byteArray);

			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			JarLogTools.jarLog.error("md5Encode2 error", e);
		}

		return hexValue.toString();
	}

	public static void main(String[] args) {
		System.out
				.println(getStringMD5("userId=116369412&time=1385001139&serverId=S1pse7Z74UY4TbpZnL"));

	}
}
