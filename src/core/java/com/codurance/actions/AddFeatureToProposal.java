package com.codurance.actions;

import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.ProposalService;
import com.codurance.model.proposal.feature.FeatureJson;
import com.google.inject.Inject;

public class AddFeatureToProposal {

	private ProposalService proposalService;

	@Inject
	public AddFeatureToProposal(ProposalService proposalService) {
		this.proposalService = proposalService;
	}

	public void addFeature(ProposalId proposalId, FeatureJson featureJson) {
		proposalService.addFeatureToProposal(proposalId, featureJson);
	}
}
