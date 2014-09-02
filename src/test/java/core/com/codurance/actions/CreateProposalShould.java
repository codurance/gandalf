package core.com.codurance.actions;

import com.codurance.actions.CreateProposal;
import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.ProposalJson;
import com.codurance.model.proposal.ProposalService;
import com.eclipsesource.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateProposalShould {

	private static final ProposalJson PROPOSAL_JSON = new ProposalJson(new JsonObject());
	private static final Proposal NEW_PROPOSAL = new Proposal(new ProposalId(1));

	@Mock ProposalService proposalService;

	private CreateProposal createProposal;

	@Before
	public void initialise() {
		given(proposalService.create(PROPOSAL_JSON)).willReturn(NEW_PROPOSAL);

	    this.createProposal = new CreateProposal(proposalService);
	}

	@Test public void
	create_a_new_proposal() {
		createProposal.create(PROPOSAL_JSON);

		verify(proposalService).create(PROPOSAL_JSON);
	}

	@Test public void
	return_a_newly_created_proposal() {
		Proposal result = createProposal.create(PROPOSAL_JSON);

		assertThat(result, is(NEW_PROPOSAL));
	}

}
