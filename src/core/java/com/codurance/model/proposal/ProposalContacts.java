package com.codurance.model.proposal;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.ArrayList;
import java.util.Iterator;
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
		List<Contact> contacts = new ArrayList<>();
		Iterator<JsonValue> jsonContacts = proposalJson.getArray("contacts").iterator();
		jsonContacts.forEachRemaining((JsonValue jsonContact) ->
				contacts.add(new Contact(jsonContact.asObject().get("name").asString(),
										 jsonContact.asObject().get("email").asString())));
		return new ProposalContacts(contacts.toArray(new Contact[contacts.size()]));
	}

	public JsonArray json() {
		JsonArray contactsJson = new JsonArray();
		contacts.forEach(contact -> addContactJson(contactsJson, contact));
		return contactsJson;
	}

	private void addContactJson(JsonArray contactsJson, Contact contact) {
		contactsJson.add(new JsonObject()
				.add("name", contact.name())
				.add("email", contact.email()));
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
