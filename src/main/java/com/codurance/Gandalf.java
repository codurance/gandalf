package com.codurance;

import com.codurance.infrastructure.Routes;
import com.codurance.infrastructure.dependency_injection.GandalfModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
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
		Injector injector = Guice.createInjector(new GandalfModule());
		Routes routes = injector.getInstance(Routes.class);
		routes.initialise();
	}

}
