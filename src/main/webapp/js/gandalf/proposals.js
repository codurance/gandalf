var App = angular.module('App', [])

App.controller('ProposalsController', function($scope, $http) {

	$scope.proposals = [];

	$scope.loadProposals = function() {
		$http.get('/proposals/all')
				.success(function(response){
					$scope.proposals = response;
				})
				.error(function(response){
					alert('Error: ' + response);
				});
	}
});

App.controller('NewProposalController', function($scope, $http) {

	$scope.clients = [];

	$scope.loadFormData = function() {
		$http.get('/clients/all')
				.success(function(response){
					$scope.clients = response;
				})
				.error(function(response){
					alert('Error: ' + response);
				});
	};
});


App.controller('ProposalController', function ($http, $scope) {

	$scope.proposal = {};

	$scope.init = function(proposalId) {
		var url = '/proposals/proposal/' + proposalId + '/json';
		$http.get(url)
				.success(function(response){
					$scope.proposal = response;
				})
				.error(function(response){
					alert('Error: ' + response);
				});
	};
});