package com.codurance.actions;

import com.codurance.infrastructure.events.EventPublisher;
import com.codurance.model.proposal.*;
import com.google.inject.Inject;

public class CreateProposal {

	private ProposalService proposalService;
	private EventPublisher eventPublisher;
	private ProposalListUpdater proposalListUpdater;

	@Inject
	public CreateProposal(ProposalService proposalService,
	                      EventPublisher eventPublisher,
	                      ProposalListUpdater proposalListUpdater) {
		this.proposalService = proposalService;
		this.eventPublisher = eventPublisher;
		this.proposalListUpdater = proposalListUpdater;
	}

	public Proposal create(ProposalJson proposalJson) {
		eventPublisher.reset();
		eventPublisher.add(proposalListUpdater);

		Proposal newProposal = proposalService.create(proposalJson);

		eventPublisher.publish(new ProposalCreated(newProposal.id()));

		return newProposal;
	}
}
