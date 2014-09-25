package com.codurance.model.proposal;

import com.codurance.infrastructure.json.JsonEntity;
import com.eclipsesource.json.JsonObject;

public class ProposalJson extends JsonEntity {

	public static ProposalJson aProposalJsonWith(JsonEntity jsonEntity) {
		return new ProposalJson(jsonEntity.jsonObject());
	}

	public ProposalJson() {
		super();
	}

	public ProposalJson(JsonObject jsonObject) {
		super(jsonObject);
	}

}
