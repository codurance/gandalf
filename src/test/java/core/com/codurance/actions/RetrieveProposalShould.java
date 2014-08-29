package core.com.codurance.actions;

import com.codurance.actions.RetrieveProposal;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.Proposals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveProposalShould {

	private static final String PROPOSAL = "{proposal1}";
	private static final ProposalId PROPOSAL_ID = new ProposalId(1);

	@Mock Proposals proposals;

	private RetrieveProposal retrieveProposal;

	@Before
	public void initialise() {
		retrieveProposal = new RetrieveProposal(proposals);
	}

	@Test public void
	return_all_proposals() {
		given(proposals.findById(PROPOSAL_ID)).willReturn(PROPOSAL);

		String json = retrieveProposal.by(PROPOSAL_ID);

		assertThat(json, is(PROPOSAL));
	}
}
