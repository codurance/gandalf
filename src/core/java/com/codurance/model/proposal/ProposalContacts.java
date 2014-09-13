package com.codurance.model.proposal;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class ProposalContacts {

	private List<Contact> contacts = new ArrayList<>();

	public ProposalContacts() {
		this(new Contact[] {});
	}

	public ProposalContacts(Contact[] contacts) {
		if (contacts != null) {
			this.contacts = asList(contacts);
		}
	}

	public static ProposalContacts from(ProposalJson proposalJson) {
		return new ProposalContacts(
						proposalJson.getArray("contacts").values().stream()
									.map((JsonValue json) -> contactFrom(json))
									.toArray(size -> new Contact[size]));
	}

	public JsonArray json() {
		JsonArray contactsJson = new JsonArray();
		contacts.stream()
				.map((Contact contact) -> jsonFor(contact))
				.forEach((JsonObject contactJson) -> contactsJson.add(contactJson));
		return contactsJson;
	}

	private JsonObject jsonFor(Contact contact) {
		return new JsonObject()
						.add("name", contact.name())
						.add("email", contact.email());
	}

	private static Contact contactFrom(JsonValue jsonContact) {
		return new Contact(jsonContact.asObject().get("name").asString(),
						   jsonContact.asObject().get("email").asString());
	}

	@Override
	public String toString() {
		return contacts.toString();
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
