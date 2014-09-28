package com.codurance.infrastructure.repositories;

import com.codurance.model.feature.FeatureJson;
import com.codurance.model.feature.Features;
import com.codurance.model.proposal.ProposalId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryJsonFeatures implements Features {

	private Map<ProposalId, List<FeatureJson>> featuresByProposal = new HashMap<>();

	@Override
	public void add(ProposalId proposalId, FeatureJson feature) {
		featuresFor(proposalId).add(feature);
	}

	private List<FeatureJson> featuresFor(ProposalId proposalId) {
		return featuresByProposal.getOrDefault(proposalId, new ArrayList<>());
	}
}
