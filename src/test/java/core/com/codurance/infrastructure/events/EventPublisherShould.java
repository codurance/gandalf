package core.com.codurance.infrastructure.events;

import com.codurance.infrastructure.events.DomainEventSubscriber;
import com.codurance.infrastructure.events.EventPublisher;
import com.codurance.model.proposal.ProposalCreated;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventPublisherShould {

	@Mock DomainEventSubscriber subscriber1;
	@Mock DomainEventSubscriber subscriber2;
	@Mock ProposalCreated domainEvent;

	private EventPublisher eventPublisher;

	@Before
	public void initialise() {
	    eventPublisher = new EventPublisher();
	}

	@Test public void
	notify_subscribers_when_an_event_is_published() {
		eventPublisher.add(subscriber1);
		eventPublisher.add(subscriber2);

		eventPublisher.publish(domainEvent);

		verify(subscriber1, times(1)).handle(domainEvent);
		verify(subscriber2, times(1)).handle(domainEvent);
	}
}
