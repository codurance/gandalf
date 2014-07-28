package com.codurance.controllers;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class MainController extends BaseController {

	public String displayMainPage(Request request, Response response) {
		return display("index.jade");
	}
}
