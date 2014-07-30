(function(){

	var app = angular.module('app-estimations', [])

	app.controller('EstimationsController', ['$http', function($http) {
		var estimations = this;

		estimations.all = [];

		$http.get('/project-estimations/all')
			.success(function(response){
				estimations.all = response;
			})
			.error(function(response){
				alert('Error: ' + response);
			});
	}]);

})();