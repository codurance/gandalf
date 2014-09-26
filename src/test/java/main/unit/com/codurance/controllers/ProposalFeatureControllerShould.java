package main.unit.com.codurance.controllers;

import com.codurance.actions.AddFeature;
import com.codurance.controllers.ProposalFeatureController;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.feature.FeatureJson;
import com.codurance.view.ProposalFeaturesPage;
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

	private static final String PROPOSAL_FEATURES_PAGE = "proposal features page";
	@Mock Request request;
	@Mock Response response;
	@Mock TemplateRenderer templateRenderer;

	@Mock
	AddFeature addFeature;

	ArgumentCaptor<ProposalId> proposalIdArgument = ArgumentCaptor.forClass(ProposalId.class);
	ArgumentCaptor<FeatureJson> featureJsonArgument = ArgumentCaptor.forClass(FeatureJson.class);

	private ProposalFeatureController controller;

	@Before
	public void initialise() {
	    this.controller = new ProposalFeatureController(templateRenderer,
			    addFeature);
	}

	@Test public void
	display_proposal_features_page() {
		given(request.cookie("proposalId")).willReturn("1");
		ProposalFeaturesPage proposalFeaturesPage = new ProposalFeaturesPage(new ProposalId(1));
		given(templateRenderer.render(proposalFeaturesPage.template(), proposalFeaturesPage.model()))
				.willReturn(PROPOSAL_FEATURES_PAGE);

		String page = controller.displayProposalFeatures(request, response);

		assertThat(page, is(PROPOSAL_FEATURES_PAGE));
	}

	@Test
	public void
	add_new_feature_to_existing_proposal() {
		String featureData = new JsonObject().add("proposalId", 1)
											 .add("feature", new JsonObject().add("description", "FEATURE A"))
											 .toString();
		given(request.body()).willReturn(featureData);

		controller.addFeature(request, response);

		verify(addFeature).add(proposalIdArgument.capture(), featureJsonArgument.capture());
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
