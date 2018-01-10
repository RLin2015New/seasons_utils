package com.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @projectName qipai_niuyouba_login
 * @fileName CharacterTools.java
 * @description
 * @author lifl
 * @time 2017下午3:30:20
 *
 */

public class CharacterTools {
	public static int countNumber(String str) {
		int count = 0;
		Pattern p = Pattern.compile("\\d");
		Matcher m = p.matcher(str);
		while (m.find()) {
			count++;
		}
		return count;
	}

	public static int countLetter(String str) {
		int count = 0;
		Pattern p = Pattern.compile("[a-zA-Z]");
		Matcher m = p.matcher(str);
		while (m.find()) {
			count++;
		}
		return count;
	}

	public static int countChinese(String str) {
		int count = 0;
		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
		Matcher m = p.matcher(str);
		while (m.find()) {
			count++;
		}
		return count;
	}

	public static int skipNumberLetterAndChinese(String str) {
		Pattern p = Pattern.compile("[a-zA-Z]|(\\d)|[\\u4e00-\\u9fa5]");
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim().length();
	}
}
