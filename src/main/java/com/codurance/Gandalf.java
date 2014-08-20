package com.codurance;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProposalController;
import com.codurance.infrastructure.Controllers;
import com.codurance.infrastructure.Routes;
import com.noodlesandwich.rekord.Rekord;
import spark.servlet.SparkApplication;

/*
    There are 2 ways to run Gandalf:

    1. Executing the GandalfLauncher, which will start Jetty. Jetty will look for
    a web.xml and will find this class (Gandalf) configured as a filter. Jetty will
    run this class and that will trigger the SparkJava framework.

    2. From Maven: mvn jetty:run. This will have a similar behaviour. Maven will
    run Jetty that will look for a web.xml where this class is configured as a filter.
 */
public class Gandalf implements SparkApplication {

	public void start() {
		init();
	}

	@Override
	public void init() {
		Routes routes = new Routes(createControllers());
		routes.initialise();
	}

	private Rekord<Controllers> createControllers() {
		MainController mainController = new MainController();
		ProposalController proposalController = new ProposalController();

		return Controllers.rekord
				.with(Controllers.mainController, mainController)
				.with(Controllers.proposalController, proposalController);
	}
}
