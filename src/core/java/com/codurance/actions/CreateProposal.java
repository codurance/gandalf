package com.codurance.actions;

import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalService;
import com.eclipsesource.json.JsonObject;
import com.google.inject.Inject;

public class CreateProposal {

	private ProposalService proposalService;

	@Inject
	public CreateProposal(ProposalService proposalService) {
		this.proposalService = proposalService;
	}

	public Proposal create(String proposalData) {
		Proposal proposal = Proposal.fromJson(JsonObject.readFrom(proposalData));
		return proposalService.create(proposal);
	}
}
