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
		ProposalsPage page = new ProposalsPage();
		return display(page.template(), page.model());
	}

	public String displayProposalForProposalId(Request request, Response reponse) {
		ProposalId proposalId = new ProposalId(request.params(":proposalId"));
		ProposalPage page = new ProposalPage(proposalId);
		return display(page.template(), page.model());
	}

	public String displayNewProposalPage(Request request, Response response) {
		NewProposalPage page = new NewProposalPage();
		return display(page.template(), page.model());
	}

	public String createProposal(Request request, Response response) {
		String newProposalJson = request.body();
		System.out.println(newProposalJson);
		String redirectURL = ProposalEstimatesPage.URL.replace(":proposalId", "10");
		response.header("redirectURL", redirectURL);
		return "";
	}

	public String displayProposalEstimates(Request request, Response response) {
		ProposalId proposalId = new ProposalId(request.params(":proposalId"));
		ProposalEstimatesPage page = new ProposalEstimatesPage();
		return display(page.template(), page.model());
	}
}
