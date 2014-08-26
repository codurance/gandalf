package com.codurance.actions;

import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalFactory;
import com.codurance.model.proposal.ProposalService;

public class CreateProposal {

	private ProposalFactory proposalFactory;
	private ProposalService proposalService;

	public CreateProposal(ProposalFactory proposalFactory, ProposalService proposalService) {
		this.proposalFactory = proposalFactory;
		this.proposalService = proposalService;
	}

	public Proposal create(String proposalData) {
		Proposal proposal = proposalFactory.createProposalFrom(proposalData);
		proposalService.create(proposal);
		return null;
	}
}
