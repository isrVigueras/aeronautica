//servicio asignar discrepancia
app.service('AsignarDiscServicio', [ '$http', '$q', function($http, $q) {
  this.asigna_discre = function(id,id2) {
    var d = $q.defer();
    $http.post(" /horasHombre/asignar/"+id+"/"+id2).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
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
//servicio alta usuario
app.service('altaUsuarioServicio', [ '$http', '$q', function($http, $q) {
  this.alta_usuario = function(objt) {
    var d = $q.defer();
    $http.post("usuario/registro",objt).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio elmina usuario
app.service('eliminaUsuarioServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_usuario = function(id) {
    var d = $q.defer();
    $http.post("/usuario/delete/"+id).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio alta perfil
app.service('altaPerfilServicio', [ '$http', '$q', function($http, $q) {
  this.alta_perfil = function(objeto) {
    var d = $q.defer();
    $http.post(" /perfil/add/",objeto).then(function(response) {
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
      
       $scope.elimina_puesto=function(folio) {
    //console.log(altarequisicion);
    console.log(folio);
      eliminaCondicionServicio.elimina_condicion(folio).then(
        function(data) {
          console.log(data);
          alert("condicion Eliminada");
          location.reload();
        })         
  }
    $scope.muestra_puesto=function(objeto) {
    console.log(objeto);
    $scope.detalle_condicion = objeto; 

    $scope.condicion_edi = {
    id: $scope.detalle_condicion.id,
    clave: $scope.detalle_condicion.clave,
    descripcion: $scope.detalle_condicion.descripcion  
  }   
  }

    $scope.Actualiza_puesto=function() {   

      ActualizaCondicionServicio.actualiza_condicion($scope.condicion_edi).then(
        function(data) {
          console.log(data);
          alert("condicion Modificada");
          location.reload();
        })  
  }
}]);
app.controller('HorasHombreController', ['$scope', 'empleados_lista','discrepancias','AsignarDiscServicio',function($scope, empleados_lista,discrepancias,AsignarDiscServicio) {
    $scope.discrepancias = discrepancias;
    $scope.empleados_lista = empleados_lista;
    console.log(empleados_lista);
    console.log("discrepancias para signar horas");
    console.log(discrepancias);
   //$scope.puestos_lista = puestos_lista;
   $scope.IdEmpleado = {
    idEmpleado: undefined
  }
     $scope.asigna_discre=function(id) {
    //console.log(altarequisicion);
     console.log("id de horas horasHombre");
      console.log(id);
      console.log("id empleado");
    console.log($scope.IdEmpleado);
      AsignarDiscServicio.asigna_discre($scope.IdEmpleado.idEmpleado,id).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Discrepancia Asignada");
          })         
  }
}]);
app.controller('UsuarioController', ['$scope','altaUsuarioServicio','eliminaUsuarioServicio','AsignarDiscServicio','perfil_lista',function($scope,altaUsuarioServicio,eliminaUsuarioServicio,AsignarDiscServicio,perfil_lista) {
    $scope.perfil_lista = perfil_lista;
    console.log(perfil_lista);
  
   $scope.fomUsuarios = {
    id: undefined,
    usuario: "",
    password: "",
    perfil: "",
    //authorities:undefined,
    email: ""
  }
     $scope.alta_usuario=function() {
    console.log($scope.fomUsuarios);
      altaUsuarioServicio.alta_usuario($scope.fomUsuarios).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Nuevo Usuario Dado de Alta");
          })         
  }
}]);
app.controller('PerfilController', ['$scope','altaPerfilServicio','eliminaUsuarioServicio','AsignarDiscServicio',function($scope,altaPerfilServicio,eliminaUsuarioServicio,AsignarDiscServicio) {
    //$scope.discrepancias = discrepancias;
    //console.log(discrepancias);
  
   $scope.fomPerfiles = {
    id: undefined,
    tipo: "",
    permisos:[false,false,false,false,false,false]
  }
     $scope.alta_perfil=function() {
    console.log($scope.fomPerfiles)
      altaPerfilServicio.alta_perfil($scope.fomPerfiles).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("El Nuevo Perfil se ha Creado");
          })         
  }
}]);