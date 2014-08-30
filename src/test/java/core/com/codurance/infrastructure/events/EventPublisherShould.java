package core.com.codurance.infrastructure.events;

import com.codurance.infrastructure.events.DomainEventSubscriber;
import com.codurance.infrastructure.events.EventPublisher;
import com.codurance.model.proposal.ProposalCreated;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventPublisherShould {

	@Mock DomainEventSubscriber subscriber;
	@Mock ProposalCreated domainEvent;

	private EventPublisher eventPublisher;

	@Before
	public void initialise() {
	    eventPublisher = new EventPublisher();
	}

	@Test public void
	notify_a_subscriber_when_an_event_is_published() {
		eventPublisher.add(subscriber);

		eventPublisher.publish(domainEvent);

		verify(subscriber).handle(domainEvent);
	} 
}
