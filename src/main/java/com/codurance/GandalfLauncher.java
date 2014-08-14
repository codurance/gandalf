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
		startJetty();
	}

	private static void startJetty() {
		try {
			Server server = createServer();
			server.start();
			server.join();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Server createServer() {
		Server server = new Server(8080);
		server.setHandler(createContext());
		return server;
	}

	private static Handler createContext() {
		WebAppContext context = createWebContext();
		addFilters(context);
		return context;
	}

	private static void addFilters(WebAppContext context) {
		addWroFilter(context);
		addSparkFilter(context);
	}

	private static void addSparkFilter(WebAppContext context) {
		FilterHolder sparkFilterHolder = new FilterHolder();
		sparkFilterHolder.setFilter(new SparkFilter());
		sparkFilterHolder.setInitParameter("applicationClass", "com.codurance.infrastructure.GaldalfSpark");
		context.addFilter(sparkFilterHolder, "/*", EnumSet.of(FORWARD, REQUEST, ASYNC, INCLUDE, ERROR));
	}

	private static void addWroFilter(WebAppContext context) {
		context.addFilter(WroFilter.class, "/wro/*", EnumSet.of(FORWARD, REQUEST, ASYNC, INCLUDE, ERROR));
	}

	private static WebAppContext createWebContext() {
		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		context.setResourceBase(resourceBase());
		return context;
	}

	private static String resourceBase() {
		try {
			return new File(System.getProperty("user.dir") + "/src/main/webapp").getCanonicalPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}