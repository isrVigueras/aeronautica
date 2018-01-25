//servicio alta empresa
app.service('altaEmpleadoServicio', [ '$http', '$q', function($http, $q) {
  this.alta_empleado = function(objeto) {
    var d = $q.defer();
    $http.post("/empleado/add",objeto).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio elmina empresa
app.service('eliminaEmpleadoServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_empleado = function(id) {
    var d = $q.defer();
    $http.post("/empleado/delete/"+id).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
 //servicio actualiza empresa
app.service('ActualizaEmpleadoServicio', [ '$http', '$q', function($http, $q) {
  this.actualiza_empelado = function(objeto) {
    var d = $q.defer();
    $http.post("/empleado/update",objeto).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
app.controller('Empleados_Controller', ['$scope', 'altaEmpleadoServicio','puestos',function($scope, altaEmpleadoServicio,puestos) {
  $scope.puestos_lista = puestos;
  console.log($scope.puestos_lista);
  
   $scope.empleadoform = {
    id: undefined,
    apellidoPaterno:"",
    apellidoMaterno:"",
    nombre:"",
    idPuesto:undefined
  }
     $scope.guarda_empleado=function() {
    //console.log(altarequisicion);
    console.log($scope.empleadoform);
      altaEmpleadoServicio.alta_empleado($scope.empleadoform).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Trabajador dado de Alta");
          //location.href="#/Inventario/condicion/colsulta";
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