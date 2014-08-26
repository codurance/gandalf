package com.codurance.model.proposal;

public class Proposal {

	private final ProposalId id;

	public Proposal(ProposalId id) {
		this.id = id;
	}

	public String client() {
		return "Footfall123";
	}

	public String project() {
		return "Salt";
	}

	public ProposalId id() {
		return id;
	}
}
