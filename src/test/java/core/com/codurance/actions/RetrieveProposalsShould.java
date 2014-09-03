package core.com.codurance.actions;

import com.codurance.actions.RetrieveProposals;
import com.codurance.model.proposal.events.ProposalSummaryList;
import com.eclipsesource.json.JsonArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveProposalsShould {

	private static final JsonArray PROPOSALS = new JsonArray().add("proposal1");

	@Mock ProposalSummaryList proposalSummaryList;

	private RetrieveProposals retrieveProposals;

	@Before
	public void initialise() {
	    retrieveProposals = new RetrieveProposals(proposalSummaryList);
	}

	@Test public void
	return_all_proposals() {
		given(proposalSummaryList.allAsJson()).willReturn(PROPOSALS);

		String json = retrieveProposals.all();

		assertThat(json, is(PROPOSALS.toString()));
	}
}
