package com.codurance.actions;

import com.codurance.model.proposal.events.ProposalSummaryList;
import com.google.inject.Inject;

public class RetrieveProposals {

	private ProposalSummaryList proposalSummaryList;

	@Inject
	public RetrieveProposals(ProposalSummaryList proposalSummaryList) {
		this.proposalSummaryList = proposalSummaryList;
	}

	public String all() {
		return proposalSummaryList.allAsJson().toString();
	}
}
