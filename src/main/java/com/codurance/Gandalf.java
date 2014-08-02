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
		staticFileLocation(STATIC_CONTENT_FOLDER);
		routes.initialise();
	}

}
