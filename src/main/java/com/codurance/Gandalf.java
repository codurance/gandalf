package com.codurance;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProjectEstimationController;
import com.codurance.page_objects.MainPage;
import com.codurance.page_objects.ProjectEstimationsPage;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.io.FileReader;
import java.io.IOException;

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
			try {
				JsonArray jsonArray = JsonArray.readFrom(new FileReader("./src/main/resources/data/estimations.json"));
				return jsonArray.toString();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		});

	}

	private void initialiseMainRoutes() {
		get(MainPage.URL,(request, response) -> {
			return mainController.displayMainPage(request, response);
		});
	}
}
