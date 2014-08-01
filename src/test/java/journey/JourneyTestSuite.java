package journey;

import com.codurance.GandalfLauncher;
import journey.projects.ProjectEstimateBaseJourneyShould;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ProjectEstimateBaseJourneyShould.class
})
public class JourneyTestSuite {

	@BeforeClass
	public static void startGandalf() {
		GandalfLauncher.gandalf().run();
	}

}
