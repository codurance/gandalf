package com.codurance.controllers;

import com.codurance.page_objects.ProjectEstimationsPage;
import spark.Request;
import spark.Response;

public class ProjectEstimationController extends BaseController {
	public String displayProjectEstimations(Request request, Response response) {
		ProjectEstimationsPage page = new ProjectEstimationsPage();
		return display(page.template(), page.model());
	}
}
