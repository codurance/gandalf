package com.codurance;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProposalController;
import com.codurance.model.proposal.ProposalId;
import com.codurance.view.MainPage;
import com.codurance.view.ProposalPage;
import com.codurance.view.ProposalsPage;
import com.eclipsesource.json.JsonArray;

import java.io.FileReader;
import java.io.IOException;

import static spark.Spark.*;

public class Gandalf {

	public static final String STATIC_CONTENT_FOLDER = "/public/";
	private MainController mainController;
	private ProposalController proposalController;

	public Gandalf(MainController mainController, ProposalController proposalController) {
		this.mainController = mainController;
		this.proposalController = proposalController;
	}

	public void run() {
		staticFileLocation(STATIC_CONTENT_FOLDER);
		initialiseRoutes();
	}

	private void initialiseRoutes() {
		initialiseMainRoutes();
		initialiseProposalsRoutes();
	}

	private void initialiseProposalsRoutes() {
		get(ProposalsPage.URL, (request, response) -> {
			return proposalController.displayProposals(request, response);
		});

		get("/proposals/all", (request, response) -> {
			try {
				JsonArray jsonArray = JsonArray.readFrom(new FileReader("./src/main/resources/data/proposals.json"));
				return jsonArray.toString();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		});

		get(ProposalPage.URL, (request, response) -> {
			ProposalId proposalId = new ProposalId(request.params(":proposalId"));
			return proposalController.displayProposalMatching(proposalId);
		});

	}

	private void initialiseMainRoutes() {
		get(MainPage.URL,(request, response) -> {
			return mainController.displayMainPage(request, response);
		});
	}
}
