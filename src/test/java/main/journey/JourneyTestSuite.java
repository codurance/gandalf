package main.journey;

import com.codurance.GandalfLauncher;
import main.journey.proposal.ProposalJourneyShould;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ProposalJourneyShould.class
})
public class JourneyTestSuite {

	private static GandalfLauncher launcher;

	@BeforeClass
	public static void startGandalf() {
		new Thread() {
			public void run() {
				launcher = new GandalfLauncher();
				launcher.start();
				launcher.join();
			}
		}.start();
	}

}
