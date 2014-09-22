package core.com.codurance.actions;

import com.codurance.actions.AddFeatureToProposal;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.ProposalService;
import com.codurance.model.proposal.feature.FeatureJson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddFeatureToProposalShould {

	private ProposalId proposalId = new ProposalId(1);

	@Mock FeatureJson featureJson;
	@Mock ProposalService proposalService;

	private AddFeatureToProposal addFeatureToProposal;

	@Before
	public void initialise() {
	    this.addFeatureToProposal = new AddFeatureToProposal(proposalService);
	}

	@Test public void
	add_feature_to_proposal() {
		addFeatureToProposal.addFeature(proposalId, featureJson);

	    verify(proposalService).addFeatureToProposal(proposalId, featureJson);
	}
}
