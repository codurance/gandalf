package com.codurance.actions;

import com.codurance.model.feature.FeatureService;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.feature.FeatureJson;
import com.google.inject.Inject;

public class AddFeature {

	private FeatureService featureService;

	@Inject
	public AddFeature(FeatureService featureService) {
		this.featureService = featureService;
	}

	public void add(ProposalId proposalId, FeatureJson featureJson) {
		featureService.addFeature(proposalId, featureJson);
	}
}
