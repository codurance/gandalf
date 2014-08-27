package com.codurance.infrastructure;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProposalController;
import com.codurance.view.*;
import com.google.inject.Inject;

import static com.codurance.infrastructure.JsonReader.jsonArrayAsString;
import static spark.Spark.get;
import static spark.Spark.post;

public class Routes {

	private MainController mainController;
	private ProposalController proposalController;

	@Inject
	public Routes(MainController mainController,
	              ProposalController proposalController) {
		this.mainController = mainController;
		this.proposalController = proposalController;
	}

	public void initialise() {
		initialiseMainRoutes();
		initialiseProposalsRoutes();
		initialiseClientsRoutes();
	}

	private void initialiseProposalsRoutes() {

		get(NewProposalPage.URL, (request, response) ->
				proposalController.displayNewProposalPage(request, response));

		post(NewProposalPage.CREATE_PROPOSAL_URL, (request, response) ->
				proposalController.createProposal(request, response));

		get(ProposalsPage.URL, (request, response) ->
				proposalController.displayProposals(request, response));

		get(ProposalsPage.PROPOSALS_DATA_URL, (request, response) ->
				proposalController.retriveAllProposals(request, response));

		get(ProposalPage.URL, (request, response) ->
				proposalController.displayProposalForProposalId(request, response));

		get(ProposalPage.PROPOSAL_DATA_URL, (request, response) ->
				proposalController.retrieveProposal(request, response));

		get(ProposalEstimatesPage.URL, (request, response) ->
				proposalController.displayProposalEstimates(request, response));

	}

	private void initialiseClientsRoutes() {
		get("/clients/all", (request, response) ->
				jsonArrayAsString("./src/main/webapp/data/clients.json"));
	}

	private void initialiseMainRoutes() {
		get(MainPage.URL,(request, response) ->
				mainController.displayMainPage(request, response));
	}

}
