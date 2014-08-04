package com.codurance.view;

import com.codurance.model.proposal.Proposal;

import java.util.Map;

public class ProposalPage extends BasePage {
	public static final String URL = "/proposals/proposal/:proposalId";
	public static final String TEMPLATE = PROPOSAL_TEMPLATE_ROOT_FOLDER + "proposal.jade";
	private static final String PAGE_TITLE = "Proposal";
	public static final String PROPOSAL_DATA_URL = URL + "/json";
	private String proposalJson;

	public ProposalPage(String proposalJson) {
		this.proposalJson = proposalJson;
	}

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

	@Override
	protected void populate(Map<String, Object> model) {
		model.put("proposal", proposalJson);
		model.put("proposalJava", new Proposal());
	}
}
