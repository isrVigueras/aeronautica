//servicio alta requisicion
app.service('altaRequiServicio', [ '$http', '$q', function($http, $q) {
  this.alta_requisicion = function(user,altarequisicion) {
    var d = $q.defer();
    $http.post("/requisicion/add/"+user,altarequisicion).then(function(response) {
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
//servicio update requisicion
app.service('actualizaRequiServicio', [ '$http', '$q', function($http, $q) {
  this.actualizar_requisicion = function(rd,user) {
    var d = $q.defer();
    $http.post("/componente/upExistencias/"+rd+"/"+user).then(function(response) {
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
app.controller("RequisicionesController", ['$scope','requisiciones','actualizaRequiServicio','$cookies',function($scope,requisiciones,actualizaRequiServicio,$cookies) {
 console.log($cookies.cosa);
 $scope.requisicionescomponente =requisiciones;
  console.log($scope.requisicionescomponente);

   $scope.actualizar=function(rd) {
    console.log(rd);
      alert("variable comprobada: "+rd);
      actualizaRequiServicio.actualizar_requisicion(rd,$cookies.cosa).then(
        function(data) {
          console.log(data);
          location.href="#/Inventario/colsulta";
          location.reload();
        })         
  }
  $scope.muestra=function(data) {
    console.log(data);
    $scope.detalle_requisicion = data; 
  }


}]);
app.controller("Requisiciones_altaController", ['$scope','inv_consultas','foliarrastrad','altaRequiServicio','$cookies',function($scope,inv_consultas,foliarrastrad,altaRequiServicio,$cookies) {
  console.log($cookies.cosa);
  $scope.provincias=inv_consultas;

   $scope.altarequisicion = {
    folio_discrepancia:foliarrastrad,
    fechaApertura:new Date(),
    folio_componente:undefined,
    numero_piezas:undefined
  }
console.log($scope.altarequisicion);

   $scope.alta_requisicion=function() {
    console.log( $scope.altarequisicion);
      alert("variable comprobada: "+$scope.altarequisicion.folio_discrepancia+$scope.altarequisicion.folio_componente);
      altaRequiServicio.alta_requisicion($cookies.cosa,$scope.altarequisicion).then(
        function(data) {
          console.log(data);
          location.href="#/Orden/discrepancia/"+$scope.altarequisicion.folio_discrepancia;
        })         
  }
}]);