package core.com.codurance.model.proposal;

import com.codurance.model.proposal.Proposal;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import org.junit.Test;

import static builders.ContactBuilder.aContact;
import static builders.ProposalBuilder.aProposal;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProposalShould {

	@Test public void
	generate_its_json_representation() {
		JsonArray contacts = new JsonArray()
								.add(new JsonObject()
										.add("name", "Sandro")
										.add("email", "sandro@codurance.com"))
								.add(new JsonObject()
										.add("name", "John")
										.add("email", "john@somewhere.com"));
		JsonObject expectedJsonRepresentation = new JsonObject()
													.add("id", "1")
													.add("clientId", "2")
													.add("contacts", contacts)
													.add("description", "Some description")
													.add("notes", "Some notes");

		Proposal proposal = aProposal()
								.withId(1)
								.withClientId(2)
								.withContacts(
										aContact()
											.withName("Sandro")
											.withEmail("sandro@codurance.com")
											.build(),
										aContact()
											.withName("John")
											.withEmail("john@somewhere.com")
											.build())
								.withDescription("Some description")
								.withNotes("Some notes")
								.build();

		assertThat(proposal.asJson(), is(expectedJsonRepresentation));
	}

}
