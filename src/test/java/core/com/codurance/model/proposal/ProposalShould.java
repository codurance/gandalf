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

	private static final JsonArray contacts = new JsonArray()
													.add(new JsonObject()
															.add("name", "Sandro")
															.add("email", "sandro@codurance.com"))
													.add(new JsonObject()
															.add("name", "John")
															.add("email", "john@somewhere.com"));
	private static final JsonObject PROPOSAL_JSON = new JsonObject()
															.add("id", "1")
															.add("clientId", "2")
															.add("projectName", "Some project")
															.add("contacts", contacts)
															.add("description", "Some description")
															.add("notes", "Some notes");

	private static final Proposal PROPOSAL = aProposal()
												.withId(1)
												.withClientId("2")
												.withProjectName("Some project")
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

	@Test public void
	generate_its_own_json_representation() {
		assertThat(PROPOSAL.asJson(), is(PROPOSAL_JSON));
	}
	
	@Test public void
	create_a_new_instance_from_json() {
	    assertThat(Proposal.fromJson(PROPOSAL_JSON), is(PROPOSAL));
	} 

}
