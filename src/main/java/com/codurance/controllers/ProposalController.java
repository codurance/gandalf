package com.codurance.controllers;

import com.codurance.model.proposal.ProposalId;
import com.codurance.view.ProposalPage;
import com.codurance.view.ProposalsPage;
import spark.Request;
import spark.Response;

public class ProposalController extends BaseController {
	public String displayProjectEstimates(Request request, Response response) {
		ProposalsPage page = new ProposalsPage();
		return display(page.template(), page.model());
	}

	public String displayEstimateWithMatching(ProposalId proposalId) {
		ProposalPage page = new ProposalPage();
		return display(page.template(), page.model());
	}
}
