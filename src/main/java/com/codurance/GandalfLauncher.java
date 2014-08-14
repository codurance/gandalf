package com.codurance;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import ro.isdc.wro.http.WroFilter;
import spark.servlet.SparkFilter;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

import static javax.servlet.DispatcherType.*;

public class GandalfLauncher {

	public static void main(String[] args) {
		try {
			startJetty();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void startJetty() throws Exception {
		Server server = createServer();
		server.start();
		server.join();
	}

	private static Server createServer() throws IOException {
		Server server = new Server(8080);
		server.setHandler(createContext());
		return server;
	}

	private static Handler createContext() throws IOException {
		WebAppContext context = createWebContext();
		addFilters(context);
		return context;
	}

	private static void addFilters(WebAppContext context) {
		context.addFilter(WroFilter.class, "/wro/*", EnumSet.of(FORWARD, REQUEST, ASYNC, INCLUDE, ERROR));

		FilterHolder sparkFilterHolder = new FilterHolder();
		sparkFilterHolder.setFilter(new SparkFilter());
		sparkFilterHolder.setInitParameter("applicationClass", "com.codurance.infrastructure.GaldalfSpark");
		context.addFilter(sparkFilterHolder, "/*", EnumSet.of(FORWARD,REQUEST,ASYNC,INCLUDE,ERROR));
	}

	private static WebAppContext createWebContext() throws IOException {
		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		context.setResourceBase(resourceBase());
		return context;
	}

	private static String resourceBase() throws IOException {
		return new File(System.getProperty("user.dir") + "/src/main/webapp").getCanonicalPath();
	}

}