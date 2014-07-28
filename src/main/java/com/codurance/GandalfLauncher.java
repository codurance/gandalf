package com.codurance;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProjectEstimationController;

public class GandalfLauncher {

	public static void main(String[] args) {
		gandalf().run();
	}

	private static Gandalf gandalf() {
		MainController mainController = new MainController();
		ProjectEstimationController projectEstimationController = new ProjectEstimationController();

		Gandalf gandalf = new Gandalf(mainController, projectEstimationController);

		return gandalf;
	}

}