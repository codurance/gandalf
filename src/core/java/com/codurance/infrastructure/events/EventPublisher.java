package com.codurance.infrastructure.events;

import java.util.HashSet;
import java.util.Set;

public class EventPublisher {

	private Set<DomainEventSubscriber> subscribers = new HashSet<>();

	public <T> void publish(T event) {
		subscribers.stream().forEach(s -> s.handle(event));
	}

	public void add(DomainEventSubscriber aSubscriber) {
		this.subscribers.add(aSubscriber);
	}

}
