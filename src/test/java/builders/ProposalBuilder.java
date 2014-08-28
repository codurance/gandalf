package builders;

import com.codurance.model.proposal.ClientId;
import com.codurance.model.proposal.Contact;
import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalId;

public class ProposalBuilder {
	private ProposalId id = new ProposalId(1);
	private ClientId clientId;
	private String projectName;
	private Contact[] contacts = new Contact[] {};
	private String description;
	private String notes;

	public static ProposalBuilder aProposal() {
		return new ProposalBuilder();
	}

	public ProposalBuilder withId(int id) {
		this.id = new ProposalId(id);
		return this;
	}
	public ProposalBuilder withClientId(String id) {
		this.clientId = new ClientId(id);
		return this;
	}

	public ProposalBuilder withProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

	public ProposalBuilder withContacts(Contact... contacts) {
		this.contacts = contacts;
		return this;
	}

	public ProposalBuilder withDescription(String description) {
		this.description = description;
		return this;
	}

	public ProposalBuilder withNotes(String notes) {
		this.notes = notes;
		return this;
	}

	public Proposal build() {
		return new Proposal(id, clientId, projectName,
							contacts, description, notes);
	}
}
