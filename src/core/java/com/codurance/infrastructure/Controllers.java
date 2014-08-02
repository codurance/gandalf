package com.codurance.infrastructure;

import com.codurance.controllers.MainController;
import com.codurance.controllers.ProposalController;
import com.noodlesandwich.rekord.Rekord;
import com.noodlesandwich.rekord.Rekords;
import com.noodlesandwich.rekord.keys.Key;
import com.noodlesandwich.rekord.keys.SimpleKey;

public interface Controllers {
	Key<Controllers, MainController> mainController = SimpleKey.named("mainController");
	Key<Controllers, ProposalController> proposalController = SimpleKey.named("proposalController");

	Rekord<Controllers> rekord = Rekords.of(Controllers.class)
			.accepting(mainController, proposalController);
}
