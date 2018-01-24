app.controller("NotificacionesController", ['$scope','aalertas',function($scope,aalertas) {
  $scope.aalertas =aalertas;
	console.log($scope.aalertas);

}]);