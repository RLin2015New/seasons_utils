package com.tools;

import java.io.IOException;
import java.util.List;

/**
 * @projectName qipai_niuyouquan_common
 * @fileName SensitiveWordTools.java
 * @description
 * @author lifl
 * @time 2017下午1:56:59
 *
 */

public class SensitiveWordTools {

	private static String REPLACE = "**";
	private static List<String> sensitiveWords = null;

	public static void init(String path) {
		try {
			sensitiveWords = FileTools.readLineToArray(path, "GBK");
		} catch (IOException e) {
			e.printStackTrace();
			JarLogTools.jarLog.error(" 敏感词加载出错 sensitiveWord error", e);
		}
	}

	/***
	 * 
	 * @description 检测敏感字
	 * @param src
	 * @return String
	 * @time 2017下午2:02:27
	 */
	public static String checkSensitiveWord(String src) {
		// init();
		if (src == null) {
			return null;
		}
		for (String word : sensitiveWords) {
			if (src.contains(word)) {
				return word;
			}
		}
		return null;
	}

	// /**
	// *
	// * @description 替换敏感字位REPLACE
	// * @param src
	// * @return boolean
	// * @time 2017下午2:03:02
	// */
	// public static String replaceSensitiveWord(String src) {
	// if (src == null) {
	// return "";
	// }
	//
	// return src;
	// }

	public static String replaceSensitiveWord(String src, int start) {
		for (int i = start; i < sensitiveWords.size(); i++) {
			String sensitive = sensitiveWords.get(i);

			if (sensitive.length() > 0 && src.contains(sensitive)) {
				return replaceSensitiveWord(src.replace(sensitive, REPLACE),
						start + 1);
			}
		}
		return src;
	}

}
