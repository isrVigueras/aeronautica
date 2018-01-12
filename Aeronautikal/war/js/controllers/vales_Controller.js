app.controller("MuestraValesController", ['$scope','discrepancia_vale',function($scope,discrepancia_vale) {
 $scope.discrepancia_vale =discrepancia_vale;
  console.log($scope.discrepancia_vale);
   $scope.muestra=function(data) {
    console.log(data);
    $scope.detalle_componentes = data; 
  }
}]);