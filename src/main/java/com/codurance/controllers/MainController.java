package com.codurance.controllers;

import spark.Request;
import spark.Response;

public class MainController {

	public String displayMainPage(Request request, Response response) {
		return "Hello World!";
	}
}
