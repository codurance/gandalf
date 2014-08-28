package core.com.codurance.model.proposal;

import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalJson;
import com.codurance.model.proposal.ProposalService;
import com.codurance.model.proposal.Proposals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static builders.ProposalBuilder.aProposal;
import static com.codurance.model.proposal.ProposalId.proposalId;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProposalServiceShould {

	private static final String NEW_PROPOSAL_ID_VALUE = "10";

	private static final Proposal NEW_PROPOSAL = aProposal().build();
	private static final ProposalJson NON_PERSISTED_PROPOSAL =
											new ProposalJson()
													.add("id", NEW_PROPOSAL_ID_VALUE)
													.add("clientId", "5");

	@Captor ArgumentCaptor<Proposal> proposalArgument;

	@Mock Proposals proposals;

	private ProposalService proposalService;

	@Before
	public void initialise() {
		this.proposalService = new ProposalService(proposals);
	}

	@Test public void
	should_store_a_new_proposal() {
		proposalService.create(NON_PERSISTED_PROPOSAL);

	    verify(proposals).add(proposalArgument.capture());
		assertThat(proposalArgument.getValue().id(), is(proposalId(NEW_PROPOSAL_ID_VALUE)));
	}

	@Ignore
	@Test public void
	create_a_new_id_for_a_new_proposal() {
		proposalService.create(NON_PERSISTED_PROPOSAL);

	    verify(NON_PERSISTED_PROPOSAL).set("id", NEW_PROPOSAL_ID_VALUE);
	}

	@Ignore
	@Test public void
	create_a_new_proposal() {
		Proposal proposal = proposalService.create(NON_PERSISTED_PROPOSAL);

		verify(proposals).add(proposalArgument.capture());
		assertThat(proposalArgument.getValue().id(), is(proposal.id()));
		assertThat(proposal.id(), is(proposalId(NEW_PROPOSAL_ID_VALUE)));
	}

}
