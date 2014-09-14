package core.com.codurance.model.proposal.events;

import com.codurance.model.client.Client;
import com.codurance.model.client.ClientService;
import com.codurance.model.proposal.ClientId;
import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.events.ProposalCreated;
import com.codurance.model.proposal.events.ProposalSummaryList;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static builders.CraftsmanBuilder.aCraftsman;
import static builders.ProposalBuilder.aProposal;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProposalSummaryListShould {

	private static final int PROPOSAL_ID = 1;
	private static ClientId CLIENT_ID = new ClientId(10);
	private static String CLIENT_NAME = "Client 10";
	private static final Client CLIENT = new Client(CLIENT_ID, CLIENT_NAME);
	private static String PROJECT_NAME = "Project 1";

	private static final JsonArray EMPTY_JSON_ARRAY = new JsonArray();
	private static final Proposal PROPOSAL = aProposal()
												.withId(PROPOSAL_ID)
												.withProjectName(PROJECT_NAME)
												.withClientId(CLIENT_ID.intValue())
												.lastUpdatedOn(LocalDate.of(2014, 7, 29))
												.withCraftsmenInvolved(
														aCraftsman()
																.withId(20)
																.withName("Sandro Mancuso")
																.build())
												.build();
	private static final JsonArray SINGLE_PROPOSAL_JSON =
										new JsonArray()
												.add(new JsonObject()
														.add("id", PROPOSAL_ID)
														.add("client", new JsonObject()
																.add("id", CLIENT_ID.intValue())
																.add("name", CLIENT_NAME))
														.add("projectName", PROJECT_NAME)
														.add("lastUpdatedOn", "29 Jul 2014")
														.add("craftsmenInvolved", new JsonArray()
																.add(new JsonObject()
																		.add("id", 20)
																		.add("name", "Sandro Mancuso"))));

	@Mock ClientService clientService;

	private ProposalSummaryList proposalSummaryList;

	@Before
	public void initialise() {
		this.proposalSummaryList = new ProposalSummaryList(clientService);
	}

	@Test public void
	not_have_any_proposals_when_created() {
		assertThat(proposalSummaryList.allAsJson(), is(EMPTY_JSON_ARRAY));
	}

	@Test public void
	return_a_json_array_containing_the_summary_of_the_only_proposal() {
		given(clientService.findBy(CLIENT_ID)).willReturn(CLIENT);

		proposalSummaryList.handle(new ProposalCreated(PROPOSAL));

	    assertThat(proposalSummaryList.allAsJson(), is(SINGLE_PROPOSAL_JSON));
	}

}
