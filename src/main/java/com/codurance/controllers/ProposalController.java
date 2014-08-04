package com.codurance.controllers;

import com.codurance.model.proposal.ProposalId;
import com.codurance.view.ProposalPage;
import com.codurance.view.ProposalsPage;
import spark.Request;
import spark.Response;

import static com.codurance.infrastructure.JsonReader.jsonObject;

public class ProposalController extends BaseController {
	public String displayProposals(Request request, Response response) {
		ProposalsPage page = new ProposalsPage();
		return display(page.template(), page.model());
	}

	public String displayProposalMatching(ProposalId proposalId) {
		ProposalPage page = new ProposalPage(proposalId);
		return display(page.template(), page.model());
	}
}
