package com.codurance.model.proposal;

import com.codurance.infrastructure.events.EventPublisher;
import com.codurance.model.proposal.events.ProposalCreated;
import com.codurance.model.proposal.events.ProposalEventPublisher;
import com.codurance.model.proposal.feature.FeatureJson;
import com.google.inject.Inject;

import static com.codurance.model.proposal.Proposal.fromJson;

public class ProposalService {

	private Proposals proposals;
	private EventPublisher eventPublisher;

	@Inject
	public ProposalService(Proposals proposals, ProposalEventPublisher eventPublisher) {
		this.proposals = proposals;
		this.eventPublisher = eventPublisher;
	}

	public synchronized Proposal create(ProposalJson proposalJson) {
		ProposalId proposalId = proposals.nextId();
		Proposal newProposal = fromJson(proposalJson.set("id", proposalId.intValue()));
		proposals.add(newProposal);
		eventPublisher.publish(new ProposalCreated(newProposal));
		return newProposal;
	}

	public void addFeatureToProposal(ProposalId proposalId, FeatureJson featureJson) {


	}
}
