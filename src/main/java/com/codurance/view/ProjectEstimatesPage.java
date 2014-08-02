package com.codurance.view;

public class ProjectEstimatesPage extends BasePage {
	public static final String URL = "/project-estimates";
	public static final String TEMPLATE = "project_estimates/all_estimates.jade";
	private static final String PAGE_TITLE = "Project Estimates";

	public String url() {
		return URL;
	}

	public String title() {
		return PAGE_TITLE;
	}

	public String template() {
		return TEMPLATE;
	}

}
