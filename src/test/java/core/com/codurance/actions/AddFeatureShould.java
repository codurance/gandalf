package core.com.codurance.actions;

import com.codurance.actions.AddFeature;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.feature.FeatureJson;
import com.codurance.model.feature.FeatureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddFeatureShould {

	private ProposalId proposalId = new ProposalId(1);

	@Mock FeatureJson featureJson;
	@Mock FeatureService featureService;

	private AddFeature addFeature;

	@Before
	public void initialise() {
	    this.addFeature = new AddFeature(featureService);
	}

	@Test public void
	add_feature_to_proposal() {
		addFeature.add(proposalId, featureJson);

	    verify(featureService).addFeature(proposalId, featureJson);
	}
}
