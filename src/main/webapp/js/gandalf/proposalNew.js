var App = angular.module('App', [])

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
