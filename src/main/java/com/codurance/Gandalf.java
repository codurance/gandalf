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

	}

	private void initialiseMainRoutes() {
		get(MainPage.URL,(request, response) -> {
			return mainController.displayMainPage(request, response);
		});
	}
}
