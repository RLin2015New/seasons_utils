package com.base;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.tools.JarLogTools;
import com.tools.MD5Tools;

/**
 * @projectName qipai_niuyouba_login
 * @fileName BaseServlet.java
 * @description
 * @author lifl
 * @time 2017下午2:23:48
 *
 */

public class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1261667621084307193L;

	public static String EDC = "edc"; // md5值
	public static String EDT = "edt"; // 排序算法

	/**
	 * 当前沿用旧的身份验证算法。<br>
	 * 这个算法其实不适用了，不能够防止复制请求后修改参数信息的侵入<br>
	 * 
	 * @description
	 * @param request
	 * @return boolean
	 * @time 2017上午10:16:21
	 */
	public boolean checkPdkSign(HttpServletRequest request) {
		
		Enumeration<String> names = request.getParameterNames();
		List<String> array = new ArrayList<String>();
		String checkCode = null;
		String checkType = null;
		while (names.hasMoreElements()) {
			String ele = names.nextElement();
			if (EDC.equals(ele)) {
				checkCode = ele;
			} else if (EDT.equals(ele)) {
				checkType = ele;
			} else {
				array.add(ele);
			}
		}
		if (checkCode == null || checkType == null) {
			JarLogTools.info("加密类型码或md5码错误");
			return false;
		}
		Integer type = Integer.parseInt(request.getParameter(checkType));
		// JarLogTools.info("type:"+type);
		// JarLogTools.info("beforeSort:"+ListTools.listToString(array, ","));
		CheckCodeSort.sort(type.toString(), array);
		// JarLogTools.info("afterSort:"+ListTools.listToString(array, ","));
		StringBuilder sb = new StringBuilder();
		for (String temp : array) {
			sb.append(request.getParameter(temp));
		}
		// JarLogTools.info("ori src:"+sb.toString());
		String md5 = MD5Tools.getStringMD5(sb.toString());
		// JarLogTools.info("md5 src:"+md5);
		String src = request.getParameter(checkCode);
		// JarLogTools.info("ori md5 src:"+src);
		if (src.equals(md5)) {
			JarLogTools.info("checkPdkSign success");
			return true;
		}
		JarLogTools.error("checkPdkSign fail");
		return false;
	}

	public static void signParams(Map<String, String> params) {
		if (params == null || params.size() == 0) {
			return;
		}
		List<String> keys = new ArrayList<String>(params.keySet());
		long rand = System.currentTimeMillis() % 2 + 1;
		CheckCodeSort.sort(rand + "", keys);
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			sb.append(params.get(key));
		}
		params.put(BaseServlet.EDT, rand + "");
		params.put(BaseServlet.EDC, MD5Tools.getStringMD5(sb.toString()));
	}
	// TODO 攻击防护 问询特别，然后防护。统计黑名单到制定文件，然后由服务器去防护
}
