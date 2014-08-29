package com.codurance.model.proposal;

import com.google.inject.Inject;

import static com.codurance.model.proposal.Proposal.fromJson;

public class ProposalService {

	private Proposals proposals;

	@Inject
	public ProposalService(Proposals proposals) {
		this.proposals = proposals;
	}

	public synchronized Proposal create(ProposalJson proposalJson) {
		ProposalId proposalId = proposals.nextId();
		Proposal newProposal = fromJson(proposalJson.set("id", proposalId.asString()));
		proposals.add(newProposal);
		return newProposal;
	}

}
