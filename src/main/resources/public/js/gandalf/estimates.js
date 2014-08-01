(function(){

	var app = angular.module('app-estimates', [])

	app.controller('EstimatesController', ['$http', function($http) {
		var estimates = this;

		estimates.all = [];

		$http.get('/project-estimates/all')
			.success(function(response){
				estimates.all = response;
			})
			.error(function(response){
				alert('Error: ' + response);
			});
	}]);

})();