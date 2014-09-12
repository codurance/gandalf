package com.codurance.model.proposal;

import com.codurance.model.craftsman.Craftsman;
import com.eclipsesource.json.JsonObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.now;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Proposal {

	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

	private final ProposalId id;
	private ProposalCraftsmen proposalCraftsmen = new ProposalCraftsmen();
	private ProposalContacts proposalContacts = new ProposalContacts();
	private LocalDate createdOn = now();
	private ClientId clientId;
	private String projectName;
	private LocalDate lastUpdatedOn;
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
		this.proposalContacts = new ProposalContacts(contacts);
		this.proposalCraftsmen = new ProposalCraftsmen(craftsmen);
		this.description = description;
		this.notes = notes;
	}

	public Proposal(ProposalJson proposalJson) {
		this.id = new ProposalId(proposalJson.get("id").asInt());
		this.clientId = new ClientId(proposalJson.get("clientId").asInt());
		this.projectName = proposalJson.getStringOrElse("projectName", "");
		this.proposalContacts = ProposalContacts.from(proposalJson);
		this.proposalCraftsmen = ProposalCraftsmen.from(proposalJson);
		this.description = proposalJson.getStringOrElse("description", "");
		this.notes = proposalJson.getStringOrElse("notes", "");
		this.createdOn = proposalJson.getDateOrElse("createdOn", DATE_TIME_FORMATTER, now());
		this.lastUpdatedOn = proposalJson.getDateOrElse("lastUpdatedOn", DATE_TIME_FORMATTER, now());
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
									.add("contacts", proposalContacts.json())
									.add("craftsmenInvolved", proposalCraftsmen.json())
									.add("description", description)
									.add("notes", notes)
									.add("createdOn", createdOn.format(DATE_TIME_FORMATTER))
									.add("lastUpdatedOn", lastUpdatedOn.format(DATE_TIME_FORMATTER)));
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
				", contacts=" + proposalContacts.toString() +
				", craftsmenInvolved=" + proposalCraftsmen.toString() +
				", description='" + description + '\'' +
				", notes='" + notes + '\'' +
				'}';
	}

}
