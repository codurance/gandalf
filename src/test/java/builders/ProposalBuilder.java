package builders;

import com.codurance.model.craftman.Craftsman;
import com.codurance.model.proposal.ClientId;
import com.codurance.model.proposal.Contact;
import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalId;

import java.time.LocalDate;

import static java.time.LocalDate.now;

public class ProposalBuilder {
	private ProposalId id = new ProposalId(1);
	private ClientId clientId;
	private String projectName;
	private Contact[] contacts = new Contact[] {};
	private String description;
	private String notes;
	private LocalDate lastUpdatedOn = now();
	private LocalDate createdOn = now();
	private Craftsman[] craftsmen = new Craftsman[] {};

	public static ProposalBuilder aProposal() {
		return new ProposalBuilder();
	}

	public ProposalBuilder withId(int id) {
		this.id = new ProposalId(id);
		return this;
	}

	public ProposalBuilder withClientId(int id) {
		this.clientId = new ClientId(id);
		return this;
	}

	public ProposalBuilder lastUpdatedOn(LocalDate date) {
		this.lastUpdatedOn = date;
		return this;
	}

	public ProposalBuilder createdOn(LocalDate date) {
		this.createdOn = date;
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

	public ProposalBuilder withCraftsmenInvolved(Craftsman... craftsmen) {
		this.craftsmen = craftsmen;
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
		return new Proposal(id,
							clientId,
							projectName,
							contacts,
							craftsmen,
							description,
							notes,
							createdOn,
							lastUpdatedOn);
	}
}
