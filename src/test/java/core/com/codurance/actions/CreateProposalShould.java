package core.com.codurance.actions;

import com.codurance.actions.CreateProposal;
import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.ProposalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.codurance.model.proposal.ProposalId.NON_PERSISTED_ID;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateProposalShould {

	private static final String PROPOSAL_JSON_DATA = "{proposal}";
	private static final Proposal NON_PERSISTED_PROPOSAL = new Proposal(NON_PERSISTED_ID);
	private static final Proposal NEW_PROPOSAL = new Proposal(new ProposalId(1));

	@Mock ProposalService proposalService;

	private CreateProposal createProposal;

	@Before
	public void initialise() {
	    this.createProposal = new CreateProposal(proposalService);
	}

	@Test public void
	create_a_new_proposal() {
		createProposal.create(PROPOSAL_JSON_DATA);

		verify(proposalService).create(NON_PERSISTED_PROPOSAL);
	}

	@Test public void
	return_a_newly_created_proposal() {
		given(proposalService.create(NON_PERSISTED_PROPOSAL)).willReturn(NEW_PROPOSAL);

		Proposal result = createProposal.create(PROPOSAL_JSON_DATA);

		assertThat(result, is(NEW_PROPOSAL));
	}
	
}
