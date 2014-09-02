package core.com.codurance.infrastructure.events;

import com.codurance.infrastructure.events.DomainEventSubscriber;
import com.codurance.infrastructure.events.EventPublisher;
import com.codurance.model.proposal.events.ProposalCreated;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventPublisherShould {

	@Mock DomainEventSubscriber aSubscriber;
	@Mock DomainEventSubscriber anotherSubscriber;
	@Mock ProposalCreated domainEvent;

	private EventPublisher eventPublisher;

	@Before
	public void initialise() {
	    eventPublisher = new EventPublisher(aSubscriber, anotherSubscriber);
	}

	@Test public void
	notify_subscribers_when_an_event_is_published() {
		eventPublisher.publish(domainEvent);

		verify(aSubscriber, times(1)).handle(domainEvent);
		verify(anotherSubscriber, times(1)).handle(domainEvent);
	}

}
