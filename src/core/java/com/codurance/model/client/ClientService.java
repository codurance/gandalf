package com.codurance.model.client;

import com.codurance.model.proposal.ClientId;

public class ClientService {
	public Client findBy(ClientId clientId) {
		return new Client(new ClientId(1), "Client 1");
	}
}
