package com.codurance;

import com.codurance.actions.RetrieveProposals;
import com.codurance.controllers.MainController;
import com.codurance.controllers.ProposalController;
import com.codurance.infrastructure.Controllers;
import com.codurance.infrastructure.Routes;
import com.codurance.infrastructure.repositories.FileSystemProposals;
import com.codurance.infrastructure.template.JadeTemplateRendered;
import com.codurance.model.proposal.Proposals;
import com.noodlesandwich.rekord.Rekord;
import main.com.codurance.controllers.TemplateRenderer;
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
		TemplateRenderer templateRenderer = new JadeTemplateRendered();
		MainController mainController = new MainController(templateRenderer);
		Proposals fileSystemProposals = new FileSystemProposals();
		RetrieveProposals retrieveProposals = new RetrieveProposals(fileSystemProposals);
		ProposalController proposalController = new ProposalController(templateRenderer, retrieveProposals);

		return Controllers.rekord
				.with(Controllers.mainController, mainController)
				.with(Controllers.proposalController, proposalController);
	}
}
