package journey.proposal;

import com.codurance.model.proposal.ProposalId;
import com.codurance.view.ProposalPage;
import com.codurance.view.ProposalsPage;
import journey.BaseJourneyTests;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProposalJourneyShould extends BaseJourneyTests {

	private ProposalsPage proposalsPage = new ProposalsPage();
	private ProposalPage proposalPage = new ProposalPage(new ProposalId("1"));

	@Test public void
	display_page_with_all_proposals() {
		navigateTo(proposalsPage.url());

		assertThat(pageTitle(), is(proposalsPage.title()));
	}

	@Test public void
	display_proposal() {
		navigateTo(proposalPage.url());

		assertThat(pageTitle(), is(proposalPage.title()));
	}

}
