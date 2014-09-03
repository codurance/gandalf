package com.codurance.model.proposal.events;

import com.codurance.infrastructure.events.DomainEventSubscriber;
import com.eclipsesource.json.JsonArray;

public class ProposalSummaryList implements DomainEventSubscriber<ProposalEvent> {

	private JsonArray proposalSummaries = new JsonArray();

	@Override
	public void handle(ProposalEvent domainEvent) {

	}

	public String allAsJson() {
		return proposalSummaries.toString();
	}
}
