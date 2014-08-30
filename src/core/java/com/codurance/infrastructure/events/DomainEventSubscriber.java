package com.codurance.infrastructure.events;

public interface DomainEventSubscriber<T> {

	void handle(T domainEvent);

}
