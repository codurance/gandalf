package com.codurance.model.proposal;

public interface Proposals {

	String findById(ProposalId proposalId);

	void add(Proposal nonPersistedProposal);

	ProposalId nextId();

}
