package com.codurance.model.feature;

import com.codurance.model.proposal.ProposalId;

public interface Features {
	void add(ProposalId proposalId, FeatureJson feature);
}
