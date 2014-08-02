package com.codurance.controllers;

import com.codurance.view.MainPage;
import spark.Request;
import spark.Response;

public class MainController extends BaseController {

	public String displayMainPage(Request request, Response response) {
		MainPage mainPage = new MainPage();
		return display(mainPage.template(), mainPage.model());
	}
}
