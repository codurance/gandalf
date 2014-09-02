package com.codurance.infrastructure.events;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class EventPublisher {

	private Set<DomainEventSubscriber> subscribers = new HashSet<>();

	public EventPublisher(DomainEventSubscriber... subscribers) {
		this.subscribers.addAll(asList(subscribers));
	}

	public <T> void publish(T event) {
		subscribers.stream().forEach(s -> s.handle(event));
	}

}
