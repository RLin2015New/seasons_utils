package com.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @projectName qipai_niuyouquan_common
 * @fileName HttpTools.java
 * @description
 * @author lifl
 * @time 2017下午8:36:22
 *
 */

public class HttpTools {
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 多次反向代理后会有多个IP值，第一个为真实IP。
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			if (ip.indexOf(",") > 0) {
				return ip.substring(0, ip.indexOf(","));
			} else {
				return ip;
			}
		}
		return ip;
	}
	
	public static String post(String httpURL,Map<String, String> params,boolean isFirst) throws Exception{
		StringBuffer sb = new StringBuffer();
		if (!isFirst) {
			sb.append("&");
		}
		if (params!= null) {
			int count=  0;
			for(String key:params.keySet()){
				if (count > 0) {
					sb.append("&");
				}
				sb.append(key).append("=").append(params.get(key));
				count++;
			}
		}
		return post(httpURL, sb.toString());
	}
	
//	public static String get(String httpURL, String params){
//		CloseableHttpClient curHttpClient = isLong ? longHttpClient: httpClient;
//		CloseableHttpResponse response = null;
//		try {
//			if (params != null && !params.isEmpty()) {
//				List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
//				for (Map.Entry<String, Object> entry : params.entrySet()) {
//					String value = String.valueOf(entry.getValue());
//					if (value != null) {
//						pairs.add(new BasicNameValuePair(entry.getKey(), value));
//					}
//				}
//				if (url.indexOf("?") == -1)
//					url += "?";
//				
//				url += EntityUtils.toString(new UrlEncodedFormEntity(pairs,charset));
//			}
//			HttpGet httpGet = new HttpGet(url);
//
//			// 请求头添加
//			if (heads != null && heads.size() > 0) {
//				for (String hKey : heads.keySet()) {
//					httpGet.addHeader(hKey, heads.get(hKey));
//				}
//			}
//
//			response = curHttpClient.execute(httpGet);
//			int statusCode = response.getStatusLine().getStatusCode();
//			if (statusCode != 200) {
//				httpGet.abort();
//			}
//			HttpEntity entity = response.getEntity();
//			String result = null;
//			if (entity != null) {
//				result = EntityUtils.toString(entity, charset);
//			}
//			EntityUtils.consume(entity);
//			response.close();
//			return result;
//		}catch (Exception e) { //其他异常捕获
//			e.printStackTrace();
//		}finally{
//			if(response!=null)
//				try {
//					response.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//		}
//		return null;
//	} 

	
	public static String get(String httpURL, String params) throws Exception {
		URL url;
		if (httpURL == null
				|| (!httpURL.startsWith("http://") && !httpURL
						.startsWith("https://"))) {
			throw new NullPointerException("param is't url-" + httpURL);
		}
		url = new URL(httpURL);
		Map<String, String> requestProperty = new HashMap<String, String>();
		if (requestProperty.isEmpty()) {
			requestProperty.put("Content-type",
					"application/x-www-form-urlencoded");
		}
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		huc.setRequestMethod("GET");
		huc.setDoOutput(true);
		huc.setDoInput(true);
		huc.setConnectTimeout(5000);
		huc.setUseCaches(false);
		huc.setReadTimeout(3000);
		huc.setInstanceFollowRedirects(true);
		for (String property : requestProperty.keySet()) {
			huc.setRequestProperty(property, requestProperty.get(property));
		}
		System.out.println("getPath:" + url.getPath() + " getPort:"
				+ url.getPort());
		huc.connect();

		if (params != null && params.length() > 0) {
			OutputStream out = huc.getOutputStream();
			out.write(params.toString().getBytes("UTF-8"));
			out.flush();
			out.close();
		}

		String err = checkError(huc);
		if (err != null) {
			return err;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(
				huc.getInputStream(), "utf-8"));
		StringBuffer resp = new StringBuffer();
		String s = in.readLine();
		while (s != null) {
			resp.append(s);
			s = in.readLine();
		}
		in.close();
		return resp.toString();
	}
	
	
	public static String post(String httpURL, String params) throws Exception {
		URL url;
		if (httpURL == null
				|| (!httpURL.startsWith("http://") && !httpURL
						.startsWith("https://"))) {
			throw new NullPointerException("param is't url-" + httpURL);
		}
		url = new URL(httpURL);
		Map<String, String> requestProperty = new HashMap<String, String>();
		if (requestProperty.isEmpty()) {
			requestProperty.put("Content-type",
					"application/x-www-form-urlencoded");
		}
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		huc.setRequestMethod("POST");
		huc.setDoOutput(true);
		huc.setDoInput(true);
		huc.setConnectTimeout(5000);
		huc.setUseCaches(false);
		huc.setReadTimeout(3000);
		huc.setInstanceFollowRedirects(true);
		for (String property : requestProperty.keySet()) {
			huc.setRequestProperty(property, requestProperty.get(property));
		}
		System.out.println("getPath:" + url.getPath() + " getPort:"
				+ url.getPort());
		huc.connect();

		if (params != null && params.length() > 0) {
			OutputStream out = huc.getOutputStream();
			out.write(params.toString().getBytes("UTF-8"));
			out.flush();
			out.close();
		}

		String err = checkError(huc);
		if (err != null) {
			return err;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(
				huc.getInputStream(), "utf-8"));
		StringBuffer resp = new StringBuffer();
		String s = in.readLine();
		while (s != null) {
			resp.append(s);
			s = in.readLine();
		}
		in.close();
		return resp.toString();
	}

	private static String checkError(HttpURLConnection huc) throws Exception {

		if (huc.getResponseCode() == HttpURLConnection.HTTP_INTERNAL_ERROR) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					huc.getErrorStream(), "ISO8859-1"));
			StringBuffer resp = new StringBuffer();
			String s = in.readLine();
			while (s != null) {
				resp.append(s).append("\n");
				s = in.readLine();
			}
			in.close();
			return resp.toString();
		}
		return null;
	}

}
