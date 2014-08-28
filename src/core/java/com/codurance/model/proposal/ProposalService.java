package com.codurance.model.proposal;

import com.google.inject.Inject;

public class ProposalService {

	private Proposals proposals;

	@Inject
	public ProposalService(Proposals proposals) {
		this.proposals = proposals;
	}

	public Proposal create(ProposalJson proposalJson) {
		return proposals.add(Proposal.fromJson(proposalJson));
	}

}
