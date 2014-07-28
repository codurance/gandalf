package journey.projects;

import com.codurance.page_objects.ProjectEstimationsPage;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProjectEstimationJourneyShould extends JourneyTests {

	private ProjectEstimationsPage projectEstimationsPage = new ProjectEstimationsPage();

	@Test public void
	display_page_with_all_project_estimations() {
		navigateTo(projectEstimationsPage.url());

		assertThat(pageTitle(), is(projectEstimationsPage.pageTile()));
	}

}
