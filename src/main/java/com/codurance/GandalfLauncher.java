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

import static com.codurance.infrastructure.Throwables.execute;
import static javax.servlet.DispatcherType.*;

public class GandalfLauncher {

	public static final String DEFAULT_PORT = "8080";
	private Server server;

	public static void main(String[] args) {
		GandalfLauncher launcher = new GandalfLauncher();
		launcher.start();
		launcher.join();
	}

	public void start() {
		execute(() -> {
			server = createServer();
			server.start();
		});
	}

	public void join() {
		execute(() -> server.join());
	}

	public void stop() {
		execute(() -> server.stop());
	}

	private Server createServer() {
		Server server = new Server(port());
		server.setHandler(createContext());
		return server;
	}

	private Integer port() {
		String port = System.getenv("PORT");
		if (port == null) {
			port = DEFAULT_PORT;
		}
		return Integer.valueOf(port);
	}

	private Handler createContext() {
		WebAppContext context = createWebContext();
		addFilters(context);
		return context;
	}

	private void addFilters(WebAppContext context) {
		addWroFilter(context);
		addSparkFilter(context);
	}

	private void addSparkFilter(WebAppContext context) {
		FilterHolder sparkFilterHolder = new FilterHolder();
		sparkFilterHolder.setFilter(new SparkFilter());
		sparkFilterHolder.setInitParameter("applicationClass", "com.codurance.Gandalf");
		context.addFilter(sparkFilterHolder, "/*", EnumSet.of(FORWARD, REQUEST, ASYNC, INCLUDE, ERROR));
	}

	private void addWroFilter(WebAppContext context) {
		context.addFilter(WroFilter.class, "/wro/*", EnumSet.of(FORWARD, REQUEST, ASYNC, INCLUDE, ERROR));
	}

	private WebAppContext createWebContext() {
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