package com.tools;

/**
 * @projectName qipai_niuyouquan_common
 * @fileName ImageTools.java
 * @description
 * @author lifl
 * @time 2017下午3:56:35
 *
 */

public class ImageTools {

	public static String getFileType(String path) {
		if (path == null) {
			return null;
		}
		String[] s = path.split("\\.");
		if (s != null && s.length > 0) {
			return s[s.length - 1];
		}
		return null;
	}
}
