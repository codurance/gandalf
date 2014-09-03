package com.codurance.model.proposal.events;

import com.codurance.model.proposal.Proposal;

public class ProposalCreated implements ProposalEvent {

	private Proposal proposal;

	public ProposalCreated(Proposal proposal) {
		this.proposal = proposal;
	}

	public Proposal proposal() {
		return proposal;
	}
}
