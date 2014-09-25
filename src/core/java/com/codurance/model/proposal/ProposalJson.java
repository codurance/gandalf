package com.codurance.model.proposal;

import com.codurance.infrastructure.json.JsonEntity;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

public class ProposalJson extends JsonEntity {

	public ProposalJson() {
		super();
	}

	public ProposalJson(JsonObject jsonObject) {
		super(jsonObject);
	}

	public ProposalJson add(String name, String value) {
		return new ProposalJson(newJsonObject().add(name, value));
	}

	public ProposalJson add(String name, int value) {
		return new ProposalJson(newJsonObject().add(name, value));
	}

	public ProposalJson add(String name, JsonArray jsonArray) {
		return new ProposalJson(newJsonObject().add(name, jsonArray));
	}

	public ProposalJson set(String name, String value) {
		return new ProposalJson(newJsonObject().set(name, value));
	}

	public ProposalJson set(String name, int value) {
		return new ProposalJson(newJsonObject().set(name, value));
	}

}
