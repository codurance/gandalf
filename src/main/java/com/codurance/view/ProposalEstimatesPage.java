package com.codurance.view;

import com.codurance.model.proposal.ProposalId;

import java.util.Map;

public class ProposalEstimatesPage extends BasePage {
	public static final String URL = "/proposals/proposal/:proposalId/estimates";
	public static final String ADD_FEATURE_URL = "proposals/proposal/feature/add";
	private static final String TEMPLATE = PROPOSAL_TEMPLATE_ROOT_FOLDER + "proposal_estimates.jade";
	private static final String TITLE = "Proposal Estimates";
	private ProposalId proposalId;

	public ProposalEstimatesPage(ProposalId proposalId) {
		this.proposalId = proposalId;
	}

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

	@Override
	protected void populate(Map<String, Object> model) {
		model.put("proposalId", proposalId.intValue());
	}

}
