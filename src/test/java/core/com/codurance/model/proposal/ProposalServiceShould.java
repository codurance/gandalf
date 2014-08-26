package core.com.codurance.model.proposal;

import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalService;
import com.codurance.model.proposal.Proposals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static builders.ProposalBuilder.aProposal;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProposalServiceShould {

	private static final Proposal NEW_PROPOSAL = aProposal().build();
	private static final Proposal NON_PERSISTED_PROPOSAL = aProposal().withId(-1).build();

	@Mock Proposals proposals;

	private ProposalService proposalService;

	@Before
	public void initialise() {
		this.proposalService = new ProposalService(proposals);
	}

	@Test public void
	create_a_new_proposal() {
		given(proposals.add(NON_PERSISTED_PROPOSAL)).willReturn(NEW_PROPOSAL);

		Proposal proposal = proposalService.create(NON_PERSISTED_PROPOSAL);

		assertThat(proposal, is(NEW_PROPOSAL));
	}

}
