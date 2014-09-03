package core.com.codurance.model.proposal.events;

import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.events.ProposalCreated;
import org.junit.Test;

import static builders.ProposalBuilder.aProposal;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProposalCreatedShould {

	private static final Proposal PROPOSAL = aProposal().build();

	@Test public void
	return_the_created_proposal() {
		ProposalCreated proposalCreated = new ProposalCreated(PROPOSAL);

	    assertThat(proposalCreated.proposal(), is(PROPOSAL));
	}
}
