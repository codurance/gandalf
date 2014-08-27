package com.codurance.controllers;

import com.codurance.actions.CreateProposal;
import com.codurance.actions.RetrieveProposal;
import com.codurance.actions.RetrieveProposals;
import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalId;
import com.codurance.view.NewProposalPage;
import com.codurance.view.ProposalEstimatesPage;
import com.codurance.view.ProposalPage;
import com.codurance.view.ProposalsPage;
import com.google.inject.Inject;
import main.com.codurance.controllers.TemplateRenderer;
import spark.Request;
import spark.Response;

public class ProposalController extends BaseController {

	private static final String EMPTY = "";

	private RetrieveProposals retrieveProposals;
	private RetrieveProposal retrieveProposal;
	private CreateProposal createProposal;

	@Inject
	public ProposalController(TemplateRenderer templateRenderer,
	                          RetrieveProposals retrieveProposals,
	                          RetrieveProposal retrieveProposal,
	                          CreateProposal createProposal) {
		super(templateRenderer);
		this.retrieveProposals = retrieveProposals;
		this.retrieveProposal = retrieveProposal;
		this.createProposal = createProposal;
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
//		JsonObject json = readFrom(createProposal.create(request.body()));
//		String redirectURL = ProposalEstimatesPage.URL.replace(":proposalId", json.get("id").toString());
		Proposal proposal = createProposal.create(request.body());
		String redirectURL = ProposalEstimatesPage.URL.replace(":proposalId", proposal.id().toString());
		response.header("redirectURL", redirectURL);
		return EMPTY;
	}

	public String displayProposalEstimates(Request request, Response response) {
		return display(new ProposalEstimatesPage());
	}

	public String retriveAllProposals(Request request, Response response) {
		return retrieveProposals.all();
	}

	public String retrieveProposal(Request request, Response response) {
		ProposalId proposalId = new ProposalId(request.params(":proposalId"));
		return retrieveProposal.by(proposalId);
	}
}
