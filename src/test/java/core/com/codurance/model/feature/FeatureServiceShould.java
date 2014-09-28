package core.com.codurance.model.feature;

import com.codurance.model.feature.FeatureJson;
import com.codurance.model.feature.FeatureService;
import com.codurance.model.feature.Features;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.events.FeatureEvent;
import com.codurance.model.proposal.events.FeatureEventPublisher;
import com.eclipsesource.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.codurance.model.proposal.ProposalId.proposalId;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FeatureServiceShould {

	private static final ProposalId PROPOSAL_ID = proposalId(1);
	private static final FeatureJson NEW_FEATURE = new FeatureJson(new JsonObject());

	@Mock Features features;
	@Mock FeatureEventPublisher featureEventPublisher;
	@Captor ArgumentCaptor<FeatureEvent> featureEvent;

	private FeatureService featureService;

	@Before
	public void initialise() {
	    featureService = new FeatureService(features, featureEventPublisher);
	}

	@Test public void
	set_proposal_id_to_new_feature() {
		FeatureJson new_feature = mock(FeatureJson.class);

		featureService.addFeature(PROPOSAL_ID, new_feature);

		verify(new_feature).set(PROPOSAL_ID);
	}

	@Test public void
	store_add_a_new_feature() {
		featureService.addFeature(PROPOSAL_ID, NEW_FEATURE);

		verify(features).add(PROPOSAL_ID, NEW_FEATURE);
	}

	@Test public void
	should_publish_a_feature_added_event() {
		featureService.addFeature(PROPOSAL_ID, NEW_FEATURE);

		verify(featureEventPublisher).publish(featureEvent.capture());

		assertThat(featureEvent.getValue().feature(), is(NEW_FEATURE));
	}


}
