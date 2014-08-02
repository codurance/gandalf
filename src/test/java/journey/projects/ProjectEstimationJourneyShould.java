package journey.projects;

import com.codurance.view.ProposalPage;
import com.codurance.view.ProposalsPage;
import journey.BaseJourneyTests;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProjectEstimationJourneyShould extends BaseJourneyTests {

	private ProposalsPage proposalsPage = new ProposalsPage();
	private ProposalPage proposalPage = new ProposalPage();

	@Test public void
	display_page_with_all_project_estimates() {
		navigateTo(proposalsPage.url());

		assertThat(pageTitle(), is(proposalsPage.title()));
	}

	@Test public void
	display_project_estimate() {
		navigateTo(proposalPage.url());

		assertThat(pageTitle(), is(proposalPage.title()));
	}

}
