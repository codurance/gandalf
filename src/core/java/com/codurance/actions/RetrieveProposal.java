package com.codurance.actions;

import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.Proposals;

public class RetrieveProposal {
	private Proposals proposals;

	public RetrieveProposal(Proposals proposals) {
		this.proposals = proposals;
	}

	public String by(ProposalId proposalId) {
		return proposals.findById(proposalId);
	}
}
