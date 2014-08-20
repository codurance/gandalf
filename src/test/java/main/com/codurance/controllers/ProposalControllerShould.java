package main.com.codurance.controllers;

import com.codurance.controllers.ProposalController;
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

}
