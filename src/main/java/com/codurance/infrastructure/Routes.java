package com.codurance.infrastructure;

import com.codurance.controllers.ProposalController;
import com.codurance.view.*;
import com.noodlesandwich.rekord.Rekord;

import static com.codurance.infrastructure.JsonReader.jsonArray;
import static spark.Spark.get;
import static spark.Spark.post;

public class Routes {

	private Rekord<Controllers> controllers;

	public Routes(Rekord<Controllers> controllers) {
		this.controllers = controllers;
	}

	public void initialise() {
		initialiseMainRoutes();
		initialiseProposalsRoutes();
		initialiseClientsRoutes();
	}

	private void initialiseProposalsRoutes() {

		ProposalController proposalController = controllers.get(Controllers.proposalController);

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
				jsonArray("./src/main/webapp/data/clients.json"));
	}

	private void initialiseMainRoutes() {
		get(MainPage.URL,(request, response) ->
				controllers.get(Controllers.mainController)
					.displayMainPage(request, response));
	}



}
