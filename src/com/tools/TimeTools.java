package com.tools;

import java.util.Calendar;

/**
 * @projectName qipai_niuyouquan_game
 * @fileName TimeTools.java
 * @description
 * @author lifl
 * @time 2017下午3:59:39
 *
 */

public class TimeTools {
	public static long SECOND = 1000;
	public static long MINUTE = SECOND * 60;
	public static long HOUR = 60 * MINUTE;
	public static long DAY = HOUR * 24;
	public static long WEEK = 7 * DAY;

	public static String getTimeString() {
		Calendar dar = Calendar.getInstance();
		return StringTools.append(dar.get(Calendar.YEAR),
				dar.get(Calendar.MONTH), dar.get(Calendar.DAY_OF_MONTH),
				dar.get(Calendar.HOUR), dar.get(Calendar.MINUTE),
				dar.get(Calendar.SECOND));
	}
}
