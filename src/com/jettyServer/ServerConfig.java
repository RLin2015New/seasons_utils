package com.jettyServer;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Servlet;

/**
 * @projectName qipai_niuyouquan_common
 * @fileName ServerConfig.java
 * @description
 * @author lifl
 * @time 2017下午5:04:27
 *
 */

public class ServerConfig {
	private int port;
	private int minThreads = 10;
	private int maxThreads = 500;
	private int acceptQueueSize = 50;
	private int acceptors = 2;
	private int maxIdleTime = '';
	private boolean isHttps;
	private String contentPath;
	private Map<Class<? extends Servlet>, String> servletMap;

	public void addServer(Class<? extends Servlet> server, String contentPath) {
		if (this.servletMap == null) {
			this.servletMap = new LinkedHashMap();
		}

		if (!this.servletMap.containsKey(server)) {
			this.servletMap.put(server, contentPath);
		}

	}

	public Map<Class<? extends Servlet>, String> getServletMap() {
		return this.servletMap;
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getMinThreads() {
		return this.minThreads;
	}

	public void setMinThreads(int minThreads) {
		this.minThreads = minThreads;
	}

	public int getMaxThreads() {
		return this.maxThreads;
	}

	public void setMaxThreads(int maxThreads) {
		this.maxThreads = maxThreads;
	}

	public int getAcceptQueueSize() {
		return this.acceptQueueSize;
	}

	public void setAcceptQueueSize(int acceptQueueSize) {
		this.acceptQueueSize = acceptQueueSize;
	}

	public int getAcceptors() {
		return this.acceptors;
	}

	public void setAcceptors(int acceptors) {
		this.acceptors = acceptors;
	}

	public int getMaxIdleTime() {
		return this.maxIdleTime;
	}

	public void setMaxIdleTime(int maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}

	public boolean isHttps() {
		return this.isHttps;
	}

	public void setHttps(boolean isHttps) {
		this.isHttps = isHttps;
	}

	public String getContentPath() {
		return this.contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

	public String buildMsg() {
		String format = "---------jetty Server Start ,port:%d ,minThreads:%d,maxThreads:%d,acceptQueueSize:%d,acceptors:%d,maxIdleTime:%d-----------";
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(
				format,
				new Object[]{Integer.valueOf(this.port),
						Integer.valueOf(this.minThreads),
						Integer.valueOf(this.maxThreads),
						Integer.valueOf(this.acceptQueueSize),
						Integer.valueOf(this.acceptors),
						Integer.valueOf(this.maxIdleTime)}));
		sb.append("\n");
		sb.append("contentPath-->" + this.contentPath);
		if (this.servletMap != null) {
			Iterator arg3 = this.servletMap.entrySet().iterator();

			while (arg3.hasNext()) {
				Entry entry = (Entry) arg3.next();
				sb.append("\n");
				sb.append(((Class) entry.getKey()).getName()).append("-->")
						.append((String) entry.getValue());
			}
		}

		return sb.toString();
	}
}


