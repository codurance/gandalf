package com.codurance.model.proposal;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.codurance.model.proposal.ProposalId.NON_PERSISTED_ID;
import static com.eclipsesource.json.JsonObject.readFrom;

public class ProposalFactory {

	public Proposal createProposalFrom(String proposalJsonData) {
		JsonObject jsonObject = readFrom(proposalJsonData);
		List<Contact> contacts = new ArrayList<>();
		Iterator<JsonValue> jsonContacts = jsonObject.get("contacts").asArray().iterator();
		while (jsonContacts.hasNext()) {
			JsonObject jsonContact = jsonContacts.next().asObject();
			contacts.add(new Contact(jsonContact.get("name").asString(),
									 jsonContact.get("email").asString()));
		}

		return new Proposal(NON_PERSISTED_ID,
							new ClientId(jsonObject.get("client").asObject().get("id").asInt()),
							jsonObject.get("project").asString(),
							contacts.toArray(new Contact[contacts.size()]),
							jsonObject.get("description").asString(),
							jsonObject.get("notes").asString());
	}

}
