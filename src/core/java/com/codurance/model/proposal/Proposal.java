package com.codurance.model.proposal;

import com.codurance.model.craftman.Craftsman;
import com.codurance.model.craftman.CraftsmanId;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.time.LocalDate.now;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Proposal {

	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

	private final ProposalId id;
	private Craftsman[] craftsmen = new Craftsman[] {};
	private LocalDate createdOn = now();
	private ClientId clientId;
	private String projectName;
	private LocalDate lastUpdatedOn;
	private Contact[] contacts = new Contact[] {};
	private String description;
	private String notes;

	public Proposal(ProposalId id) {
		this.id = id;
	}

	public Proposal(ProposalId id, ClientId clientId, String projectName,
	                Contact[] contacts, Craftsman[] craftsmen, String description, String notes,
	                LocalDate createdOn, LocalDate lastUpdatedOn) {
		this.id = id;
		this.clientId = clientId;
		this.projectName = projectName;
		this.createdOn = createdOn;
		this.lastUpdatedOn = lastUpdatedOn;
		this.contacts = (contacts != null) ? contacts : new Contact[] {};
		this.craftsmen = (craftsmen != null) ? craftsmen : new Craftsman[] {};
		this.description = description;
		this.notes = notes;
	}

	public Proposal(ProposalJson proposalJson) {
		this.id = new ProposalId(proposalJson.get("id").asInt());
		this.clientId = new ClientId(proposalJson.get("clientId").asInt());
		this.projectName = proposalJson.getStringOrElse("projectName", "");
		this.contacts = getContactsFrom(proposalJson);
		this.craftsmen = getCraftsmenFrom(proposalJson);
		this.description = proposalJson.getStringOrElse("description", "");
		this.notes = proposalJson.getStringOrElse("notes", "");
		this.createdOn = proposalJson.getDateOrElse("createdOn", DATE_TIME_FORMATTER, now());
		this.lastUpdatedOn = proposalJson.getDateOrElse("lastUpdatedOn", DATE_TIME_FORMATTER, now());
	}

	private Craftsman[] getCraftsmenFrom(ProposalJson proposalJson) {
		List<Craftsman> craftsmen = new ArrayList<>();
		Iterator<JsonValue> jsonCraftsmen = proposalJson.getArray("craftsmenInvolved").iterator();
		while (jsonCraftsmen.hasNext()) {
			JsonObject jsonCraftsman = jsonCraftsmen.next().asObject();
			craftsmen.add(new Craftsman(new CraftsmanId(jsonCraftsman.get("id").asInt()),
										jsonCraftsman.get("name").asString()));
		}
		return craftsmen.toArray(new Craftsman[craftsmen.size()]);
	}

	private Contact[] getContactsFrom(ProposalJson proposalJson) {
		List<Contact> contacts = new ArrayList<>();
		Iterator<JsonValue> jsonContacts = proposalJson.getArray("contacts").iterator();
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

	public String getDescription() {
		return description;
	}

	public ProposalJson asJson() {
		return new ProposalJson(new JsonObject()
									.add("id", id.intValue())
									.add("clientId", clientId.intValue())
									.add("projectName", projectName)
									.add("contacts", contactsJson())
									.add("craftsmenInvolved", craftsmenJson())
									.add("description", description)
									.add("notes", notes)
									.add("createdOn", createdOn.format(DATE_TIME_FORMATTER))
									.add("lastUpdatedOn", lastUpdatedOn.format(DATE_TIME_FORMATTER)));
	}

	private JsonArray craftsmenJson() {
		JsonArray craftsmenJson = new JsonArray();
		for (Craftsman craftsman : craftsmen) {
			addCraftsmanJson(craftsmenJson, craftsman);
		}
		return craftsmenJson;
	}

	private JsonArray contactsJson() {
		JsonArray contactsJson = new JsonArray();
		for (Contact contact : contacts) {
			addContactJson(contactsJson, contact);
		}
		return contactsJson;
	}

	private void addCraftsmanJson(JsonArray craftsmenJson, Craftsman craftsman) {
		craftsmenJson.add(new JsonObject()
				.add("id", craftsman.id().intValue())
				.add("name", craftsman.name()));
	}

	private void addContactJson(JsonArray contactsJson, Contact contact) {
		contactsJson.add(new JsonObject()
				.add("name", contact.name())
				.add("email", contact.email()));
	}

	public static Proposal fromJson(ProposalJson proposalJson) {
		return new Proposal(proposalJson);
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
				", craftsmenInvolved=" + Arrays.toString(craftsmen) +
				", description='" + description + '\'' +
				", notes='" + notes + '\'' +
				'}';
	}

}
