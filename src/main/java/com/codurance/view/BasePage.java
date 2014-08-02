package com.codurance.view;

import java.util.HashMap;
import java.util.Map;

public abstract class BasePage {

	public abstract String url();

	public abstract String title();

	public abstract String template();

	public Map<String, Object> model() {
		Map<String, Object> model = new HashMap<>();
		model.put("title", title());
		return model;
	}
}
