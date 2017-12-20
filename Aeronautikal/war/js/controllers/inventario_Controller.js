//servicio alta Inventario
app.service('InventarioService', [ '$http', '$q', function($http, $q) {
  this.genera_inventario = function(inventario) {
    var d = $q.defer();
    $http.post("/componente/add",inventario).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);

app.controller("InventarioController", ['$scope','InventarioService',function($scope,InventarioService) {
  $scope.filtro = {
      d_componente: ""
    }
 $scope.inventario = {
    id:undefined,
    fechaApertura:new Date(),
    d_componente:"",
    d_descripcion:"",
    d_parte:"",
    d_cantidad:undefined,
    d_pendientes:undefined
  }

   $scope.alta_inventario=function() {
    if ($scope.form.$valid) {
      //alert("variable comprobada: "+$scope.inventario.d_componente+" y la fecha "+ $scope.inventario.fechaApertura);
      InventarioService.genera_inventario($scope.inventario).then(
        function(data) {
          console.log(data);
          //alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
            location.href="#/Inventario/colsulta";
         alert("componente dado de alta");
        })
              
    }else {
      alert("Hay datos inválidos");
    }
  }
}]);
app.controller("InventarioconsultaController", ['$scope','inv_consultas',function($scope,inv_consultas) {
 $scope.inv_consultas =inv_consultas;
  console.log($scope.inv_consultas);
   $scope.muestra=function(data) {
    console.log(data);
    $scope.detalle_componentes = data; 
  }
}]);