package com.codurance.infrastructure;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProposalController;
import com.noodlesandwich.rekord.Rekord;
import spark.servlet.SparkApplication;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class GaldalfSpark implements SparkApplication {

	@Override
	public void init() {
//		try {

//			File webappFolder = new File(System.getProperty("user.dir") + "/src/main/webapp");
//			externalStaticFileLocation(webappFolder.getCanonicalPath());
//			File webinfFolder = new File(System.getProperty("user.dir") + "/src/main/webapp/WEB-INF");
//			System.out.println(webappFolder.getCanonicalPath());
//			addToClassPath(webappFolder);
//			addToClassPath(webinfFolder);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}

		MainController mainController = new MainController();
		ProposalController proposalController = new ProposalController();

		Rekord<Controllers> controllers = Controllers.rekord
				.with(Controllers.mainController, mainController)
				.with(Controllers.proposalController, proposalController);

//		setPort(8080);
		Routes routes = new Routes(controllers);

		routes.initialise();
	}

	private static void addToClassPath(File file) throws Exception {
		Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
		method.setAccessible(true);
		method.invoke(ClassLoader.getSystemClassLoader(), new Object[]{file.toURI().toURL()});
	}
}
