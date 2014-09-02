package core.com.codurance.model.proposal;

import com.codurance.model.proposal.ProposalCreated;
import com.codurance.model.proposal.ProposalEventPublisher;
import com.codurance.model.proposal.ProposalListUpdater;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProposalEventPublisherShould {

	@Mock ProposalListUpdater proposalListUpdater;
	@Mock ProposalCreated proposalEvent;

	private ProposalEventPublisher eventPublisher;

	@Before
	public void initialise() {
		eventPublisher = new ProposalEventPublisher(proposalListUpdater);
	}

	@Test
	public void
	notify_proposal_event_subscribers() {
		eventPublisher.publish(proposalEvent);

		verify(proposalListUpdater).handle(proposalEvent);
	}

}
