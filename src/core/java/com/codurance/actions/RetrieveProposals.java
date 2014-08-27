package com.codurance.actions;

import com.codurance.model.proposal.Proposals;
import com.google.inject.Inject;

public class RetrieveProposals {

	private Proposals proposals;

	@Inject
	public RetrieveProposals(Proposals proposals) {
		this.proposals = proposals;
	}

	public String all() {
		return proposals.all();
	}
}
