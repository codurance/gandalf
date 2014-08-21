package com.codurance.controllers;

import com.codurance.view.BasePage;
import main.com.codurance.controllers.TemplateRenderer;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class BaseController {

	private final TemplateRenderer templateRenderer;

	public BaseController(TemplateRenderer templateRenderer) {
		this.templateRenderer = templateRenderer;
	}

	protected String display(BasePage page) {
		try {
			return templateRenderer.render(page.template(), page.model());
		} catch (Exception e) {
			return errorPage(page.template(), e);
		}
	}

	private String errorPage(String pageTemplate, Exception e) {
		return "Error loading page [" + pageTemplate + "]" + "\n"
				+ ExceptionUtils.getStackTrace(e);
	}
}
