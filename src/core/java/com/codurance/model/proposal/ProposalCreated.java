package com.codurance.model.proposal;

public class ProposalCreated {

	private ProposalId proposalId;

	public ProposalCreated(ProposalId proposalId) {
		this.proposalId = proposalId;
	}

	public ProposalId proposalId() {
		return this.proposalId;
	}
}
