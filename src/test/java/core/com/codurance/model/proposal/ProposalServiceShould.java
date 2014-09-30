package core.com.codurance.model.proposal;

import com.codurance.infrastructure.json.JsonEntity;
import com.codurance.model.proposal.*;
import com.codurance.model.proposal.events.FeatureAdded;
import com.codurance.model.proposal.events.ProposalCreated;
import com.codurance.model.proposal.events.ProposalEventPublisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.codurance.model.proposal.ProposalId.proposalId;
import static com.codurance.model.proposal.ProposalJson.aProposalJsonWith;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProposalServiceShould {

	private static final ProposalId NEXT_PROPOSAL_ID = proposalId(11);

	private ProposalJson newProposalJson;

	@Captor ArgumentCaptor<Proposal> proposalArgument;
	@Captor ArgumentCaptor<ProposalCreated> proposalEvent;
	@Captor ArgumentCaptor<FeatureAdded> featureEvent;
	@Mock Proposals proposals;
	@Mock ProposalEventPublisher proposalEventPublisher;

	private ProposalService proposalService;

	@Before
	public void initialise() {
		this.proposalService = new ProposalService(proposals,
												   proposalEventPublisher);
		given(proposals.nextId()).willReturn(NEXT_PROPOSAL_ID);
		newProposalJson = aProposalJsonWith(new JsonEntity().add("clientId", 5));
	}

	@Test public void
	store_a_new_proposal() {
		proposalService.create(newProposalJson);

	    verify(proposals).add(captureNewProposal());
		assertThat(newProposal().id(), is(NEXT_PROPOSAL_ID));
	}

	@Test public void
	create_a_new_id_for_a_new_proposal() {
		Proposal proposal = proposalService.create(newProposalJson);

		verify(proposals).nextId();
		assertThat(proposal.id(), is(NEXT_PROPOSAL_ID));
	}

	@Test public void
	publish_a_proposal_created_event() {
		Proposal proposal = proposalService.create(newProposalJson);

		verify(proposalEventPublisher).publish(proposalEvent.capture());
		assertThat(proposalEvent.getValue().proposal(), is(proposal));
	}

	private Proposal newProposal() {
		return proposalArgument.getValue();
	}

	private Proposal captureNewProposal() {
		return proposalArgument.capture();
	}

}
