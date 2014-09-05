package main.unit.com.codurance.view;

import com.codurance.model.proposal.ProposalId;
import com.codurance.view.ProposalPage;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProposalPageShould {

	@Test public void
	add_the_proposal_id_value_to_its_model() {
		ProposalPage proposalPage = new ProposalPage(new ProposalId(10));

		assertThat(proposalPage.model().get("proposalId"), is(10));
	}

}
