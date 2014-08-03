package com.codurance.infrastructure;

import com.codurance.model.proposal.ProposalId;
import com.codurance.view.MainPage;
import com.codurance.view.ProposalPage;
import com.codurance.view.ProposalsPage;
import com.noodlesandwich.rekord.Rekord;

import static com.codurance.infrastructure.JsonReader.jsonArray;
import static com.codurance.infrastructure.JsonReader.jsonObject;
import static spark.Spark.get;

public class Routes {

	private Rekord<Controllers> controllers;

	public Routes(Rekord<Controllers> controllers) {
		this.controllers = controllers;
	}

	public void initialise() {
		initialiseMainRoutes();
		initialiseProposalsRoutes();
	}

	private void initialiseProposalsRoutes() {
		get(ProposalsPage.URL, (request, response) -> {
			return controllers.get(Controllers.proposalController)
					.displayProposals(request, response);
		});

		get(ProposalsPage.PROPOSALS_DATA_URL, (request, response) -> {
			return jsonArray("./src/main/resources/data/proposals.json");
		});

		get(ProposalPage.URL, (request, response) -> {
			ProposalId proposalId = new ProposalId(request.params(":proposalId"));
			return controllers.get(Controllers.proposalController)
						.displayProposalMatching(proposalId);
		});

		get(ProposalPage.PROPOSAL_DATA_URL, (request, response) -> {
			return jsonObject("./src/main/resources/data/proposal.json");
		});

	}

	private void initialiseMainRoutes() {
		get(MainPage.URL,(request, response) -> {
			return controllers.get(Controllers.mainController)
					.displayMainPage(request, response);
		});
	}



}
