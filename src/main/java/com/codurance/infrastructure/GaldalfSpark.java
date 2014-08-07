package com.codurance.infrastructure;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProposalController;
import com.noodlesandwich.rekord.Rekord;
import spark.servlet.SparkApplication;

import static spark.SparkBase.staticFileLocation;

public class GaldalfSpark implements SparkApplication {

	private static final String STATIC_CONTENT_FOLDER = "/public/";


	@Override
	public void init() {
		MainController mainController = new MainController();
		ProposalController proposalController = new ProposalController();

		Rekord<Controllers> controllers = Controllers.rekord
				.with(Controllers.mainController, mainController)
				.with(Controllers.proposalController, proposalController);

		Routes routes = new Routes(controllers);

		routes.initialise();

//		staticFileLocation(STATIC_CONTENT_FOLDER);
	}
}
