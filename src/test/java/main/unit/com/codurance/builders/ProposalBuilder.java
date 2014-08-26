package main.unit.com.codurance.builders;

import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalId;

public class ProposalBuilder {
	private ProposalId id = new ProposalId(1);

	public static ProposalBuilder aProposal() {
		return new ProposalBuilder();
	}

	public ProposalBuilder withId(int id) {
		this.id = new ProposalId(id);
		return this;
	}

	public Proposal build() {
		return new Proposal(id);
	}
}
