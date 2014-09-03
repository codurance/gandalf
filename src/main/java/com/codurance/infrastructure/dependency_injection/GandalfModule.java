package com.codurance.infrastructure.dependency_injection;

import com.codurance.infrastructure.Routes;
import com.codurance.infrastructure.repositories.InMemoryJsonProposals;
import com.codurance.infrastructure.template.JadeTemplateRendered;
import com.codurance.model.proposal.ProposalService;
import com.codurance.model.proposal.Proposals;
import com.codurance.model.proposal.events.ProposalSummaryList;
import com.google.inject.AbstractModule;
import main.com.codurance.controllers.TemplateRenderer;

import static com.google.inject.Scopes.SINGLETON;

public class GandalfModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(TemplateRenderer.class).to(JadeTemplateRendered.class);

		bind(Routes.class);

		bind(ProposalSummaryList.class).in(SINGLETON);
		bind(ProposalService.class).in(SINGLETON);
		bind(Proposals.class).to(InMemoryJsonProposals.class).in(SINGLETON);
	}
}
