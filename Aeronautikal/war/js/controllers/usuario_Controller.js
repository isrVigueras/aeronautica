//servicio inicia trabajo
app.service('IniciaTrabajoServicio', [ '$http', '$q', function($http, $q) {
  this.iniciar_trabajo = function(id) {
    var d = $q.defer();
    $http.post("/horasHombre/inicia/"+id).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio pausar trabajo
app.service('PausarTrabajoServicio', [ '$http', '$q', function($http, $q) {
  this.pausar_trabajo = function(id) {
    var d = $q.defer();
    $http.post("/horasHombre/stop/"+id).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
app.controller('UsuariosH_Hombre_Controller', ['$scope','IniciaTrabajoServicio','PausarTrabajoServicio',function($scope,IniciaTrabajoServicio,PausarTrabajoServicio) {
    //console.log(empleados_lista);
   
     $scope.iniciar_trabajo=function(id) {
    	console.log(id);
      IniciaTrabajoServicio.iniciar_trabajo(id).then(
        function(data) {
          console.log(data);
          alert("Iniciando el medidor de tiempo");
        })         
  }

     $scope.pausar_trabajo=function(id) {
    	console.log(id);
      PausarTrabajoServicio.pausar_trabajo(id).then(
        function(data) {
          console.log(data);
          alert("pausado el medidor de tiempo");
        })         
  }
}]);