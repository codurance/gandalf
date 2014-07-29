package com.codurance.controllers;

import com.codurance.page_objects.MainPage;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class MainController extends BaseController {

	public String displayMainPage(Request request, Response response) {
		MainPage mainPage = new MainPage();
		return display(mainPage.template(), mainPage.model());
	}
}
