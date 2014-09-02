package main.journey.proposal;

import com.codurance.model.proposal.ProposalId;
import com.codurance.view.NewProposalPage;
import com.codurance.view.ProposalPage;
import com.codurance.view.ProposalsPage;
import main.journey.BaseJourneyTests;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProposalJourneyShould extends BaseJourneyTests {

	private ProposalsPage proposalsPage = new ProposalsPage();
	private ProposalPage proposalPage = new ProposalPage(new ProposalId(1));
	private NewProposalPage newProposalPage = new NewProposalPage();

	@Test public void
	display_page_with_all_proposals() {
		navigateTo(proposalsPage.url());

		assertThat(pageTitle(), is(proposalsPage.title()));
	}

	@Test public void
	display_proposal() {
		String proposalPageURL = proposalPage.URL.replace(":proposalId", "1");
		navigateTo(proposalPageURL);

		assertThat(pageTitle(), is(proposalPage.title()));
	}

	@Test public void
	display_new_proposal_page() {
		navigateTo(newProposalPage.url());

	    assertThat(pageTitle(), is(newProposalPage.title()));
	}

}
