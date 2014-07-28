package com.codurance.page_objects;

import java.util.HashMap;
import java.util.Map;

public class ProjectEstimationsPage {
	public static final String URL = "/project-estimations";
	public static final String TEMPLATE = "project_estimations/all_estimations.jade";
	private static final String PAGE_TITLE = "Project Estimations";

	public String url() {
		return URL;
	}

	public String pageTile() {
		return PAGE_TITLE;
	}

	public String template() {
		return TEMPLATE;
	}

	public Map<String, Object> model() {
		Map<String, Object> model = new HashMap<>();
		model.put("pageTitle", PAGE_TITLE);
		return model;
	}
}
