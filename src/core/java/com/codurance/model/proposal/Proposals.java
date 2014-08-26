package com.codurance.model.proposal;

public interface Proposals {

	String all();

	String findById(ProposalId proposalId);

	Proposal add(Proposal nonPersistedProposal);
}
