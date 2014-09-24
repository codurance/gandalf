package com.codurance.model.proposal;

import com.codurance.model.proposal.events.FeatureAdded;
import com.codurance.model.proposal.events.FeatureEventPublisher;
import com.codurance.model.proposal.events.ProposalCreated;
import com.codurance.model.proposal.events.ProposalEventPublisher;
import com.codurance.model.proposal.feature.FeatureJson;
import com.google.inject.Inject;

import static com.codurance.model.proposal.Proposal.fromJson;

public class ProposalService {

	private Proposals proposals;
	private ProposalEventPublisher proposalEventPublisher;
	private FeatureEventPublisher featureEventPublisher;

	@Inject
	public ProposalService(Proposals proposals,
	                       ProposalEventPublisher proposalEventPublisher,
	                       FeatureEventPublisher featureEventPublisher) {
		this.proposals = proposals;
		this.proposalEventPublisher = proposalEventPublisher;
		this.featureEventPublisher = featureEventPublisher;
	}

	public synchronized Proposal create(ProposalJson proposalJson) {
		ProposalId proposalId = proposals.nextId();
		Proposal newProposal = fromJson(proposalJson.set("id", proposalId.intValue()));
		proposals.add(newProposal);
		proposalEventPublisher.publish(new ProposalCreated(newProposal));
		return newProposal;
	}

	public void addFeatureToProposal(ProposalId proposalId, FeatureJson newFeature) {
		proposals.addFeatureToProposal(proposalId, newFeature);
		featureEventPublisher.publish(new FeatureAdded(newFeature));
	}
}
