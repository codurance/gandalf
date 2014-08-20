package com.codurance.infrastructure.template;

import de.neuland.jade4j.Jade4J;
import main.com.codurance.controllers.TemplateRenderer;

import java.io.File;
import java.util.Map;

import static com.codurance.infrastructure.Throwables.executeQuery;

public class JadeTemplateRendered implements TemplateRenderer {

	private static final String CURRENT_FOLDER = new File(".").getAbsolutePath();
	private static final String RESOURCES_FOLDER = CURRENT_FOLDER + "/src/main/webapp/WEB-INF/view/";
	private static final String TEMPLATES_FOLDER = RESOURCES_FOLDER + "templates/";

	@Override
	public String render(String template, Map<String, Object> model) {
		return executeQuery(() -> Jade4J.render(TEMPLATES_FOLDER + template, model));
	}
}
