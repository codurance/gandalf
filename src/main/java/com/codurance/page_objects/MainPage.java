package com.codurance.page_objects;

public class MainPage extends BasePage {

	public static final String URL = "/";
	public static final String TEMPLATE = "index.jade";
	private static final String PAGE_TITLE = "Welcome to Gandalf";

	@Override
	public String url() {
		return URL;
	}

	@Override
	public String title() {
		return PAGE_TITLE;
	}

	@Override
	public String template() {
		return TEMPLATE;
	}
}
