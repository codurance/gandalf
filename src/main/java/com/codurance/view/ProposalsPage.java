package com.codurance.view;

public class ProposalsPage extends BasePage {
	public static final String URL = "/proposals";
	public static final String TEMPLATE = PROPOSAL_TEMPLATE_ROOT_FOLDER + "proposals.jade";
	private static final String PAGE_TITLE = "Proposals";

	public String url() {
		return URL;
	}

	public String title() {
		return PAGE_TITLE;
	}

	public String template() {
		return TEMPLATE;
	}

}
