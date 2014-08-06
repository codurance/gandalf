package com.codurance;

import com.codurance.infrastructure.Routes;

import static spark.Spark.staticFileLocation;


public class Gandalf {

	private  static final String STATIC_CONTENT_FOLDER = "/public/";

	private Routes routes;

	public Gandalf(Routes routes) {
		this.routes = routes;
	}

	public void run() {
		configureStaticFiles();
		configureRoutes();
	}

	private void configureStaticFiles() {
		staticFileLocation(STATIC_CONTENT_FOLDER);
	}

	private void configureRoutes() {
		routes.initialise();
	}

}
