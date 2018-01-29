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
  this.actualiza_empleado = function(objeto) {
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
          location.href="#/Admin/Empleados/Consulta";
        })         
  }
}]);
app.controller('EmpleadoMuestraController', ['$scope','eliminaEmpleadoServicio','ActualizaEmpleadoServicio','empleados_lista','puestos',function($scope,eliminaEmpleadoServicio,ActualizaEmpleadoServicio,empleados_lista,puestos) {
  $scope.empleados_lista = empleados_lista;
  $scope.puestos_lista = puestos;
      console.log(empleados_lista);
      
       $scope.elimina_empleado=function(folio) {
    //console.log(altarequisicion);
    console.log(folio);
      eliminaEmpleadoServicio.elimina_empleado(folio).then(
        function(data) {
          console.log(data);
          alert("Trabajador Eliminado");
          location.reload();
        })         
  }
    $scope.muestra_empleado=function(objeto) {
    console.log(objeto);
    $scope.detalle_empleado = objeto; 

    $scope.empleado_edi = {
    id: $scope.detalle_empleado.id,
    apellidoPaterno: $scope.detalle_empleado.apellidoPaterno,
    apellidoMaterno: $scope.detalle_empleado.apellidoMaterno,
    idPuesto: $scope.detalle_empleado.idPuesto,
    nombre: $scope.detalle_empleado.nombre
  }   
  }

    $scope.Actualiza_Empleado=function() {   
      console.log($scope.condicion_edi);
      ActualizaEmpleadoServicio.actualiza_empleado($scope.condicion_edi).then(
        function(data) {
          console.log(data);
          alert("Datos de Trabajador Modificados");
          location.reload();
        })  
  }
}]);