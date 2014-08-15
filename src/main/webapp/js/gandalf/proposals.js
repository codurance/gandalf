(function() {

	var App = angular.module('ProposalApp', [])

	App.controller('ProposalsController', function($scope, $http) {
		var proposalsCallback = function(response) { $scope.proposals = response; };

		$scope.loadProposals = loadJsonData($http, '/proposals/all', proposalsCallback);
	});

	App.controller('NewProposalController', ['$http', '$scope', '$window',
		function($http, $scope, $window) {
			var clientsCallback = function(response){ $scope.clients = response; };

			$scope.loadFormData = loadJsonData($http, '/clients/all', clientsCallback);

			$scope.master = {};

			$scope.contacts = [{
									"name":"",
									"email":""
								},{
									"name":"",
									"email":""
								}];

			$scope.create = function(proposal) {
				$scope.master = angular.copy(proposal);
				$http.post('/proposals/proposal/create', $scope.master)
						.success(function(data, status, headers, config) {
							$window.location.href = headers('redirectURL');
						});
			};

			$scope.reset = function() {
				$scope.proposal = angular.copy($scope.master);
			}

			$scope.isUnchanged = function(proposal) {
				return angular.equals(proposal, $scope.master);
			};

			$scope.reset();
		}]);


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
