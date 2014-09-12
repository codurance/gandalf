package com.codurance.model.proposal;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.stream;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class ProposalContacts {

	private Contact[] contacts;

	public ProposalContacts() {
		this(new Contact[] {});
	}

	public ProposalContacts(Contact[] contacts) {
		this.contacts = (contacts != null) ? contacts : new Contact[] {};
	}

	public static ProposalContacts from(ProposalJson proposalJson) {
		List<Contact> contacts = new ArrayList<>();
		Iterator<JsonValue> jsonContacts = proposalJson.getArray("contacts").iterator();
		while (jsonContacts.hasNext()) {
			JsonObject jsonContact = jsonContacts.next().asObject();
			contacts.add(new Contact(jsonContact.get("name").asString(),
					jsonContact.get("email").asString()));
		}
		return new ProposalContacts(contacts.toArray(new Contact[contacts.size()]));
	}

	public JsonArray json() {
		JsonArray contactsJson = new JsonArray();
		stream(contacts).forEach(contact -> addContactJson(contactsJson, contact));
		return contactsJson;
	}

	private void addContactJson(JsonArray contactsJson, Contact contact) {
		contactsJson.add(new JsonObject()
				.add("name", contact.name())
				.add("email", contact.email()));
	}

	@Override
	public String toString() {
		return Arrays.toString(contacts);
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
