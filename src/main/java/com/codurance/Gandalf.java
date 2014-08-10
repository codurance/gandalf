package com.codurance;

import com.codurance.infrastructure.GaldalfSpark;
import com.codurance.infrastructure.Routes;

public class Gandalf {

	public void start() {
		GaldalfSpark spark = new GaldalfSpark();
		spark.init();
	}

}
