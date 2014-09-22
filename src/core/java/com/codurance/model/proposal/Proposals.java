package com.codurance.model.proposal;

import com.codurance.model.proposal.feature.FeatureJson;

public interface Proposals {

	String findById(ProposalId proposalId);

	void add(Proposal nonPersistedProposal);

	ProposalId nextId();

	void addFeatureToProposal(ProposalId proposalId, FeatureJson newFeature);
}
