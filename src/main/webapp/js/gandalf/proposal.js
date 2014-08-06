(function(){

	var app = angular.module('app', []);

	app.controller('ProposalController', ['$http', '$scope', function ($http, $scope) {

		var proposal = this;

		proposal.json = {};

		$scope.init = function(proposalId) {
			var url = '/proposals/proposal/' + proposalId + '/json';

			$http.get(url)
					.success(function(response){
						proposal.json = response;
					})
					.error(function(response){
						alert('Error: ' + response);
					});
		};
	}]);

})();
