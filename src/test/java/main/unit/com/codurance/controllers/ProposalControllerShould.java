package main.unit.com.codurance.controllers;

import com.codurance.actions.CreateProposal;
import com.codurance.actions.RetrieveProposal;
import com.codurance.actions.RetrieveProposals;
import com.codurance.controllers.ProposalController;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.ProposalJson;
import com.codurance.view.NewProposalPage;
import com.codurance.view.ProposalPage;
import com.codurance.view.ProposalsPage;
import main.com.codurance.controllers.TemplateRenderer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import spark.Request;
import spark.Response;

import static builders.ProposalBuilder.aProposal;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProposalControllerShould {

	private static final String PROPOSALS_PAGE = "proposals page";
	private static final String PROPOSAL_PAGE = "proposal page";
	private static final String NEW_PROPOSAL_PAGE = "new proposal page";
	private static final String PROPOSALS_JSON = "[{proposal1}, {proposal2}]";
	private static final String PROPOSAL_JSON = "{\"id\":10}";
	private static final String NEW_PROPOSAL_DATA = "{\"projectName\":\"some project\"}";
	private static final String EMPTY = "";

	private ProposalsPage proposalsPage = new ProposalsPage();

	@Mock Request request;
	@Mock Response response;
	@Mock TemplateRenderer templateRenderer;
	@Mock RetrieveProposals retrieveProposals;
	@Mock RetrieveProposal retrieveProposal;
	@Mock CreateProposal createProposal;

	private ProposalController proposalController;

	@Before
	public void initialise() {
	    proposalController = new ProposalController(templateRenderer,
			                                        retrieveProposals,
			                                        retrieveProposal,
			                                        createProposal);
	}

	@Test public void
	display_proposals_page() {
		given(templateRenderer.render(proposalsPage.template(), proposalsPage.model()))
				.willReturn(PROPOSALS_PAGE);

		String page = proposalController.displayProposals(request, response);

		assertThat(page, is(PROPOSALS_PAGE));
	}

	@Test public void
	return_list_of_proposals_in_json_format() {
		given(retrieveProposals.all()).willReturn(PROPOSALS_JSON);

		String json = proposalController.retriveAllProposals(request, response);

	    assertThat(json, is(PROPOSALS_JSON));
	}

	@Test public void
	display_proposal_for_proposal_id() {
		ProposalPage proposalPage = new ProposalPage(new ProposalId("10"));
		given(request.params(":proposalId")).willReturn("10");
		given(templateRenderer.render(proposalPage.template(), proposalPage.model()))
				.willReturn(PROPOSAL_PAGE);

		String page = proposalController.displayProposalForProposalId(request, response);

		assertThat(page, is(PROPOSAL_PAGE));
	}

	@Test public void
	display_new_proposal_page() {
		NewProposalPage newProposalPage = new NewProposalPage();
		given(templateRenderer.render(newProposalPage.template(), newProposalPage.model()))
				.willReturn(NEW_PROPOSAL_PAGE);

		String page = proposalController.displayNewProposalPage(request, response);

		assertThat(page, is(NEW_PROPOSAL_PAGE));
	}

	@Test public void
	return_proposal_in_json_format() {
		given(request.params(":proposalId")).willReturn("10");
		given(retrieveProposal.by(new ProposalId("10"))).willReturn(PROPOSAL_JSON);

		String proposalJson = proposalController.retrieveProposal(request, response);

		assertThat(proposalJson, is(PROPOSAL_JSON));
	}

	@Test public void
	redirect_to_proposal_estimations_page_after_proposal_is_created() {
		given(request.body()).willReturn(NEW_PROPOSAL_DATA);
		given(createProposal.create(any(ProposalJson.class))).willReturn(aProposal().withId(2).build());

		String result = proposalController.createProposal(request, response);

		verify(response).header("redirectURL", "/proposals/proposal/2/estimates");
	    assertThat(result, is(EMPTY));
	}

}
