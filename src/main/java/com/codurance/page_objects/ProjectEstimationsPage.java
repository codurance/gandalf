package com.codurance.page_objects;

public class ProjectEstimationsPage extends BasePage {
	public static final String URL = "/project-estimations";
	public static final String TEMPLATE = "project_estimations/all_estimations.jade";
	private static final String PAGE_TITLE = "Project Estimations";

	public String url() {
		return URL;
	}

	public String pageTitle() {
		return PAGE_TITLE;
	}

	public String template() {
		return TEMPLATE;
	}

}
