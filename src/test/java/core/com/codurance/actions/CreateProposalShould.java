package core.com.codurance.actions;

import com.codurance.actions.CreateProposal;
import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalFactory;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.ProposalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateProposalShould {

	private static final String PROPOSAL_JSON_DATA = "{proposal}";
	private static final Proposal PROPOSAL = new Proposal(new ProposalId(1));

	@Mock ProposalFactory proposalFactory;
	@Mock ProposalService proposalService;

	private CreateProposal createProposal;

	@Before
	public void initialise() {
	    this.createProposal = new CreateProposal(proposalFactory, proposalService);
	}

	@Test public void
	create_a_new_proposal() {
		given(proposalFactory.createProposalFrom(PROPOSAL_JSON_DATA)).willReturn(PROPOSAL);

		createProposal.create(PROPOSAL_JSON_DATA);

		verify(proposalService).create(PROPOSAL);
	} 
	
}
