package com.tools;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @projectName qipai_niuyouba_login
 * @fileName NyTools.java
 * @description
 * @author lifl
 * @time 2017下午2:28:41
 *
 */

public class NyTools {

	public static int YES = 1;

	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * Object to JSON
	 * 
	 * @param value
	 * @return String
	 */
	public static String writeValueAsString(Object value) {
		if (value == null) {
			return null;
		}
		String result = null;
		try {
			result = mapper.writeValueAsString(value);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			JarLogTools.jarLog.error("writeValueAsString error", e);
		}
		return result;
	}

	/**
	 * JSON to bean
	 * 
	 * @param content
	 * @param clazz
	 * @return Object
	 */
	public static <T> T readValue(String content, Class<T> clazz) {
		try {
			return mapper.readValue(content, clazz);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			JarLogTools.jarLog.error("readValue error", e);
		}
		return null;
	}

	public static Integer getIntegerFromMap(Map<String, Object> map, String key) {
		Object obj = map.get(key);
		if (obj == null) {
			return -1;
		}
		return Integer.valueOf(obj.toString());
	}

	public static boolean getBoolean(String val, boolean def) {
		if (!StringUtils.isBlank(val)) {
			return Boolean.valueOf(val);
		}
		return def;
	}

	public static Integer getInt(String val, int def) {
		if (!StringUtils.isBlank(val)) {
			return Integer.valueOf(val);
		}
		return def;
	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((1[0-9][0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		System.out.println(m.matches() + "---");
		return m.matches();
	}

	public static String combine(List<Integer> pais, String split) {
		String p = "";
		for (Integer one : pais) {
			if (p.length() != 0) {
				p += split;
			}
			p += one;
		}
		return p;
	}

	public static String generatePassword(String phone) {
		return MD5Tools.getStringMD5(phone + "sanguo_shangyou_2013");
	}
}
