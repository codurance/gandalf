(function(){

	var app = angular.module('app', []);

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
