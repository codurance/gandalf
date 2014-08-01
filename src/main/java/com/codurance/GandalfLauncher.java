package com.codurance;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProjectEstimatesController;

public class GandalfLauncher {

	public static void main(String[] args) {
		gandalf().run();
	}

	public static Gandalf gandalf() {
		MainController mainController = new MainController();
		ProjectEstimatesController projectEstimatesController = new ProjectEstimatesController();

		Gandalf gandalf = new Gandalf(mainController, projectEstimatesController);

		return gandalf;
	}

}