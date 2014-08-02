package com.codurance;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProposalController;
import com.codurance.model.proposal.ProposalId;
import com.codurance.view.MainPage;
import com.codurance.view.ProjectEstimatePage;
import com.codurance.view.ProjectEstimatesPage;
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
		initialiseProjectEstimatesRoutes();
	}

	private void initialiseProjectEstimatesRoutes() {
		get(ProjectEstimatesPage.URL, (request, response) -> {
			return proposalController.displayProjectEstimates(request, response);
		});

		get("/project-estimates/all", (request, response) -> {
			try {
				JsonArray jsonArray = JsonArray.readFrom(new FileReader("./src/main/resources/data/estimates.json"));
				return jsonArray.toString();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		});

		get(ProjectEstimatePage.URL, (request, response) -> {
			ProposalId proposalId = new ProposalId(request.params(":estimateId"));
			return proposalController.displayEstimateWithMatching(proposalId);
		});

	}

	private void initialiseMainRoutes() {
		get(MainPage.URL,(request, response) -> {
			return mainController.displayMainPage(request, response);
		});
	}
}
