package com.codurance.model.proposal;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import static com.eclipsesource.json.JsonObject.readFrom;

public class ProposalJson {
	private JsonObject jsonObject;

	public ProposalJson() {
		this.jsonObject = new JsonObject();
	}

	public ProposalJson(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
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

	public JsonValue get(String name) {
		return jsonObject.get(name);
	}

	public String getStringOrElse(String name, String elseValue) {
		JsonValue value = get(name);
		return (value != null) ? value.asString() : elseValue;
	}

	public ProposalJson set(String name, String value) {
		return new ProposalJson(newJsonObject().set(name, value));
	}

	public ProposalJson set(String name, int value) {
		return new ProposalJson(newJsonObject().set(name, value));
	}

	private JsonObject newJsonObject() {
		return readFrom(jsonObject.toString());
	}

	public JsonArray getArray(String name) {
		JsonValue jsonValue = jsonObject.get(name);
		return (jsonValue != null) ? jsonValue.asArray() : new JsonArray();
	}

	public JsonObject jsonObject() {
		return this.jsonObject;
	}

	@Override
	public String toString() {
		return "ProposalJson{" +
				"jsonObject=" + jsonObject.toString() +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ProposalJson that = (ProposalJson) o;

		if (!jsonObject.equals(that.jsonObject)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return jsonObject.hashCode();
	}
}
