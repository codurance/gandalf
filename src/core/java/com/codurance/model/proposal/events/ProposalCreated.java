package com.codurance.model.proposal.events;

import com.codurance.model.proposal.ProposalId;

public class ProposalCreated {

	private ProposalId proposalId;

	public ProposalCreated(ProposalId proposalId) {
		this.proposalId = proposalId;
	}

	public ProposalId proposalId() {
		return this.proposalId;
	}
}
