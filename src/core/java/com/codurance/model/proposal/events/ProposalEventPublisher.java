package com.codurance.model.proposal.events;

import com.codurance.infrastructure.events.EventPublisher;
import com.google.inject.Inject;

public class ProposalEventPublisher extends EventPublisher {

	@Inject
	public ProposalEventPublisher(ProposalListUpdater proposalListUpdater) {
		add(proposalListUpdater);
	}
}
