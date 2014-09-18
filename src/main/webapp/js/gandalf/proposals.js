(function() {

	var App = angular.module('ProposalApp', [])

	App.controller('ProposalsController', function($scope, $http) {
		var proposalsCallback = function(response) { $scope.proposals = response; };

		$scope.loadProposals = loadJsonData($http, '/proposals/all', proposalsCallback);
	});

	App.controller('NewProposalController', ['$http', '$scope', '$window',
		function($http, $scope, $window) {
			var clientsCallback = function(response){ $scope.clients = response; };
			var craftsmanCallback = function(response) { $scope.craftsmen = response; };

			$scope.loadFormData = function() {
				loadJsonData($http, '/clients/all', clientsCallback);
				loadJsonData($http, '/craftsmen/all', craftsmanCallback);
			}

			$scope.selectedCraftsmen = [];

			$scope.master = {};

			$scope.newProposal = {
									"client": {"id":""},
									"project":"pro name",
									"description":"",
									"notes":"",
									"contacts":[
										{
											"name":"",
											"email":""
										},{
											"name":"John Lennon",
											"email":"john.lennon@coolcards.com"
										}
									]
								};

			$scope.create = function(proposal) {
				$scope.master = angular.copy(proposal);
				$scope.master.craftsmenInvolved = angular.copy($scope.selectedCraftsmen);
				$http.post('/proposals/proposal/create', $scope.master)
						.success(function(data, status, headers, config) {
							$window.location.href = headers('redirectURL');
						});
			};

			$scope.reset = function() {
				$scope.newProposal = angular.copy($scope.master);
			}

			$scope.isUnchanged = function(proposal) {
				return angular.equals(proposal, $scope.master);
			};

			$scope.reset();
		}]);


	App.controller('ProposalController', function ($http, $scope) {
		$scope.init = function(proposalId) {
			loadProposal($http, $scope, proposalId);
		};
	});

	App.controller('ProposalEstimateController', function($http, $scope) {
		$scope.init = function(proposalId) {
			loadProposal($http, $scope, proposalId);
		}

		$scope.newFeature = {
			"description":"",
			"assumptions":"",
			"pessimistic":"1",
			"optimistic":"1",
			"realistic":"1",
			"phase":"1"
		};

		$scope.create = function(proposalId, newFeature) {
			var data = {};
			data.proposalId = proposalId;
			data.feature = newFeature;
			$http.post('/proposals/proposal/feature/add', data)
					.success(function(data, status, headers, config) {
						alert("Feature added.");
//						$window.location.href = headers('redirectURL');
					});
		};

	});

	loadProposal = function($http, $scope, proposalId) {
		var url = '/proposals/proposal/' + proposalId + '/json';
		loadJsonData($http, url, function(response) {
			return $scope.proposal = response;
		})
	}

	loadJsonData = function($http, url, successCallback) {
		$http.get(url)
				.success(successCallback)
				.error(displayError);
	}

	displayError = function(message) {
		alert('Error: ' + message);
	}

})();
