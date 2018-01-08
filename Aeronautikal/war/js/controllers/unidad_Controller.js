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
    $http.post("/empresa/delete/"+id).then(function(response) {
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
    $http.post("/empresa/update",objeto).then(function(response) {
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
    $scope.detalle_cliente = objeto; 

    $scope.unidad_edi = {
    idEmpresa:$scope.detalle_cliente.idEmpresa,
    nombreEmpresa:$scope.detalle_cliente.nombreEmpresa,
    rfc:$scope.detalle_cliente.rfc,
    razonSocial:$scope.detalle_cliente.razonSocial,
    pais:$scope.detalle_cliente.pais,
    nombreContacto:$scope.detalle_cliente.nombreContacto,
    telefono:$scope.detalle_cliente.telefono,
    email:$scope.detalle_cliente.email
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