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
//servicio elimina Inventario
app.service('EliminaInventarioService', [ '$http', '$q', function($http, $q) {
  this.elimina_inventario = function(id) {
    var d = $q.defer();
    $http.post("/componente/delete/"+id).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);


app.controller("InventarioController", ['$scope','InventarioService','categoria','unidad','condicion',function($scope,InventarioService,categoria,unidad,condicion) {
$scope.categoria =categoria;
$scope.unidad =unidad;
$scope.condicion =condicion;

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
    d_pendientes:undefined,
    idCategoria:undefined,
    idCondicion:undefined,
    idUnidad:undefined,
    d_modelo:"",
    d_marca:"",
    d_observaciones:"",
    maximo:undefined,
    minimo:undefined,
    anaquel:"",
    repisa:"",
    imagen:"",
    certificado8130:Boolean

  }

   $scope.alta_inventario=function() {
    console.log($scope.inventario);
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
app.controller("InventarioconsultaController", ['$scope','inv_consultas','EliminaInventarioService',function($scope,inv_consultas,EliminaInventarioService) {
 $scope.inv_consultas =inv_consultas;
  console.log($scope.inv_consultas);
   $scope.muestra=function(data) {
    console.log(data);
    $scope.detalle_componentes = data; 
  }
}]);