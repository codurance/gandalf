package com.codurance.controllers;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static de.neuland.jade4j.Jade4J.render;

public class BaseController {

	private static final String CURRENT_FOLDER = new File(".").getAbsolutePath();
	private static final String RESOURCES_FOLDER = CURRENT_FOLDER + "/src/main/webapp/WEB-INF/view/";
	private static final String TEMPLATES_FOLDER = RESOURCES_FOLDER + "templates/";

	public String display(String pageTemplate, Map<String, Object> model) {
		try {
			return render(TEMPLATES_FOLDER + pageTemplate, model);
		} catch (IOException e) {
			return errorPage(pageTemplate, e);
		}
	}

	public String display(String pageTemplate) {
		return display(pageTemplate, new HashMap<>());
	}

	private String errorPage(String pageTemplate, IOException e) {
		return "Error loading page [" + pageTemplate + "]" + "\n"
				+ ExceptionUtils.getStackTrace(e);
	}
}
