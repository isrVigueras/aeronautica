//servicio alta empresa
app.service('altaCondicionServicio', [ '$http', '$q', function($http, $q) {
  this.alta_condicion = function(empresa) {
    var d = $q.defer();
    $http.post("/condicion/add",empresa).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio elmina empresa
app.service('eliminaCondicionServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_condicion = function(id) {
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
app.service('ActualizaCondicionServicio', [ '$http', '$q', function($http, $q) {
  this.actualiza_condicion = function(objeto) {
    var d = $q.defer();
    $http.post("/empresa/update",objeto).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
app.controller('condicionController', ['$scope', 'altaCondicionServicio',function($scope, altaCondicionServicio) {
   $scope.condicionform = {
    id: undefined,
    clave: undefined,
    descripcion:""
  }
     $scope.guarda_condicion=function() {
    //console.log(altarequisicion);
      altaCondicionServicio.alta_condicion($scope.condicionform).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("condicion Guardada");
          location.href="#/Inventario/condicion/colsulta";
        })         
  }
}]);
app.controller('condicionMuestraController', ['$scope','eliminaCondicionServicio','condiciones_consultas','ActualizaCondicionServicio',function($scope,eliminaCondicionServicio,condiciones_consultas,ActualizaCondicionServicio) {
  $scope.condiciones_consultas = condiciones_consultas;
      console.log(condiciones_consultas);
      
       $scope.elimina_condicion=function(folio) {
    //console.log(altarequisicion);
    console.log(folio);
      eliminaCondicionServicio.elimina_condicion(folio).then(
        function(data) {
          console.log(data);
          alert("condicion Eliminada");
          location.reload();
        })         
  }
    $scope.muestra_condicion=function(objeto) {
    console.log(objeto);
    $scope.detalle_cliente = objeto; 

    $scope.condicion_edi = {
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

    $scope.Actualiza_condicion=function() {   

      ActualizaCondicionServicio.actualiza_condicion($scope.condicion_edi).then(
        function(data) {
          console.log(data);
          alert("condicion Modificada");
          location.reload();
        })  
  }
}]);