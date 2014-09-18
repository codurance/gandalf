package com.codurance.model.proposal.feature;

import com.eclipsesource.json.JsonObject;

public class FeatureJson {
	private JsonObject featureJson;

	public FeatureJson(JsonObject featureJson) {
		this.featureJson = featureJson;
	}

	public String getAsString(String name) {
		return featureJson.get(name).asString();
	}
}
