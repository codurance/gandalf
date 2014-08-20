package com.codurance.controllers;

import main.com.codurance.controllers.TemplateRenderer;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

	private final TemplateRenderer templateRenderer;

	public BaseController(TemplateRenderer templateRenderer) {
		this.templateRenderer = templateRenderer;
	}

	public String display(String pageTemplate, Map<String, Object> model) {
		try {
			return templateRenderer.render(pageTemplate, model);
		} catch (Exception e) {
			return errorPage(pageTemplate, e);
		}
	}

	public String display(String pageTemplate) {
		return display(pageTemplate, new HashMap<>());
	}

	private String errorPage(String pageTemplate, Exception e) {
		return "Error loading page [" + pageTemplate + "]" + "\n"
				+ ExceptionUtils.getStackTrace(e);
	}
}
