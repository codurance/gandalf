package com.codurance.model.proposal;

public class ProposalService {

	private Proposals proposals;

	public ProposalService(Proposals proposals) {
		this.proposals = proposals;
	}

	public Proposal create(Proposal proposal) {
		return proposals.add(proposal);
	}

}
