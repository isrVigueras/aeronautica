//servicio alta empresa
app.service('altaUnidadServicio', [ '$http', '$q', function($http, $q) {
  this.alta_unidad = function(empresa) {
    var d = $q.defer();
    $http.post("/unidad/add",empresa).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio elmina empresa
app.service('eliminaUnidadServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_unidad = function(id) {
    var d = $q.defer();
    $http.post("/unidad/delete/"+id).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
 //servicio actualiza empresa
app.service('ActualizaUnidadServicio', [ '$http', '$q', function($http, $q) {
  this.actualiza_unidad = function(objeto) {
    var d = $q.defer();
    $http.post("/unidad/update",objeto).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
app.controller('unidadController', ['$scope', 'altaUnidadServicio',function($scope, altaUnidadServicio) {
   $scope.unidadform = {
    id: undefined,
    clave: undefined,
    sigla:"",
    descripcion:""
  }
     $scope.guarda_unidad=function() {
    //console.log(altarequisicion);
      altaUnidadServicio.alta_unidad($scope.unidadform).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Unidad Guardada");
          location.href="#/Inventario/unidad/colsulta";
        })         
  }
}]);
app.controller('unidadMuestraController', ['$scope','eliminaUnidadServicio','unidades_consultas','ActualizaUnidadServicio',function($scope,eliminaUnidadServicio,unidades_consultas,ActualizaUnidadServicio) {
  $scope.unidades_consultas = unidades_consultas;
      console.log(unidades_consultas);
      
       $scope.elimina_unidad=function(folio) {
    //console.log(altarequisicion);
    console.log(folio);
      eliminaUnidadServicio.elimina_unidad(folio).then(
        function(data) {
          console.log(data);
          alert("unidad Eliminada");
          location.reload();
        })         
  }
    $scope.muestra_unidad=function(objeto) {
    console.log(objeto);
    $scope.detalle_unidad = objeto; 

    $scope.unidad_edi = {
    id:  $scope.detalle_unidad.id,
    clave: $scope.detalle_unidad.clave,
    sigla: $scope.detalle_unidad.sigla,
    descripcion: $scope.detalle_unidad.descripcion
  }   
  }

    $scope.Actualiza_unidad=function() {   

      ActualizaUnidadServicio.actualiza_unidad($scope.unidad_edi).then(
        function(data) {
          console.log(data);
          alert("unidad Modificada");
          location.reload();
        })  
  }
}]);