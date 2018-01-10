package com.tools;

import org.apache.log4j.Logger;

/**
 * @projectName qipai_niuyouquan_common
 * @fileName JarLogTools.java
 * @description
 * @author lifl
 * @time 2017下午8:38:11
 *
 */

public class JarLogTools {
	public static Logger jarLog;
	
	public static void setJarLog(Logger log){
		jarLog = log;
	}

	public static void info(String str) {
		if (jarLog != null) {
			jarLog.info(str);
		}
	}

	public static void error(String str) {
		if (jarLog != null) {
			jarLog.error(str);
		}
	}

}
