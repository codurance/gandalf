package com.codurance;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProposalController;
import com.codurance.infrastructure.Controllers;
import com.codurance.infrastructure.Routes;
import com.noodlesandwich.rekord.Rekord;

public class GandalfLauncher {

	public static void main(String[] args) {
		gandalf().run();
	}

	public static Gandalf gandalf() {
		MainController mainController = new MainController();
		ProposalController proposalController = new ProposalController();

		Rekord<Controllers> controllers = Controllers.rekord
											.with(Controllers.mainController, mainController)
											.with(Controllers.proposalController, proposalController);

		Routes routes = new Routes(controllers);

		Gandalf gandalf = new Gandalf(routes);

		return gandalf;
	}

}