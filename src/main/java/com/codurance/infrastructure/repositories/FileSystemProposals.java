package com.codurance.infrastructure.repositories;

import com.codurance.model.proposal.Proposals;

import static com.codurance.infrastructure.JsonReader.jsonArray;

public class FileSystemProposals implements Proposals {
	@Override
	public String all() {
		return jsonArray("./src/main/webapp/data/proposals.json");
	}
}
