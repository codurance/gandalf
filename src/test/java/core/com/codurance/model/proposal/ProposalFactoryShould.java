package core.com.codurance.model.proposal;

import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalFactory;
import org.junit.Test;

import static builders.ContactBuilder.aContact;
import static builders.ProposalBuilder.aProposal;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProposalFactoryShould {

	private static final String NEW_PROPOSAL_JSON_DATA =
			"{" +
					"\"client\":{" +
								"\"id\":1" +
							    "}," +
					"\"project\":\"EPOS Integration\"," +
					"\"contacts\":[" +
									"{\"name\":\"Bob\",\"email\":\"bob@a.com\"}," +
									"{\"name\":\"Paul\",\"email\":\"paul@a.com\"}" +
					              "]," +
					"\"description\":\"some description\"," +
					"\"notes\":\"some notes\"" +
				 "}";

	private ProposalFactory proposalFactory = new ProposalFactory();

	@Test public void
	create_a_proposal_from_json_data() {
		System.out.println(NEW_PROPOSAL_JSON_DATA);
		Proposal expectedProposal = aProposal()
										.withId(-1)
										.withClientId(1)
										.withProjectName("EPOS Integration")
										.withContacts(
												aContact().withName("Bob").withEmail("bob@a.com").build(),
												aContact().withName("Paul").withEmail("paul@a.com").build())
										.withDescription("some description")
										.withNotes("some notes")
										.build();

		Proposal proposal = proposalFactory.createProposalFrom(NEW_PROPOSAL_JSON_DATA);

		assertThat(proposal, is(expectedProposal));
	}

}
