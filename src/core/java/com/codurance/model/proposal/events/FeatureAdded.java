package com.codurance.model.proposal.events;

import com.codurance.model.proposal.feature.FeatureJson;

public class FeatureAdded implements FeatureEvent {

	private FeatureJson feature;

	public FeatureAdded(FeatureJson feature) {
		this.feature = feature;
	}

	@Override
	public FeatureJson feature() {
		return feature;
	}
}
