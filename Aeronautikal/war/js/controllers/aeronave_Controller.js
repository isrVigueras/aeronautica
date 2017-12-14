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
app.controller('aeronaveController', ['$scope', 'altaAeronaveServicio',function($scope, altaAeronaveServicio) {
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
        })         
  }
}]);