package com.codurance.page_objects;

public class ProjectEstimatePage extends BasePage {
	public static final String URL = "/project-estimate/:id";
	public static final String TEMPLATE = "project-estimates/project-estimate";
	private static final String PAGE_TITLE = "Project Estimate";

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
