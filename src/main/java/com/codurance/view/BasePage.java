package com.codurance.view;

import java.util.HashMap;
import java.util.Map;

public abstract class BasePage {

	protected static final String PROPOSAL_TEMPLATE_ROOT_FOLDER = "proposal/";

	public abstract String url();

	public abstract String title();

	public abstract String template();

	public Map<String, Object> model() {
		Map<String, Object> model = new HashMap<>();
		model.put("title", title());
		return model;
	}
}
