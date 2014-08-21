package main.com.codurance.controllers;

import com.codurance.controllers.ProposalController;
import com.codurance.model.proposal.ProposalId;
import com.codurance.view.NewProposalPage;
import com.codurance.view.ProposalPage;
import com.codurance.view.ProposalsPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import spark.Request;
import spark.Response;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProposalControllerShould {

	private static final String PROPOSALS_PAGE = "proposals page";
	private static final String PROPOSAL_PAGE = "proposal page";
	private static final String NEW_PROPOSAL_PAGE = "new proposal page";

	private ProposalsPage proposalsPage = new ProposalsPage();

	@Mock Request request;
	@Mock Response response;
	@Mock TemplateRenderer templateRenderer;

	private ProposalController proposalController;

	@Before
	public void initialise() {
	    proposalController = new ProposalController(templateRenderer);
	}

	@Test public void
	display_proposals_page() {
		given(templateRenderer.render(proposalsPage.template(), proposalsPage.model()))
				.willReturn(PROPOSALS_PAGE);

		String page = proposalController.displayProposals(request, response);

		assertThat(page, is(PROPOSALS_PAGE));
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

}
