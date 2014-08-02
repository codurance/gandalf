package com.codurance;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProposalController;
import com.codurance.infrastructure.Routes;

public class GandalfLauncher {

	public static void main(String[] args) {
		gandalf().run();
	}

	public static Gandalf gandalf() {
		MainController mainController = new MainController();
		ProposalController proposalController = new ProposalController();

		Routes routes = new Routes(mainController, proposalController);

		Gandalf gandalf = new Gandalf(routes);

		return gandalf;
	}

}