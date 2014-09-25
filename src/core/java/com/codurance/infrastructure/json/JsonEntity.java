package com.codurance.infrastructure.json;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.eclipsesource.json.JsonObject.readFrom;
import static java.time.LocalDate.parse;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class JsonEntity {

	private final JsonObject jsonObject;

	public JsonEntity() {
		this(new JsonObject());
	}

	public JsonEntity(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public JsonValue get(String name) {
		return jsonObject.get(name);
	}

	public String getStringOrElse(String name, String elseValue) {
		JsonValue value = get(name);
		return (value != null) ? value.asString() : elseValue;
	}

	public LocalDate getDateOrElse(String name, DateTimeFormatter dateTimeFormat, LocalDate elseValue) {
		JsonValue value = get(name);
		return (value != null) ? parse(value.asString(), dateTimeFormat) : elseValue;
	}

	protected JsonObject newJsonObject() {
		return readFrom(jsonObject.toString());
	}

	public JsonArray getArray(String name) {
		JsonValue jsonValue = jsonObject.get(name);
		return (jsonValue != null) ? jsonValue.asArray() : new JsonArray();
	}

	public JsonObject jsonObject() {
		return this.jsonObject;
	}

	public JsonEntity add(String name, String value) {
		return new JsonEntity(newJsonObject().add(name, value));
	}

	public JsonEntity add(String name, int value) {
		return new JsonEntity(newJsonObject().add(name, value));
	}

	public JsonEntity add(String name, JsonArray jsonArray) {
		return new JsonEntity(newJsonObject().add(name, jsonArray));
	}

	public JsonEntity set(String name, String value) {
		return new JsonEntity(newJsonObject().set(name, value));
	}

	public JsonEntity set(String name, int value) {
		return new JsonEntity(newJsonObject().set(name, value));
	}

	@Override
	public String toString() {
		return "ProposalJson{" +
				"jsonObject=" + jsonObject.toString() +
				'}';
	}

	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

}
