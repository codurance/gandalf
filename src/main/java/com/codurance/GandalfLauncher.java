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

		Gandalf gandalf = new Gandalf();

		return gandalf;
	}

}