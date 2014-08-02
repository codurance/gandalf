package journey.projects;

import com.codurance.view.ProjectEstimatePage;
import com.codurance.view.ProjectEstimatesPage;
import journey.BaseJourneyTests;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProjectEstimationJourneyShould extends BaseJourneyTests {

	private ProjectEstimatesPage projectEstimatesPage = new ProjectEstimatesPage();
	private ProjectEstimatePage projectEstimatePage = new ProjectEstimatePage();

	@Test public void
	display_page_with_all_project_estimates() {
		navigateTo(projectEstimatesPage.url());

		assertThat(pageTitle(), is(projectEstimatesPage.title()));
	}

	@Test public void
	display_project_estimate() {
		navigateTo(projectEstimatePage.url());

		assertThat(pageTitle(), is(projectEstimatePage.title()));
	}

}
