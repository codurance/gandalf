package com.codurance.view;

public class ProposalPage extends BasePage {
	public static final String URL = "/project-estimate/:estimateId";
	public static final String TEMPLATE = "project_estimates/project_estimate.jade";
	private static final String PAGE_TITLE = "Proposal";

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
