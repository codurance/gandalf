package com.codurance.view;

public class ProposalEstimatesPage extends BasePage {
	public static final String URL = "/proposals/proposal/:proposalId/estimates";
	private static final String TEMPLATE = PROPOSAL_TEMPLATE_ROOT_FOLDER + "proposal_estimates.jade";
	private static final String TITLE = "Proposal Estimates";

	@Override
	public String url() {
		return URL;
	}

	@Override
	public String title() {
		return TITLE;
	}

	@Override
	public String template() {
		return TEMPLATE;
	}
}
