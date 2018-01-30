//servicio alta empresa
app.service('altaPuestoServicio', [ '$http', '$q', function($http, $q) {
  this.alta_puesto = function(objt) {
    var d = $q.defer();
    $http.post("/puesto/add",objt).then(function(response) {
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
    $http.post("/condicion/delete/"+id).then(function(response) {
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
    $http.post("/condicion/update",objeto).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
app.controller('AdminController', ['$scope', 'altaPuestoServicio',function($scope, altaPuestoServicio) {
   $scope.puestoform = {
    id: undefined,
    Clave: undefined,
    Descripcion:""
  }
     $scope.guarda_puesto=function() {
    //console.log(altarequisicion);
    console.log($scope.puestoform);
      altaPuestoServicio.alta_puesto($scope.puestoform).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Puesto Guardado");
          location.href="#/Admin/Puestos/Consulta";
        })         
  }
}]);
app.controller('PuestoMuestraController', ['$scope','eliminaCondicionServicio','ActualizaCondicionServicio','puestos_lista',function($scope,eliminaCondicionServicio,ActualizaCondicionServicio,puestos_lista) {
  $scope.puestos_lista = puestos_lista;
      console.log(puestos_lista);
      
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
    $scope.detalle_condicion = objeto; 

    $scope.condicion_edi = {
    id: $scope.detalle_condicion.id,
    clave: $scope.detalle_condicion.clave,
    descripcion: $scope.detalle_condicion.descripcion  
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
app.controller('HorasHombreController', ['$scope', 'empleados_lista','discrepancias',function($scope, empleados_lista,discrepancias) {
    console.log(empleados_lista);
    console.log("discrepancias para signar horas");
    console.log(discrepancias);
   //$scope.puestos_lista = puestos_lista;
   $scope.puestoform = {
    id: undefined,
    Clave: undefined,
    Descripcion:""
  }
     $scope.guarda_puesto=function() {
    //console.log(altarequisicion);
    console.log($scope.puestoform);
      altaPuestoServicio.alta_puesto($scope.puestoform).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Puesto Guardado");
          location.href="#/Admin/Puestos/Consulta";
        })         
  }
}]);