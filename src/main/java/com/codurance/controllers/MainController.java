package com.codurance.controllers;

import com.codurance.view.MainPage;
import main.com.codurance.controllers.TemplateRenderer;
import spark.Request;
import spark.Response;

public class MainController extends BaseController {

	public MainController(TemplateRenderer templateRenderer) {
		super(templateRenderer);
	}

	public String displayMainPage(Request request, Response response) {
		return display(new MainPage());
	}
}
