package core.com.codurance.model.proposal.events;

import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.events.ProposalCreated;
import com.codurance.model.proposal.events.ProposalSummaryList;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static builders.ContactBuilder.aContact;
import static builders.ProposalBuilder.aProposal;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProposalSummaryListShould {

	private static int CLIENT_ID = 10;
	private static String CLIENT_NAME = "Client 10";
	private static String PROJECT_NAME = "Project 1";

	private static final String EMPTY_JSON_ARRAY = "[]";
	private static final Proposal PROPOSAL = aProposal()
												.withId(1)
												.withProjectName(PROJECT_NAME)
												.withClientId(CLIENT_ID)
												.lastUpdatedOn(LocalDate.of(2014, 7, 29))
												.withContacts(
														aContact()
																.withEmail("sandro@codurance.com")
																.build())
												.build();
	private static final String SINGLE_PROPOSAL_JSON = "[{" +
															"\"id\":1," +
															"\"client\":{" +
																	"\"id\":" + CLIENT_ID +"," +
																	"\"name\":\"" + CLIENT_NAME + "\"" +
															"}," +
															"\"projectName\":\"" + PROJECT_NAME + "\"," +
															"\"lastUpdated\":\"29 Jul 2014\"," +
															"\"peopleInvolved\":[\"sandro@codurance.com\"]" +
														"}]";

	private ProposalSummaryList proposalSummaryList;

	@Before
	public void initialise() {
		this.proposalSummaryList = new ProposalSummaryList();
	}

	@Test public void
	not_have_any_proposals_when_created() {
		assertThat(proposalSummaryList.allAsJson(), is(EMPTY_JSON_ARRAY));
	}

	@Test public void
	return_a_json_array_containing_the_summary_of_the_only_proposal() {
		proposalSummaryList.handle(new ProposalCreated(PROPOSAL));

	    assertThat(proposalSummaryList.allAsJson(), is(SINGLE_PROPOSAL_JSON));
	}

}
