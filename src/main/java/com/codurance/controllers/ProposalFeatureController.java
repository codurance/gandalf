package com.codurance.controllers;

import com.codurance.actions.AddFeatureToProposal;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.proposal.feature.FeatureJson;
import com.codurance.view.ProposalEstimatesPage;
import com.eclipsesource.json.JsonObject;
import com.google.inject.Inject;
import main.com.codurance.controllers.TemplateRenderer;
import spark.Request;
import spark.Response;

import static com.eclipsesource.json.JsonObject.readFrom;
import static java.lang.Integer.valueOf;

public class ProposalFeatureController extends BaseController {
	private AddFeatureToProposal addFeatureToProposal;

	@Inject
	public ProposalFeatureController(TemplateRenderer templateRenderer,
	                                 AddFeatureToProposal addFeatureToProposal) {
		super(templateRenderer);
		this.addFeatureToProposal = addFeatureToProposal;
	}

	public String displayProposalEstimates(Request request, Response response) {
		String proposalId = request.cookie("proposalId");
		return display(new ProposalEstimatesPage(new ProposalId(proposalId)));
	}

	public String addFeature(Request request, Response response) {
		JsonObject data = readFrom(request.body());
		ProposalId proposalId = new ProposalId(valueOf(data.get("proposalId").asInt()));
		FeatureJson featureJson = new FeatureJson(data.get("feature").asObject());
		addFeatureToProposal.addFeature(proposalId, featureJson);
		return display(new ProposalEstimatesPage(proposalId));
	}
}
