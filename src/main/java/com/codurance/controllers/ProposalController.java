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
		String proposalDetails = jsonObject("./src/main/resources/data/proposal.json");
		ProposalPage page = new ProposalPage(proposalDetails);
		return display(page.template(), page.model());
	}
}
