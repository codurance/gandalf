package com.codurance.infrastructure.events;

import java.util.Set;

import static java.lang.Boolean.FALSE;

public class EventPublisher {

	private static final ThreadLocal<Set> subscribers = new ThreadLocal<>();

	private static final ThreadLocal<Boolean> publishing = new ThreadLocal<Boolean>() {
		@Override
		protected Boolean initialValue() {
			return FALSE;
		}
	};

	public <T> void publish(T event) {

	}

	public void add(DomainEventSubscriber aSubscriber) {


	}
}
