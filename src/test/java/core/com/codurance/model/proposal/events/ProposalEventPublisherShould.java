package core.com.codurance.model.proposal.events;

import com.codurance.model.proposal.events.ProposalCreated;
import com.codurance.model.proposal.events.ProposalEventPublisher;
import com.codurance.model.proposal.events.ProposalSummaryList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProposalEventPublisherShould {

	@Mock
	ProposalSummaryList proposalSummaryList;
	@Mock ProposalCreated proposalEvent;

	private ProposalEventPublisher eventPublisher;

	@Before
	public void initialise() {
		eventPublisher = new ProposalEventPublisher(proposalSummaryList);
	}

	@Test
	public void
	notify_proposal_event_subscribers() {
		eventPublisher.publish(proposalEvent);

		verify(proposalSummaryList).handle(proposalEvent);
	}

}
