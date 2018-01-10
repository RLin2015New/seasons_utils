package com.jettyServer;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.DispatcherType;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServlet;

import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.GzipFilter;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

/**
 * @projectName qipai_niuyouquan_common
 * @fileName JettyServer.java
 * @description
 * @author lifl
 * @time 2017下午5:04:50
 *
 */

public class JettyServer {
	private static ServerConnector getHttpConnector(Server server,
			ServerConfig conf) {
		HttpConfiguration config = new HttpConfiguration();
		config.setSecureScheme("https");
		config.setSecurePort(conf.getPort());
		ServerConnector connector = new ServerConnector(server,
				new ConnectionFactory[] { new HttpConnectionFactory(config) });
		connector.setPort(conf.getPort());
		connector.setAcceptQueueSize(conf.getAcceptQueueSize());
		connector.setIdleTimeout((long) conf.getMaxIdleTime());
		return connector;
	}

	private static ServerConnector getHttpsConnector(Server server,
			ServerConfig conf) {
		HttpConfiguration https = new HttpConfiguration();
		https.setSecurePort(conf.getPort());
		https.setSecureScheme("https");
		https.addCustomizer(new SecureRequestCustomizer());
		SslContextFactory sslContextFactory = new SslContextFactory();
		sslContextFactory.setKeyStorePath("keystore.jks");
		sslContextFactory.setKeyStorePassword("sy599guaji");
		sslContextFactory.setKeyManagerPassword("sy599guaji");
		ServerConnector sslConnector = new ServerConnector(
				server,
				new ConnectionFactory[] {
						new SslConnectionFactory(sslContextFactory, "http/1.1"),
						new HttpConnectionFactory(https) });
		sslConnector.setPort(conf.getPort());
		sslConnector.setAcceptQueueSize(conf.getAcceptQueueSize());
		sslConnector.setIdleTimeout((long) conf.getMaxIdleTime());
		return sslConnector;
	}

	public static ResourceHandler getResHandler(String resourcePath) {
		ResourceHandler resource = new ResourceHandler();
		resource.setResourceBase(resourcePath);
		resource.setDirectoriesListed(true);
//		resource.setWelcomeFiles(new String[] { "1.html" });
		return resource;
	}

	public static void startJetty(ServerConfig conf) throws Exception {
		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setMinThreads(conf.getMinThreads());
		threadPool.setMaxThreads(conf.getMaxThreads());
		Server server = new Server(threadPool);
		ServerConnector HttpsConnector = null;
		ServerConnector HttpConnector = getHttpConnector(server, conf);
		if (conf.isHttps()) {
			HttpsConnector = getHttpsConnector(server, conf);
		}

		if (HttpConnector != null) {
			server.addConnector(HttpConnector);
		}

		if (HttpsConnector != null) {
			server.addConnector(HttpsConnector);
		}

		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");
		Map serverMap = conf.getServletMap();
		if (serverMap != null && !serverMap.isEmpty()) {
			Iterator arg7 = serverMap.entrySet().iterator();

			while (arg7.hasNext()) {
				Entry handlers = (Entry) arg7.next();
				context.addServlet((Class) handlers.getKey(),
						(String) handlers.getValue());
			}
		}

		context.addFilter(GzipFilter.class, conf.getContentPath(),
				EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
		HandlerCollection handlers1 = new HandlerCollection();
		handlers1.addHandler(context);
		server.setHandler(handlers1);
		server.start();
		server.join();
	}

	public static void startJetty(ServerConfig conf, HttpServlet imageUpload,
			String imageUrl) throws Exception {
		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setMinThreads(conf.getMinThreads());
		threadPool.setMaxThreads(conf.getMaxThreads());
		Server server = new Server(threadPool);
		ServerConnector HttpsConnector = null;
		ServerConnector HttpConnector = getHttpConnector(server, conf);
		if (conf.isHttps()) {
			HttpsConnector = getHttpsConnector(server, conf);
		}

		if (HttpConnector != null) {
			server.addConnector(HttpConnector);
		}

		if (HttpsConnector != null) {
			server.addConnector(HttpsConnector);
		}

		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");
		Map serverMap = conf.getServletMap();
		if (serverMap != null && !serverMap.isEmpty()) {
			Iterator arg7 = serverMap.entrySet().iterator();

			while (arg7.hasNext()) {
				Entry handlers = (Entry) arg7.next();
				context.addServlet((Class) handlers.getKey(),
						(String) handlers.getValue());
			}
		}

		context.addFilter(GzipFilter.class, conf.getContentPath(),
				EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
		if (imageUpload != null) {	//支持图片上传
			ServletHolder fileUploadServletHolder = new ServletHolder(
					imageUpload);
			fileUploadServletHolder.getRegistration().setMultipartConfig(
					new MultipartConfigElement("data/tmp"));
			context.addServlet(fileUploadServletHolder, imageUrl);
		}

		HandlerCollection handlers1 = new HandlerCollection();
		handlers1.addHandler(context);
		server.setHandler(handlers1);
		server.start();
		server.join();
	}

}
