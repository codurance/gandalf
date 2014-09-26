package com.codurance.model.feature;

import com.codurance.infrastructure.json.JsonEntity;
import com.codurance.model.proposal.ProposalId;
import com.eclipsesource.json.JsonObject;

public class FeatureJson extends JsonEntity {

	public FeatureJson(JsonObject jsonObject) {
		super(jsonObject);
	}

	public void set(ProposalId proposalId) {
		jsonObject().set("proposalId", proposalId.intValue());
	}
}
