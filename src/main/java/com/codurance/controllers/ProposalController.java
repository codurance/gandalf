package com.codurance.controllers;

import com.codurance.actions.CreateProposal;
import com.codurance.actions.RetrieveProposal;
import com.codurance.actions.RetrieveProposals;
import com.codurance.model.proposal.Proposal;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.ProposalJson;
import com.codurance.view.NewProposalPage;
import com.codurance.view.ProposalEstimatesPage;
import com.codurance.view.ProposalPage;
import com.codurance.view.ProposalsPage;
import com.google.inject.Inject;
import main.com.codurance.controllers.TemplateRenderer;
import spark.Request;
import spark.Response;

import static com.eclipsesource.json.JsonObject.readFrom;
import static java.lang.Integer.valueOf;

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
		ProposalId proposalId = new ProposalId(valueOf(request.params(":proposalId")));
		return display(new ProposalPage(proposalId));
	}

	public String displayNewProposalPage(Request request, Response response) {
		return display(new NewProposalPage());
	}

	public String createProposal(Request request, Response response) {
		ProposalJson proposalJson = new ProposalJson(readFrom(request.body()));
		Proposal proposal = createProposal.create(proposalJson);
		String redirectURL = ProposalEstimatesPage.URL.replace(":proposalId", proposal.id().asString());
		response.header("redirectURL", redirectURL);
		response.cookie("proposalId", proposal.id().asString());
		return EMPTY;
	}

	public String displayProposalEstimates(Request request, Response response) {
		String proposalId = request.cookies().get("proposalId");
		return display(new ProposalEstimatesPage(new ProposalId(proposalId)));
	}

	public String retriveAllProposals(Request request, Response response) {
		return retrieveProposals.all();
	}

	public String retrieveProposal(Request request, Response response) {
		ProposalId proposalId = new ProposalId(valueOf(request.params(":proposalId")));
		return retrieveProposal.by(proposalId);
	}
}
