//servicio alta Aeronave
app.service('altaAeronaveServicio', [ '$http', '$q', function($http, $q) {
  this.alta_aeronave = function(user,aeronave) {
    var d = $q.defer();
    $http.post("/aeronave/add/"+user,aeronave).then(function(response) {
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
//servicio elmina Aeronave
app.service('eliminaAeronaveServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_aeronave = function(folio,user) {
    var d = $q.defer();
    $http.post("/aeronave/delete/"+folio+"/"+user).then(function(response) {
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
//servicio actualiza Aeronave
app.service('ActualizaAeronaveServicio', [ '$http', '$q', function($http, $q) {
  this.actualiza_aeronave = function(user,objeto) {
    var d = $q.defer();
    $http.post("/aeronave/update/"+user,objeto).then(function(response) {
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
app.controller('aeronaveController', ['$scope','altaAeronaveServicio','$cookies',function($scope,altaAeronaveServicio,$cookies) {
   console.log($cookies.cosa);
   $scope.aeronaveform = {
    numeroAeronave: undefined,
    matricula:"",
    modelo:"",
    numeroSerie:"",
    nacionalidad: "",
    marca:"",
    marcas:"",
    motor1:"",
    motor2:"",
    tiempovuelo:undefined,
     aterrizaje:""
    
  }
     $scope.guarda_aero=function() {
    //console.log(altarequisicion);
    console.log($scope.aeronaveform);
      altaAeronaveServicio.alta_aeronave($cookies.cosa,$scope.aeronaveform).then(
        function(data) {
          console.log(data);
          alert("Aeronave Guardada");
          location.href="#/Aeronaves/consulta";
        })         
  }
}]);
app.controller('aeronaveMuestraController', ['$scope','eliminaAeronaveServicio','aeronaves_consultas','ActualizaAeronaveServicio','$cookies',function($scope,eliminaAeronaveServicio,aeronaves_consultas,ActualizaAeronaveServicio,$cookies) {
  console.log($cookies.cosa);
  $scope.aeronaves_consultas = aeronaves_consultas;
      console.log(aeronaves_consultas);
       $scope.elimina_aero=function(folio) {
    //console.log(altarequisicion);
    console.log(folio);
      eliminaAeronaveServicio.elimina_aeronave(folio,$cookies.cosa).then(
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
    id:$scope.detalle_aeronave.id,
    numeroAeronave: $scope.detalle_aeronave.numeroAeronave,
    matricula:$scope.detalle_aeronave.matricula,
    modelo:$scope.detalle_aeronave.modelo,
    numeroSerie:$scope.detalle_aeronave.numeroSerie,
    nacionalidad:$scope.detalle_aeronave.nacionalidad,
    marca:$scope.detalle_aeronave.marca,
    marcas:$scope.detalle_aeronave.marcas,
    motor1:$scope.detalle_aeronave.motor1,
    motor2:$scope.detalle_aeronave.motor2,
    tiempovuelo:$scope.detalle_aeronave.tiempovuelo,
     aterrizaje:$scope.detalle_aeronave.aterrizaje
  }            
  }

    $scope.Actualiza_aeronave=function() {   

      ActualizaAeronaveServicio.actualiza_aeronave($cookies.cosa,$scope.aeronave_edi).then(
        function(data) {
          console.log(data);
          alert("Aeronave Modificada");
          location.reload();
        })  
  }
}]);