package core.com.codurance.model.proposal.event;

import com.codurance.model.proposal.events.ProposalCreated;
import com.codurance.model.proposal.events.ProposalEventPublisher;
import com.codurance.model.proposal.events.ProposalListUpdater;
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
