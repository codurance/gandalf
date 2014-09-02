package com.codurance.infrastructure.events;

import java.util.HashSet;
import java.util.Set;

public class EventPublisher {

	private static final ThreadLocal<Set<DomainEventSubscriber>> subscribers =
			new ThreadLocal<Set<DomainEventSubscriber>>() {
				@Override
				protected Set<DomainEventSubscriber> initialValue() {
					return new HashSet<>();
				}
			};

	public <T> void publish(T event) {
		subscribers.get().stream().forEach(s -> s.handle(event));
	}

	public void add(DomainEventSubscriber aSubscriber) {
		this.subscribers.get().add(aSubscriber);
	}

	/**
	 * In case the application uses a thread pool, EventPublisher must provide
	 * a way to reset its subscribers.
	 */
	public void reset() {
		this.subscribers.set(new HashSet<>());
	}
}
