package core.com.codurance.model.proposal;

import com.codurance.infrastructure.json.JsonEntity;
import com.codurance.model.proposal.*;
import com.codurance.model.proposal.events.FeatureAdded;
import com.codurance.model.proposal.events.FeatureEventPublisher;
import com.codurance.model.proposal.events.ProposalCreated;
import com.codurance.model.proposal.events.ProposalEventPublisher;
import com.codurance.model.proposal.feature.FeatureJson;
import com.eclipsesource.json.JsonObject;
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
	private static final ProposalId PROPOSAL_ID = proposalId(1);
	private static final FeatureJson NEW_FEATURE = new FeatureJson(new JsonObject());

	private ProposalJson newProposalJson;

	@Captor ArgumentCaptor<Proposal> proposalArgument;
	@Captor ArgumentCaptor<ProposalCreated> proposalEvent;
	@Captor ArgumentCaptor<FeatureAdded> featureEvent;
	@Mock Proposals proposals;
	@Mock ProposalEventPublisher proposalEventPublisher;
	@Mock FeatureEventPublisher featureEventPublisher;

	private ProposalService proposalService;

	@Before
	public void initialise() {
		this.proposalService = new ProposalService(proposals,
												   proposalEventPublisher,
												   featureEventPublisher);
		given(proposals.nextId()).willReturn(NEXT_PROPOSAL_ID);
		newProposalJson = aProposalJsonWith(new JsonEntity().add("clientId", 5));
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

		verify(proposalEventPublisher).publish(proposalEvent.capture());
		assertThat(proposalEvent.getValue().proposal(), is(proposal));
	}

	@Test public void
	should_add_a_feature_to_a_proposal() {
		proposalService.addFeatureToProposal(PROPOSAL_ID, NEW_FEATURE);

		verify(proposals).addFeatureToProposal(PROPOSAL_ID, NEW_FEATURE);
	}

	@Test public void
	should_publish_a_feature_added_event() {
		proposalService.addFeatureToProposal(PROPOSAL_ID, NEW_FEATURE);

		verify(featureEventPublisher).publish(featureEvent.capture());

	    assertThat(featureEvent.getValue().feature(), is(NEW_FEATURE));
	}

}
