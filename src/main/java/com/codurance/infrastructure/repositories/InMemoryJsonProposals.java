package com.codurance.infrastructure.repositories;

import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.Proposals;
import com.codurance.model.proposal.feature.FeatureJson;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;

import java.util.stream.Stream;

import static com.codurance.infrastructure.JsonReader.jsonObject;
import static com.codurance.model.proposal.ProposalId.proposalId;
import static java.util.Spliterator.DISTINCT;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;

public class InMemoryJsonProposals implements Proposals {

	private JsonArray proposals = new JsonArray();

	@Override
	public ProposalId nextId() {
		Stream<JsonValue> jsonProposals = stream(
				spliteratorUnknownSize(proposals.iterator(), DISTINCT), false);
		Integer lastId = jsonProposals
							.map((jsonValue) -> jsonValue.asObject().get("id").asInt())
							.max(Integer::compare)
							.orElseGet(() -> Integer.valueOf(0));
		return proposalId(lastId + 1);
	}

	@Override
	public void addFeatureToProposal(ProposalId proposalId, FeatureJson newFeature) {

	}

	@Override
	public String findById(ProposalId proposalId) {
		return jsonObject("./src/main/webapp/data/proposal.json");
	}

	@Override
	public void add(Proposal newProposal) {
		proposals.add(newProposal.asJson().jsonObject());
	}

}
