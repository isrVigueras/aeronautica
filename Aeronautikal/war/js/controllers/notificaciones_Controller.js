app.controller("NotificacionesController", ['$scope','aalertas','lista_derequisiciones',function($scope,aalertas,lista_derequisiciones) {
  $scope.aalertas =aalertas;
	$scope.lista_derequisiciones =lista_derequisiciones;  
	console.log($scope.aalertas);
	console.log($scope.lista_derequisiciones);

}]);