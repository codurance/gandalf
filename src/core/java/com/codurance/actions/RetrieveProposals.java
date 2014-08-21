package com.codurance.actions;

import com.codurance.model.proposal.Proposals;

public class RetrieveProposals {

	private Proposals proposals;

	public RetrieveProposals(Proposals proposals) {
		this.proposals = proposals;
	}

	public String all() {
		return proposals.all();
	}
}
