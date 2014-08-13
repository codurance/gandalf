var App = angular.module('App', [])

App.controller('ProposalsController', function($scope, $http) {
	var proposalsCallback = function(response) { $scope.proposals = response; };

	$scope.loadProposals = loadJsonData($http, '/proposals/all', proposalsCallback);
});

App.controller('NewProposalController', function($scope, $http) {
	var clientsCallback = function(response){ $scope.clients = response; };

	$scope.loadFormData = loadJsonData($http, '/clients/all', clientsCallback);
});


App.controller('ProposalController', function ($http, $scope) {
	var proposalCallback = function(response){ $scope.proposal = response; };

	$scope.init = function(proposalId) {
		var url = '/proposals/proposal/' + proposalId + '/json';
		loadJsonData($http, url, proposalCallback);
	};
});

loadJsonData = function($http, url, successCallback) {
	$http.get(url)
			.success(successCallback)
			.error(displayError);
}

displayError = function(message) {
	alert('Error: ' + message);
}
