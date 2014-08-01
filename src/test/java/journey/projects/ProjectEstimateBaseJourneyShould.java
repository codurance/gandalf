package journey.projects;

import com.codurance.page_objects.ProjectEstimatesPage;
import journey.BaseJourneyTests;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProjectEstimateBaseJourneyShould extends BaseJourneyTests {

	private ProjectEstimatesPage projectEstimatesPage = new ProjectEstimatesPage();

	@Test public void
	display_page_with_all_project_estimates() {
		navigateTo(projectEstimatesPage.url());

		assertThat(pageTitle(), is(projectEstimatesPage.pageTitle()));
	}

}
