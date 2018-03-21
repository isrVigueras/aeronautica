//servicio asignar discrepancia
app.service('AsignarDiscServicio', [ '$http', '$q', function($http, $q) {
  this.asigna_discre = function(id,id2,user) {
    var d = $q.defer();
    $http.post(" /horasHombre/asignar/"+id+"/"+id2+"/"+user).then(function(response) {
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
//servicio alta puesto
app.service('altaPuestoServicio', [ '$http', '$q', function($http, $q) {
  this.alta_puesto = function(user,objt) {
    var d = $q.defer();
    $http.post("/puesto/add/"+user,objt).then(function(response) {
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
//servicio actualiza puesto
app.service('ActualizaPuestoServicio', [ '$http', '$q', function($http, $q) {
  this.actualiza_puesto = function(user,objeto) {
    var d = $q.defer();
    $http.post("/puesto/update/"+user,objeto).then(function(response) {
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
//servicio eliminar puesto
app.service('EliminaPuestoServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_puesto = function(id,user) {
    var d = $q.defer();
    $http.post("/puesto/delete/"+id+"/"+user).then(function(response) {
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
//servicio elmina condicion
app.service('eliminaCondicionServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_condicion = function(id) {
    var d = $q.defer();
    $http.post("/condicion/delete/"+id).then(function(response) {
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
 //servicio actualiza empresa
app.service('ActualizaCondicionServicio', [ '$http', '$q', function($http, $q) {
  this.actualiza_condicion = function(objeto) {
    var d = $q.defer();
    $http.post("/condicion/update",objeto).then(function(response) {
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
//servicio alta usuario
app.service('altaUsuarioServicio', [ '$http', '$q', function($http, $q) {
  this.alta_usuario = function(user,objt) {
    var d = $q.defer();
    $http.post("usuario/registro/"+user,objt).then(function(response) {
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
//servicio alta perfil
app.service('altaPerfilServicio', [ '$http', '$q', function($http, $q) {
  this.alta_perfil = function(user,objeto) {
    var d = $q.defer();
    $http.post("/perfil/add/"+user,objeto).then(function(response) {
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
//servicio elimina perfil
app.service('eliminaPerfilServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_perfil = function(id,user) {
    var d = $q.defer();
    $http.post("/perfil/delete/"+id+"/"+user).then(function(response) {
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
//servicio elimina usuario
app.service('eliminaUsuarioServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_usuario = function(id,user) {
    var d = $q.defer();
    $http.post("/usuario/delete/"+id+"/"+user).then(function(response) {
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
 //servicio actualiza usuario
app.service('ActualizaUsuarioServicio', [ '$http', '$q', function($http, $q) {
  this.actualiza_usuario = function(user,objeto) {
    var d = $q.defer();
    $http.post("/usuario/update/"+user,objeto).then(function(response) {
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

app.controller('AdminController', ['$scope', 'altaPuestoServicio','$cookies',function($scope, altaPuestoServicio,$cookies) {
    console.log($cookies.cosa);
   $scope.puestoform = {
    id: undefined,
    Clave: undefined,
    Descripcion:""
  }
     $scope.guarda_puesto=function() {
    //console.log(altarequisicion);
    console.log($scope.puestoform);
      altaPuestoServicio.alta_puesto($cookies.cosa,$scope.puestoform).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Puesto Guardado");
          location.href="#/Admin/Puestos/Consulta";
        })         
  }
}]);
app.controller('PuestoMuestraController', ['$scope','EliminaPuestoServicio','ActualizaPuestoServicio','puestos_lista','$cookies',function($scope,EliminaPuestoServicio,ActualizaPuestoServicio,puestos_lista,$cookies) {
   console.log($cookies.cosa);
  $scope.puestos_lista = puestos_lista;
      console.log(puestos_lista);
      
       $scope.elimina_puesto=function(id) {
    //console.log(altarequisicion);
    console.log(id);
      EliminaPuestoServicio.elimina_puesto(id,$cookies.cosa).then(
        function(data) {
          console.log(data);
          alert("Puesto Eliminado");
          location.reload();
        })         
  }
    $scope.muestra_puesto=function(objeto) {
    console.log(objeto);
    $scope.detalle_puesto = objeto; 

    $scope.puesto_edi = {
    id: $scope.detalle_puesto.id,
    Clave: $scope.detalle_puesto.Clave,
    Descripcion: $scope.detalle_puesto.Descripcion  
  }   
  }

    $scope.Actualiza_puesto=function() {   
      console.log($scope.puesto_edi);
      ActualizaPuestoServicio.actualiza_puesto($cookies.cosa,$scope.puesto_edi).then(
        function(data) {
          console.log(data);
          alert("Puesto Modificada");
          location.reload();
        })  
  }
}]);
app.controller('HorasHombreController', ['$scope', 'usuarios_lista','discrepancias','AsignarDiscServicio','$cookies',function($scope, usuarios_lista,discrepancias,AsignarDiscServicio,$cookies) {
     console.log($cookies.cosa);
    $scope.discrepancias = discrepancias;
    $scope.empleados_lista = usuarios_lista;
    console.log(usuarios_lista);
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
      AsignarDiscServicio.asigna_discre($scope.IdEmpleado.idEmpleado,id,$cookies.cosa).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Discrepancia Asignada");
          })         
  }
}]);
app.controller('UsuarioController', ['$scope','altaUsuarioServicio','AsignarDiscServicio','perfil_lista','$cookies','puestos',function($scope,altaUsuarioServicio,AsignarDiscServicio,perfil_lista,$cookies,puestos) {
     console.log($cookies.cosa);
    $scope.perfil_lista = perfil_lista;
    $scope.puestos_lista = puestos;
    console.log(perfil_lista);
  
   $scope.fomUsuarios = {
    id: undefined,
    usuario: "",
    password: "",
    perfil: "",
    aPaterno: "",
    aMaterno:"",
    idPuesto: undefined

  }
     $scope.alta_usuario=function() {
    console.log($scope.fomUsuarios);
      altaUsuarioServicio.alta_usuario($cookies.cosa,$scope.fomUsuarios).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Nuevo Usuario Dado de Alta");
          })         
  }
}]);
app.controller('UsuarioMuestraController', ['$scope','usuarios_lista','perfil_lista','eliminaUsuarioServicio','AsignarDiscServicio','$cookies','ActualizaUsuarioServicio','puestos_lista',function($scope,usuarios_lista,perfil_lista,eliminaUsuarioServicio,AsignarDiscServicio,$cookies,ActualizaUsuarioServicio,puestos_lista) {
     console.log($cookies.cosa);
    $scope.usuarios_lista = usuarios_lista;
    console.log(usuarios_lista);
    $scope.perfil_lista = perfil_lista;
    console.log(perfil_lista);
    console.log("ls pues");
      $scope.puestos_lista = puestos_lista;
      console.log(puestos_lista);
 $scope.muestra_usu=function(objeto) {
    console.log(objeto);
     $scope.detalle_usuario = objeto;
       
    $scope.editaUsuarios = {
    id: $scope.detalle_usuario.id,
    usuario: $scope.detalle_usuario.usuario,
    password: $scope.detalle_usuario.password,
    perfil: $scope.detalle_usuario.perfil,
    idPuesto:$scope.detalle_usuario.idPuesto,
    nombre:$scope.detalle_usuario.nombre,
    //authorities:undefined,
  }

    }
     $scope.elimina_usuario=function(id) {
    console.log(id);
      eliminaUsuarioServicio.elimina_usuario(id,$cookies.cosa).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("El Usuario Eliminado");
          })         
  }
   $scope.actualiza_usuario=function() {
    console.log($scope.editaUsuarios);
      ActualizaUsuarioServicio.actualiza_usuario($cookies.cosa,$scope.editaUsuarios).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Usuario Modificado");
          })         
  }
}]);
app.controller('PerfilController', ['$scope','altaPerfilServicio','eliminaUsuarioServicio','AsignarDiscServicio','$cookies',function($scope,altaPerfilServicio,eliminaUsuarioServicio,AsignarDiscServicio,$cookies) {
     console.log($cookies.cosa);
    //$scope.discrepancias = discrepancias;
    //console.log(discrepancias);
  
   $scope.fomPerfiles = {
    id: undefined,
    tipo: "",
    permisos:[false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false]
  }
     $scope.alta_perfil=function() {
    console.log($scope.fomPerfiles)
      altaPerfilServicio.alta_perfil($cookies.cosa,$scope.fomPerfiles).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("El Nuevo Perfil se ha Creado");
          })         
  }
}]);
app.controller('PerfilMuestraController', ['$scope','perfil_regis','eliminaPerfilServicio','$cookies',function($scope,perfil_regis,eliminaPerfilServicio,$cookies) {
    if($cookies.cosa == null){
      alert("session vacia");
      location.href="/";
    }
    else
    {
    $scope.perfil_regis = perfil_regis;
      console.log($scope.perfil_regis);
     console.log($cookies.cosa);
    //$scope.discrepancias = discrepancias;
    //console.log(discrepancias);
  
   $scope.fomPerfiles = {
    id: undefined,
    tipo: "",
    permisos:[false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false,false,
              false,false,false,false,false]
  }
   $scope.elimina_perfil=function(id) {
    console.log(id);
      eliminaPerfilServicio.elimina_perfil(id,$cookies.cosa).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Perfil Eliminado");
          })         
  }
   } 
}]);