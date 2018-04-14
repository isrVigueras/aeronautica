//servicio inicia trabajo
app.service('IniciaTrabajoServicio', [ '$http', '$q', function($http, $q) {
  this.iniciar_trabajo = function(id,user) {
    var d = $q.defer();
    $http.post("/horasHombre/start/"+id+"/"+user).then(function(response) {
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
//servicio pausar trabajo
app.service('PausarTrabajoServicio', [ '$http', '$q', function($http, $q) {
  this.pausar_trabajo = function(id,user) {
    var d = $q.defer();
    $http.post("/horasHombre/pausa/"+id+"/"+user).then(function(response) {
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
//servicio stop trabajo
app.service('PararTrabajoServicio', [ '$http', '$q', function($http, $q) {
  this.parar_trabajo = function(id,user) {
    var d = $q.defer();
    $http.post("/horasHombre/stop/"+id+"/"+user).then(function(response) {
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
//servicio restart trabajo
app.service('ReiniciarTrabajoServicio', [ '$http', '$q', function($http, $q) {
  this.reiniciar_trabajo = function(id) {
    var d = $q.defer();
    $http.post("/horasHombre/restart/"+id+"/"+user).then(function(response) {
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
app.controller('UsuariosH_Hombre_Controller', ['$scope','IniciaTrabajoServicio','PausarTrabajoServicio','PararTrabajoServicio','ReiniciarTrabajoServicio','Consulta_HHombre','$cookies',function($scope,IniciaTrabajoServicio,PausarTrabajoServicio,PararTrabajoServicio,ReiniciarTrabajoServicio,Consulta_HHombre,$cookies) {
    console.log($cookies.cosa);
    $scope.Consulta_HHombre = Consulta_HHombre;
    console.log(Consulta_HHombre);
   
     $scope.iniciar_trabajo=function(id) {
    	console.log(id);
      IniciaTrabajoServicio.iniciar_trabajo(id,$cookies.cosa).then(
        function(data) {
          console.log(data);
          alert("Iniciando el medidor de tiempo");
          location.reload();
        })         
  }

     $scope.pausar_trabajo=function(id) {
    	console.log(id);
      PausarTrabajoServicio.pausar_trabajo(id,$cookies.cosa).then(
        function(data) {
          console.log(data);
          alert("pausado el medidor de tiempo");
          location.reload();
        })         
  }
       $scope.parar_trabajo=function(id) {
    	console.log(id);
      PararTrabajoServicio.parar_trabajo(id,$cookies.cosa).then(
        function(data) {
          console.log(data);
          alert("parado el medidor de tiempo");
          location.reload();
        })         
  }
       $scope.reiniciar_trabajo=function(id) {
    	console.log(id);
      ReiniciarTrabajoServicio.reiniciar_trabajo(id,$cookies.cosa).then(
        function(data) {
          console.log(data);
          alert("reiniciado el medidor de tiempo");
          location.reload();
        })         
  }
}]);

