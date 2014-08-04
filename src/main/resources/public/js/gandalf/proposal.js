//var App = angular.module('App', []);
//
//App.controller('ProposalController', function ($scope, $http) {
//
//	var proposal = this;
//
//	proposal.json = {};
//
//	$scope.proposalId = -1;
//
//	$http.get('/proposals/proposal/'+$scope.proposalId+'/json')
//			.success(function(response){
//				proposal.json = response;
//			})
//			.error(function(response){
//				alert('Error: ' + response);
//			});
//
//});