//servicio alta requisicion
app.service('altaRequiServicio', [ '$http', '$q', function($http, $q) {
  this.alta_requisicion = function(altarequisicion) {
    var d = $q.defer();
    $http.post("/requisicion/add",altarequisicion).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio update requisicion
app.service('actualizaRequiServicio', [ '$http', '$q', function($http, $q) {
  this.actualizar_requisicion = function(rd) {
    var d = $q.defer();
    $http.post("/componente/upExistencias/"+rd).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
app.controller("RequisicionesController", ['$scope','requisiciones','actualizaRequiServicio',function($scope,requisiciones,actualizaRequiServicio) {
 $scope.requisicionescomponente =requisiciones;
  console.log($scope.requisicionescomponente);

   $scope.actualizar=function(rd) {
    console.log(rd);
      alert("variable comprobada: "+rd);
      actualizaRequiServicio.actualizar_requisicion(rd).then(
        function(data) {
          console.log(data);
          location.href="#/Inventario/colsulta";
        })         
  }

}]);
app.controller("Requisiciones_altaController", ['$scope','inv_consultas','foliarrastrad','altaRequiServicio',function($scope,inv_consultas,foliarrastrad,altaRequiServicio) {
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
      altaRequiServicio.alta_requisicion($scope.altarequisicion).then(
        function(data) {
          console.log(data);
          location.href="#/Orden/discrepancia/"+$scope.altarequisicion.folio_discrepancia;
        })         
  }
}]);