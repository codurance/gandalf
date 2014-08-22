package com.codurance.infrastructure.repositories;

import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.Proposals;

import static com.codurance.infrastructure.JsonReader.jsonArray;
import static com.codurance.infrastructure.JsonReader.jsonObject;

public class FileSystemProposals implements Proposals {
	@Override
	public String all() {
		return jsonArray("./src/main/webapp/data/proposals.json");
	}

	@Override
	public String findById(ProposalId proposalId) {
		return jsonObject("./src/main/webapp/data/proposal.json");
	}
}
