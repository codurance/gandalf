package main.com.codurance.controllers;

import java.util.Map;

public interface TemplateRenderer {
	String render(String template, Map<String, Object> model);
}
