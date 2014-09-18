package main.unit.com.codurance.controllers;

import com.codurance.actions.AddFeatureToProposal;
import com.codurance.controllers.ProposalFeatureController;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.feature.FeatureJson;
import com.eclipsesource.json.JsonObject;
import main.com.codurance.controllers.TemplateRenderer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import spark.Request;
import spark.Response;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProposalFeatureControllerShould {

	@Mock Request request;
	@Mock Response response;
	@Mock TemplateRenderer templateRenderer;

	@Mock AddFeatureToProposal addFeatureToProposal;

	ArgumentCaptor<ProposalId> proposalIdArgument = ArgumentCaptor.forClass(ProposalId.class);
	ArgumentCaptor<FeatureJson> featureJsonArgument = ArgumentCaptor.forClass(FeatureJson.class);

	private ProposalFeatureController controller;

	@Before
	public void initialise() {
	    this.controller = new ProposalFeatureController(templateRenderer,
			                                            addFeatureToProposal);
	}

	@Test
	public void
	add_new_feature_to_existing_proposal() {
		String featureData = new JsonObject().add("proposalId", 1)
											 .add("feature", new JsonObject().add("description", "FEATURE A"))
											 .toString();
		given(request.body()).willReturn(featureData);

		controller.addFeature(request, response);

		verify(addFeatureToProposal).addFeature(proposalIdArgument.capture(), featureJsonArgument.capture());
		assertThat(proposalIdAsInt(), is(1));
		assertThat(featureDescription(), is("FEATURE A"));
	}

	private int proposalIdAsInt() {
		return proposalIdArgument.getValue().intValue();
	}

	private String featureDescription() {
		return featureJsonArgument.getValue().getAsString("description");
	}

}
