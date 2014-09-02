package core.com.codurance.model.proposal;

import com.codurance.infrastructure.events.EventPublisher;
import com.codurance.model.proposal.*;
import com.codurance.model.proposal.events.ProposalCreated;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.codurance.model.proposal.ProposalId.proposalId;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProposalServiceShould {

	private static final ProposalId NEXT_PROPOSAL_ID = proposalId(11);

	private ProposalJson newProposalJson;

	@Captor ArgumentCaptor<Proposal> proposalArgument;
	@Captor ArgumentCaptor<ProposalCreated> event;
	@Mock Proposals proposals;
	@Mock EventPublisher eventPublisher;

	private ProposalService proposalService;

	@Before
	public void initialise() {
		this.proposalService = new ProposalService(proposals, eventPublisher);
		given(proposals.nextId()).willReturn(NEXT_PROPOSAL_ID);
		newProposalJson = new ProposalJson().add("clientId", 5);
	}

	@Test public void
	should_store_a_new_proposal() {
		proposalService.create(newProposalJson);

	    verify(proposals).add(proposalArgument.capture());
		assertThat(proposalArgument.getValue().id(), is(NEXT_PROPOSAL_ID));
	}

	@Test public void
	create_a_new_id_for_a_new_proposal() {
		Proposal proposal = proposalService.create(newProposalJson);

		verify(proposals).nextId();
		assertThat(proposal.id(), is(NEXT_PROPOSAL_ID));
	}

	@Test public void
	should_publish_a_proposal_created_event() {
		Proposal proposal = proposalService.create(newProposalJson);

		verify(eventPublisher).publish(event.capture());
		assertThat(event.getValue().proposalId(), is(proposal.id()));
	}

}
