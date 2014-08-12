(function(){

	var app = angular.module('app-proposals', [])

	app.controller('NewProposalController', ['$http', function($http) {
		var clients = this;

		clients.all = [];

		$http.get('/clients/all')
				.success(function(response){
					clients.all = response;
				})
				.error(function(response){
					alert('Error: ' + response);
				});
	}]);

})();

