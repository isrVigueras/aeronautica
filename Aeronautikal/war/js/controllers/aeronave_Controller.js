//servicio alta Aeronave
app.service('altaAeronaveServicio', [ '$http', '$q', function($http, $q) {
  this.alta_aeronave = function(aeronave) {
    var d = $q.defer();
    $http.post("/aeronave/add",aeronave).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio elmina Aeronave
app.service('eliminaAeronaveServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_aeronave = function(folio) {
    var d = $q.defer();
    $http.post("/aeronave/delete/"+folio).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio actualiza Aeronave
app.service('ActualizaAeronaveServicio', [ '$http', '$q', function($http, $q) {
  this.actualiza_aeronave = function(objeto) {
    var d = $q.defer();
    $http.post("/aeronave/update",objeto).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
app.controller('aeronaveController', ['$scope','altaAeronaveServicio',function($scope,altaAeronaveServicio) {
   $scope.aeronaveform = {
    numeroAeronave: undefined,
    matricula:"",
    modelo:"",
    numeroSerie:"",
    nacionalidad: "",
    tiempovuelo:undefined,
     aterrizaje:""
    
  }
     $scope.guarda_aero=function() {
    //console.log(altarequisicion);
    console.log($scope.aeronaveform);
      altaAeronaveServicio.alta_aeronave($scope.aeronaveform).then(
        function(data) {
          console.log(data);
          alert("Aeronave Guardada");
          location.href="#/Aeronaves/consulta";
        })         
  }
}]);
app.controller('aeronaveMuestraController', ['$scope','eliminaAeronaveServicio','aeronaves_consultas','ActualizaAeronaveServicio',function($scope,eliminaAeronaveServicio,aeronaves_consultas,ActualizaAeronaveServicio) {
  $scope.aeronaves_consultas = aeronaves_consultas;
      console.log(aeronaves_consultas);
       $scope.elimina_aero=function(folio) {
    //console.log(altarequisicion);
    console.log(folio);
      eliminaAeronaveServicio.elimina_aeronave(folio).then(
        function(data) {
          console.log(data);
          alert("Aeronave Eliminada....");
          location.reload();
        })         
  }

         $scope.muestra_aero=function(objeto) {
    console.log(objeto);
    $scope.detalle_aeronave = objeto; 

    $scope.aeronave_edi = {
    numeroAeronave: $scope.detalle_aeronave.numeroAeronave,
    matricula:$scope.detalle_aeronave.matricula,
    modelo:$scope.detalle_aeronave.modelo,
    numeroSerie:$scope.detalle_aeronave.numeroSerie,
    nacionalidad:$scope.detalle_aeronave.nacionalidad,
    tiempovuelo:$scope.detalle_aeronave.tiempovuelo,
     aterrizaje:$scope.detalle_aeronave.aterrizaje
  }            
  }

    $scope.Actualiza_aeronave=function() {   

      ActualizaAeronaveServicio.actualiza_aeronave($scope.aeronave_edi).then(
        function(data) {
          console.log(data);
          alert("Aeronave Modificada");
          location.reload();
        })  
  }
}]);