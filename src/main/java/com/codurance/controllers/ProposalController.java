package com.codurance.controllers;

import com.codurance.model.proposal.ProposalId;
import com.codurance.view.ProposalEstimatesPage;
import com.codurance.view.NewProposalPage;
import com.codurance.view.ProposalPage;
import com.codurance.view.ProposalsPage;
import main.com.codurance.controllers.TemplateRenderer;
import spark.Request;
import spark.Response;

public class ProposalController extends BaseController {

	public ProposalController(TemplateRenderer templateRenderer) {
		super(templateRenderer);
	}

	public String displayProposals(Request request, Response response) {
		return display(new ProposalsPage());
	}

	public String displayProposalForProposalId(Request request, Response reponse) {
		ProposalId proposalId = new ProposalId(request.params(":proposalId"));
		return display(new ProposalPage(proposalId));
	}

	public String displayNewProposalPage(Request request, Response response) {
		return display(new NewProposalPage());
	}

	public String createProposal(Request request, Response response) {
		String newProposalJson = request.body();
		String redirectURL = ProposalEstimatesPage.URL.replace(":proposalId", "10");
		response.header("redirectURL", redirectURL);
		return "";
	}

	public String displayProposalEstimates(Request request, Response response) {
		ProposalId proposalId = new ProposalId(request.params(":proposalId"));
		return display(new ProposalEstimatesPage());
	}
}
