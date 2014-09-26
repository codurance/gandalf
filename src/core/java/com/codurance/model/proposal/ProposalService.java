package com.codurance.model.proposal;

import com.codurance.model.proposal.events.ProposalCreated;
import com.codurance.model.proposal.events.ProposalEventPublisher;
import com.google.inject.Inject;

import static com.codurance.model.proposal.Proposal.fromJson;
import static com.codurance.model.proposal.ProposalJson.aProposalJsonWith;

public class ProposalService {

	private Proposals proposals;
	private ProposalEventPublisher proposalEventPublisher;

	@Inject
	public ProposalService(Proposals proposals,
	                       ProposalEventPublisher proposalEventPublisher) {
		this.proposals = proposals;
		this.proposalEventPublisher = proposalEventPublisher;
	}

	public synchronized Proposal create(ProposalJson proposalJson) {
		ProposalId proposalId = proposals.nextId();
		proposalJson = aProposalJsonWith(proposalJson.set("id", proposalId.intValue()));
		Proposal newProposal = fromJson(proposalJson);
		proposals.add(newProposal);
		proposalEventPublisher.publish(new ProposalCreated(newProposal));
		return newProposal;
	}

}
