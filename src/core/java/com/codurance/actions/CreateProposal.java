package com.codurance.actions;

import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalJson;
import com.codurance.model.proposal.ProposalService;
import com.google.inject.Inject;

public class CreateProposal {

	private ProposalService proposalService;

	@Inject
	public CreateProposal(ProposalService proposalService) {
		this.proposalService = proposalService;
	}

	public Proposal create(ProposalJson proposalJson) {
		return proposalService.create(proposalJson);
	}
}
