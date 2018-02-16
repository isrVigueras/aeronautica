//servicio alta empresa
app.service('altaCondicionServicio', [ '$http', '$q', function($http, $q) {
  this.alta_condicion = function(user,empresa) {
    var d = $q.defer();
    $http.post("/condicion/add/"+user,empresa).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
       if(response.status==403){
      alert("No tienes Permisos");
      location.href="#/Inicio/paginaPrincipal";
      //$rootScope.authenticated = false;
                              }
    });
    return d.promise;
  }
} ]);
//servicio elmina empresa
app.service('eliminaCondicionServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_condicion = function(id,user) {
    var d = $q.defer();
    $http.post("/condicion/delete/"+id+"/"+user).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
       if(response.status==403){
      alert("No tienes Permisos");
      location.href="#/Inicio/paginaPrincipal";
      //$rootScope.authenticated = false;
                              }
    });
    return d.promise;
  }
} ]);
 //servicio actualiza empresa
app.service('ActualizaCondicionServicio', [ '$http', '$q', function($http, $q) {
  this.actualiza_condicion = function(user,objeto) {
    var d = $q.defer();
    $http.post("/condicion/update/"+user,objeto).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
       if(response.status==403){
      alert("No tienes Permisos");
      location.href="#/Inicio/paginaPrincipal";
      //$rootScope.authenticated = false;
                              }
    });
    return d.promise;
  }
} ]);
app.controller('condicionController', ['$scope', 'altaCondicionServicio','$cookies',function($scope, altaCondicionServicio,$cookies) {
   console.log($cookies.cosa);
   $scope.condicionform = {
    id: undefined,
    clave: undefined,
    descripcion:""
  }
     $scope.guarda_condicion=function() {
    //console.log(altarequisicion);
      altaCondicionServicio.alta_condicion($cookies.cosa,$scope.condicionform).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("condicion Guardada");
          location.href="#/Inventario/condicion/colsulta";
        })         
  }
}]);
app.controller('condicionMuestraController', ['$scope','eliminaCondicionServicio','condiciones_consultas','ActualizaCondicionServicio','$cookies',function($scope,eliminaCondicionServicio,condiciones_consultas,ActualizaCondicionServicio,$cookies) {
  console.log($cookies.cosa);
  $scope.condiciones_consultas = condiciones_consultas;
      console.log(condiciones_consultas);
      
       $scope.elimina_condicion=function(folio) {
    //console.log(altarequisicion);
    console.log(folio);
      eliminaCondicionServicio.elimina_condicion(folio,$cookies.cosa).then(
        function(data) {
          console.log(data);
          alert("condicion Eliminada");
          location.reload();
        })         
  }
    $scope.muestra_condicion=function(objeto) {
    console.log(objeto);
    $scope.detalle_condicion = objeto; 

    $scope.condicion_edi = {
    id: $scope.detalle_condicion.id,
    clave: $scope.detalle_condicion.clave,
    descripcion: $scope.detalle_condicion.descripcion  
  }   
  }

    $scope.Actualiza_condicion=function() {   

      ActualizaCondicionServicio.actualiza_condicion($cookies.cosa,$scope.condicion_edi).then(
        function(data) {
          console.log(data);
          alert("condicion Modificada");
          location.reload();
        })  
  }
}]);