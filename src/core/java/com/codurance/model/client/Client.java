package com.codurance.model.client;

import com.codurance.model.proposal.ClientId;

public class Client {

	private ClientId id;
	private String name;

	public Client(ClientId id, String name) {
		this.id = id;
		this.name = name;
	}

	public ClientId id() {
		return id;
	}

	public String name() {
		return name;
	}
}
