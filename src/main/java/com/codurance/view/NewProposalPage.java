package com.codurance.view;

public class NewProposalPage extends BasePage {

	public static final String URL = "/proposals/new";
	public static final String TEMPLATE = PROPOSAL_TEMPLATE_ROOT_FOLDER + "proposals_new.jade";
	private static final String PAGE_TITLE = "New Proposals";

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
