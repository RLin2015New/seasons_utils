package com.tools;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @projectName qipai_niuyouquan_common
 * @fileName StringTools.java
 * @description
 * @author lifl
 * @time 2017下午5:53:13
 *
 */

public class StringTools {
	/**
	 * 
	 * @description
	 * @param src
	 * @param len
	 * @param apd
	 * @param append
	 *            是否拼接到结尾
	 * @return String
	 * @time 2017下午4:41:12
	 */
	public static String fullFillToLength(String src, int len, String apd,
			boolean append) {
		if (len <= 0) {
			return "";
		}
		if (src == null) {
			return createCopyNum(len, apd);
		}
		if (src.length() < len) {
			if (append) {
				return src + createCopyNum(len - src.length(), apd);
			} else {
				return createCopyNum(len - src.length(), apd) + src;
			}
		}
		if (src.length() > len) {
			return src.substring(0, len);
		}
		return src;
	}

	public static String createCopyNum(int len, String copyed) {
		if (len <= 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(copyed);
		}
		return sb.toString();
	}

	public static String append(String str, String... s) {
		StringBuilder sb = new StringBuilder();
		if (str != null) {
			sb.append(str);
		}
		for (String one : s) {
			sb.append(one);
		}
		return sb.toString();
	}

	public static String append(Object str, Object... s) {
		StringBuilder sb = new StringBuilder();
		if (str != null) {
			sb.append(str.toString());
		}
		for (Object one : s) {
			sb.append(one.toString());
		}
		return sb.toString();
	}

	public static boolean isEmpty(String str, String... s) {
		if (str == null || str.length() == 0) {
			return true;
		}
		for (String one : s) {
			if (one == null || one.length() == 0) {
				return true;
			}
		}
		return false;
	}

	public static boolean isAllNumber(String str){
		if (str == null) {
			return false;
		}
		return str.matches("[0-9]+");
	}
	
	
	public static boolean getBoolean(String val, boolean def) {
		if (!StringUtils.isBlank(val)) {
			return Boolean.valueOf(val);
		}
		return def;
	}

	/**
	 * 对JSON key进行排序，然后组合成p=v&p1=v1&p2=v2&p3=v3形式
	 * 
	 * @param data
	 * @return
	 */
	public static String getUrlData(JSONObject data) {
		Set<String> keySets = data.keySet();
		Object[] keys = keySets.toArray();
		Arrays.sort(keys);

		StringBuffer str = new StringBuffer();
		int i = 0;
		for (Object key : keys) {
			String value = data.getString(key.toString());
			if (i > 0)
				str.append("&");
			str.append(key).append("=").append(value);
			i++;
		}
		return str.toString();
	}

	public static final String FORMAT_FOUR = "yyyyMMddHHmmss";

	public static String dateToString(Date time, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String date = dateFormat.format(time);
		return date;
	}

	public static <T, V> String mapToString(Map<T, V> map, String prex,
			String split) {

		if (map == null || map.size() == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		int size = map.size();
		for (T t : map.keySet()) {
			sb.append(t);
			sb.append(prex);
			sb.append(map.get(t));
			if ((size--) > 0) {
				sb.append(split);
			}
		}
		return sb.toString();
	}

	public static int handlerVersionStr(String version) {
		version = version.replace("v", "");
		version = version.replace(".", "");
		return Integer.parseInt(version);
	}

	public static int getIntValue(String[] values, int index) {
		String value = getValue(values, index);
		if (StringUtils.isBlank(value)) {
			return 0;
		}
		return Integer.parseInt(value);
	}

	public static Integer getInt(String val, int def) {
		if (!StringUtils.isBlank(val)) {
			return Integer.valueOf(val);
		}
		return def;
	}

	public static String getValue(String[] values, int index) {
		if (index >= values.length) {
			return "";
		}
		return values[index];
	}

	public static String filterEmoji(String source) {
		// JarLogTools.jarLog.info("source:" + source);
		if (StringUtils.isNotBlank(source)) {
			source = source.replaceAll(
					"[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
			 JarLogTools.jarLog.info("source:" + source);
			source = source.replaceAll("\\\\x[0-9|a-fA-F][0-9|a-fA-F]", "");
			JarLogTools.jarLog.info("source:" + source);
			return source;
		} else {
			return source;
		}
	}

	// /**
	// * 检测是否有emoji字符
	// *
	// * @param source
	// * 需要判断的字符串
	// * @return 一旦含有就抛出
	// */
	// public static boolean containsEmoji(String source) {
	// int len = source.length();
	// for (int i = 0; i < len; i++) {
	// char codePoint = source.charAt(i);
	// if (!notisEmojiCharacter(codePoint)) {
	// // 判断确认有表情字符
	// return true;
	// }
	// }
	// return false;
	// }
	//
	// /**
	// * 非emoji表情字符判断
	// *
	// * @param codePoint
	// * @return
	// */
	// private static boolean notisEmojiCharacter(char codePoint) {
	// return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
	// || (codePoint == 0xD)
	// || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
	// || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
	// || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	// }
	//
	// /**
	// * 过滤emoji 或者 其他非文字类型的字符
	// *
	// * @param source
	// * 需要过滤的字符串
	// * @return
	// */
	// public static String filterEmoji(String source) {
	// if (!containsEmoji(source)) {
	// return source;// 如果不包含，直接返回
	// }
	//
	// StringBuilder buf = null;// 该buf保存非emoji的字符
	// int len = source.length();
	// for (int i = 0; i < len; i++) {
	// char codePoint = source.charAt(i);
	// if (notisEmojiCharacter(codePoint)) {
	// if (buf == null) {
	// buf = new StringBuilder(source.length());
	// }
	// buf.append(codePoint);
	// }
	// }
	//
	// if (buf == null) {
	// return "";// 如果没有找到非emoji的字符，则返回无内容的字符串
	// } else {
	// if (buf.length() == len) {
	// buf = null;
	// return source;
	// } else {
	// return buf.toString();
	// }
	// }
	// }
	//
	// public static String filterEmoji2(String source) {
	// if (source == null) {
	// return source;
	// }
	// Pattern emoji = Pattern
	// .compile(
	// "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
	// Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
	// Matcher emojiMatcher = emoji.matcher(source);
	// if (emojiMatcher.find()) {
	// source = emojiMatcher.replaceAll("*");
	// return source;
	// }
	// return source;
	// }

	public static void main(String[] args) {
		// System.out.println(fullFillToLength("a", 4, "0", false));
		// System.out.println(fullFillToLength("aaaa", 4, "0", false));
		// System.out.println(fullFillToLength("aaaaa", 4, "0", false));
		// \xF0\x9F\x8C\xB1
		String b_text = "\\xF0\\x9F\\x8C\\xB1 \\xF0\\x9F\\x8C\\xB1 \\xF0\\x9F\\x8C\\xB1 \\xF0\\x9F\\x8C\\xB1 009";
		// String b_text = "abs";

		b_text = b_text.replaceAll("\\\\x[0-9|a-fA-F][0-9|a-fA-F]", "");
		// b_text = Integer.toHexString(b_text);
		// String regex="^[A-Fa-f0-9]+$";

		// String str =
		// "adt 12 ce 63 02251 26342".replaceAll("[^0-9abcdef]*([0-9abcdef])[^0-9abcdef]*([0-9abcdef]?)[^0-9abcdef]*",
		// "$1$2 ").trim().toUpperCase();"
		// + "
		// System.out.println(filterEmoji2(b_text));
		System.out.println(b_text);

		// b_text = b_text.replaceAll("[\\x##]", "");
		// b_text = b_text.replaceAll("\\x([0-9]|[a-zA-Z])([0-9]|[a-zA-Z])",
		// "");
		// b_text = b_text.replaceAll("[\x00-\xZZ]", "");
		// System.out.println(filterEmoji(b_text));
		// for (int i = 0; i < b_text.length; i++) {
		// if ((b_text[i] & 0xF8) == 0xF0) {
		// for (int j = 0; j < 4; j++) {
		// b_text[i + j] = 0x30;
		// }
		// i += 3;
		// }
		// }
	}
}
