package com.codurance.controllers;

import com.codurance.actions.AddFeature;
import com.codurance.model.proposal.ProposalId;
import com.codurance.model.feature.FeatureJson;
import com.codurance.view.ProposalFeaturesPage;
import com.eclipsesource.json.JsonObject;
import com.google.inject.Inject;
import main.com.codurance.controllers.TemplateRenderer;
import spark.Request;
import spark.Response;

import static com.eclipsesource.json.JsonObject.readFrom;
import static java.lang.Integer.valueOf;

public class ProposalFeatureController extends BaseController {
	private AddFeature addFeature;

	@Inject
	public ProposalFeatureController(TemplateRenderer templateRenderer,
	                                 AddFeature addFeature) {
		super(templateRenderer);
		this.addFeature = addFeature;
	}

	public String displayProposalFeatures(Request request, Response response) {
		String proposalId = request.cookie("proposalId");
		return display(new ProposalFeaturesPage(new ProposalId(proposalId)));
	}

	public String addFeature(Request request, Response response) {
		JsonObject data = readFrom(request.body());
		ProposalId proposalId = new ProposalId(valueOf(data.get("proposalId").asInt()));
		FeatureJson featureJson = new FeatureJson(data.get("feature").asObject());
		addFeature.add(proposalId, featureJson);
		return display(new ProposalFeaturesPage(proposalId));
	}
}
