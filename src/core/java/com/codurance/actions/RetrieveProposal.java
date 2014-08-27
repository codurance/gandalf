package com.codurance.actions;

import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.Proposals;
import com.google.inject.Inject;

public class RetrieveProposal {
	private Proposals proposals;

	@Inject
	public RetrieveProposal(Proposals proposals) {
		this.proposals = proposals;
	}

	public String by(ProposalId proposalId) {
		return proposals.findById(proposalId);
	}
}
