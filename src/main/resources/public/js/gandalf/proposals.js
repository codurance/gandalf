(function(){

	var app = angular.module('app-proposals', [])

	app.controller('ProposalsController', ['$http', function($http) {
		var proposals = this;

		proposals.all = [];

		$http.get('/proposals/all')
			.success(function(response){
				proposals.all = response;
			})
			.error(function(response){
				alert('Error: ' + response);
			});
	}]);

	app.controller('ProposalController', ['$http', function ($http) {
		var proposal = this;

		proposal.json = {};

		proposal.proposalId = -1;

		$http.get('/proposals/proposal/'+this.proposalId+'/json')
				.success(function(response){
					proposal.json = response;
				})
				.error(function(response){
					alert('Error: ' + response);
				});

	}]);

})();

