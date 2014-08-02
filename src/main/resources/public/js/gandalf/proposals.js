(function(){

	var app = angular.module('app-proposals', [])

	app.controller('ProposalsController', ['$http', function($http) {
		var proposals = this;

		proposals.all = [];

		$http.get('/proposals/all')
			.success(function(response){
				proposals.all = response;
			})
			.error(function(response){
				alert('Error: ' + response);
			});
	}]);

})();