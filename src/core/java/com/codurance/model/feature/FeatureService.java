package com.codurance.model.feature;

import com.codurance.model.proposal.ProposalId;
import com.google.inject.Inject;

public class FeatureService {

	private Features features;

	@Inject
	public FeatureService(Features features) {
		this.features = features;
	}

	public void addFeature(ProposalId proposalId, FeatureJson newFeature) {
		newFeature.set(proposalId);
		features.add(newFeature);
//		featureEventPublisher.publish(new FeatureAdded(newFeature));
	}

}
