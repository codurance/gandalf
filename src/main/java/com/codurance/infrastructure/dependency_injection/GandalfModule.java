package com.codurance.infrastructure.dependency_injection;

import com.codurance.infrastructure.Routes;
import com.codurance.infrastructure.repositories.FileSystemProposals;
import com.codurance.infrastructure.template.JadeTemplateRendered;
import com.codurance.model.proposal.ProposalService;
import com.codurance.model.proposal.Proposals;
import com.google.inject.AbstractModule;
import main.com.codurance.controllers.TemplateRenderer;

public class GandalfModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(TemplateRenderer.class).to(JadeTemplateRendered.class);

		bind(Routes.class);

		bind(ProposalService.class).asEagerSingleton();
		bind(Proposals.class).to(FileSystemProposals.class);
	}
}
