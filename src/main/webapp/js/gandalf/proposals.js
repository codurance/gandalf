(function() {

	var App = angular.module('ProposalApp', [])

	App.controller('ProposalsController', function($scope, $http) {
		var proposalsCallback = function(response) { $scope.proposals = response; };

		$scope.loadProposals = loadJsonData($http, '/proposals/all', proposalsCallback);
	});

	App.controller('NewProposalController', function($scope, $http) {
		var clientsCallback = function(response){ $scope.clients = response; };

		$scope.loadFormData = loadJsonData($http, '/clients/all', clientsCallback);

		$scope.master = {};

		$scope.update = function(proposal) {
			$scope.master = angular.copy(proposal);
		};

		$scope.reset = function() {
			$scope.proposal = angular.copy($scope.master);
		}

		$scope.isUnchanged = function(proposal) {
			return angular.equals(proposal, $scope.master);
		};

		$scope.reset();
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

})();
