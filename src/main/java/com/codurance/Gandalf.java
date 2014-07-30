package com.codurance;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProjectEstimationController;
import com.codurance.page_objects.MainPage;
import com.codurance.page_objects.ProjectEstimationsPage;

import static spark.Spark.*;

public class Gandalf {

	public static final String STATIC_CONTENT_FOLDER = "/public/";
	private MainController mainController;
	private ProjectEstimationController projectEstimationController;

	public Gandalf(MainController mainController, ProjectEstimationController projectEstimationController) {
		this.mainController = mainController;
		this.projectEstimationController = projectEstimationController;
	}

	public void run() {
		staticFileLocation(STATIC_CONTENT_FOLDER);
		initialiseRoutes();
	}

	private void initialiseRoutes() {
		initialiseMainRoutes();
		initialiseProjectEstimationsRoutes();
	}

	private void initialiseProjectEstimationsRoutes() {
		get(ProjectEstimationsPage.URL, (request, response) -> {
			return projectEstimationController.displayProjectEstimations(request, response);
		});

		get("/project-estimations/all", (request, response) -> {
			return "[" +
					"{" +
						"\"client\":\"Footfall123\"," +
						"\"project-name\": \"Salt\"," +
						"\"last-updated\": \"10th Jun 2014\"," +
						"\"people-involved\": [\"sandro@codurance.com\", \"mash@codurance.com\"]"  +
					"},{" +
						"\"client\":\"Mealtek\"," +
						"\"project-name\": \"Mobile platform\"," +
						"\"last-updated\": \"29th Jul 2014\"," +
						"\"people-involved\": [\"sandro@codurance.com\"]" +
					"}" +
					"]";
		});

	}

	private void initialiseMainRoutes() {
		get(MainPage.URL,(request, response) -> {
			return mainController.displayMainPage(request, response);
		});
	}
}
