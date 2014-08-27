package com.codurance.infrastructure.repositories;

import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.Proposals;
import com.eclipsesource.json.JsonArray;

import static com.codurance.infrastructure.JsonReader.jsonArray;
import static com.codurance.infrastructure.JsonReader.jsonArrayAsString;
import static com.codurance.infrastructure.JsonReader.jsonObject;

public class FileSystemProposals implements Proposals {

	private JsonArray proposals;

	public FileSystemProposals() {
		proposals = jsonArray("./src/main/webapp/data/proposals.json");
	}

	@Override
	public String all() {
		return jsonArrayAsString("./src/main/webapp/data/proposals.json");
	}

	@Override
	public String findById(ProposalId proposalId) {
		return jsonObject("./src/main/webapp/data/proposal.json");
	}

	@Override
	public Proposal add(Proposal nonPersistedProposal) {
		return null;
	}
}
