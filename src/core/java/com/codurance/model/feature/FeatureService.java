package com.codurance.model.feature;

import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.events.FeatureAdded;
import com.codurance.model.proposal.events.FeatureEventPublisher;
import com.google.inject.Inject;

public class FeatureService {

	private Features features;
	private FeatureEventPublisher featureEventPublisher;

	@Inject
	public FeatureService(Features features, FeatureEventPublisher featureEventPublisher) {
		this.features = features;
		this.featureEventPublisher = featureEventPublisher;
	}

	public void addFeature(ProposalId proposalId, FeatureJson newFeature) {
		newFeature.set(proposalId);
		features.add(newFeature);
		featureEventPublisher.publish(new FeatureAdded(newFeature));
	}

}
