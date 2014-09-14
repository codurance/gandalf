package com.codurance.model.proposal.events;

import com.codurance.infrastructure.events.DomainEventSubscriber;
import com.codurance.model.client.Client;
import com.codurance.model.client.ClientService;
import com.codurance.model.proposal.ClientId;
import com.codurance.model.proposal.ProposalJson;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.google.inject.Inject;

public class ProposalSummaryList implements DomainEventSubscriber<ProposalEvent> {

	private JsonArray proposalSummaries = new JsonArray();
	private ClientService clientService;

	@Inject
	public ProposalSummaryList(ClientService clientService) {
		this.clientService = clientService;
	}

	@Override
	public void handle(ProposalEvent domainEvent) {
		ProposalJson proposal = domainEvent.proposal().asJson();
		Client client = clientService.findBy(new ClientId(proposal.get("clientId").asInt()));
		JsonObject proposalSummary = new JsonObject()
											.add("id", proposal.get("id").asInt())
											.add("client", new JsonObject()
													.add("id", client.id().intValue())
													.add("name", client.name()))
											.add("projectName", proposal.get("projectName").asString())
											.add("lastUpdatedOn", proposal.get("lastUpdatedOn").asString())
											.add("craftsmenInvolved", proposal.getArray("craftsmenInvolved"));
		proposalSummaries.add(proposalSummary);
	}

	public JsonArray allAsJson() {
		return proposalSummaries;
	}
}
