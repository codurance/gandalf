package com.codurance;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProposalController;

public class GandalfLauncher {

	public static void main(String[] args) {
		gandalf().run();
	}

	public static Gandalf gandalf() {
		MainController mainController = new MainController();
		ProposalController proposalController = new ProposalController();

		Gandalf gandalf = new Gandalf(mainController, proposalController);

		return gandalf;
	}

}