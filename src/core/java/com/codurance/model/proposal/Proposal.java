package com.codurance.model.proposal;

import java.util.Arrays;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Proposal {

	private final ProposalId id;
	private ClientId clientId;
	private String projectName;
	private Contact[] contacts;
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
		this.contacts = contacts;
		this.description = description;
		this.notes = notes;
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
}
