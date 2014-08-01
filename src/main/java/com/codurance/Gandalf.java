package com.codurance;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProjectEstimatesController;
import com.codurance.model.estimates.EstimateId;
import com.codurance.page_objects.MainPage;
import com.codurance.page_objects.ProjectEstimatePage;
import com.codurance.page_objects.ProjectEstimatesPage;
import com.eclipsesource.json.JsonArray;

import java.io.FileReader;
import java.io.IOException;

import static spark.Spark.*;

public class Gandalf {

	public static final String STATIC_CONTENT_FOLDER = "/public/";
	private MainController mainController;
	private ProjectEstimatesController projectEstimatesController;

	public Gandalf(MainController mainController, ProjectEstimatesController projectEstimatesController) {
		this.mainController = mainController;
		this.projectEstimatesController = projectEstimatesController;
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
			return projectEstimatesController.displayProjectEstimates(request, response);
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
			EstimateId estimateId = new EstimateId(request.params(":estimateId"));
			return projectEstimatesController.displayEstimateWithMatching(estimateId);
		});

	}

	private void initialiseMainRoutes() {
		get(MainPage.URL,(request, response) -> {
			return mainController.displayMainPage(request, response);
		});
	}
}
