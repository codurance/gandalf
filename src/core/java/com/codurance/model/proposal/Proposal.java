package com.codurance.model.proposal;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Proposal {

	private final ProposalId id;
	private ClientId clientId;
	private String projectName;
	private Contact[] contacts = new Contact[] {};
	private String description;
	private String notes;

	public Proposal(ProposalId id) {
		this.id = id;
	}

	public Proposal(ProposalId id, ClientId clientId, String projectName,
	                Contact[] contacts, String description, String notes) {
		this.id = id;
		this.clientId = clientId;
		this.projectName = projectName;
		this.contacts = (contacts != null) ? contacts : new Contact[] {};
		this.description = description;
		this.notes = notes;
	}

	public Proposal(JsonObject proposalJson) {
		this.id = new ProposalId(proposalJson.get("id").asString());
		this.clientId = new ClientId(proposalJson.get("clientId").asString());
		this.projectName = proposalJson.get("projectName").asString();
		this.contacts = getContactsFrom(proposalJson);
		this.description = proposalJson.get("description").asString();
		this.notes = proposalJson.get("notes").asString();
	}

	private Contact[] getContactsFrom(JsonObject proposalJson) {
		List<Contact> contacts = new ArrayList<>();
		Iterator<JsonValue> jsonContacts = proposalJson.get("contacts").asArray().iterator();
		while (jsonContacts.hasNext()) {
			JsonObject jsonContact = jsonContacts.next().asObject();
			contacts.add(new Contact(jsonContact.get("name").asString(),
					jsonContact.get("email").asString()));
		}
		return contacts.toArray(new Contact[contacts.size()]);
	}

	public ProposalId id() {
		return id;
	}

	public ClientId clientId() {
		return clientId;
	}

	public String projectName() {
		return projectName;
	}

	public Contact[] getContacts() {
		return contacts;
	}

	public String getDescription() {
		return description;
	}

	public String getNotes() {
		return notes;
	}

	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return "Proposal{" +
				"id=" + id +
				", clientId=" + clientId +
				", projectName='" + projectName + '\'' +
				", contacts=" + Arrays.toString(contacts) +
				", description='" + description + '\'' +
				", notes='" + notes + '\'' +
				'}';
	}

	public JsonObject asJson() {
		JsonArray contactsJson = new JsonArray();
		for (Contact contact : contacts) {
				contactsJson.add(new JsonObject()
						.add("name", contact.name())
						.add("email", contact.email()));
		}
		return new JsonObject()
						.add("id", id.toString())
						.add("clientId", clientId.stringValue())
						.add("projectName", projectName)
						.add("contacts", contactsJson)
						.add("description", description)
						.add("notes", notes);
	}

	public static Proposal fromJson(JsonObject proposalJson) {
		return new Proposal(proposalJson);
	}
}
