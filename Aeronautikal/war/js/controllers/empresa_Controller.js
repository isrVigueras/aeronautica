//servicio alta empresa
app.service('altaEmpresaServicio', [ '$http', '$q', function($http, $q) {
  this.alta_empresa = function(user,empresa) {
    var d = $q.defer();
    $http.post("/empresa/add/"+user,empresa).then(function(response) {
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
//servicio elmina empresa
app.service('eliminaEmpresaServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_empresa = function(id,user) {
    var d = $q.defer();
    $http.post("/empresa/delete/"+id+"/"+user).then(function(response) {
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
app.service('ActualizaEmpresaServicio', [ '$http', '$q', function($http, $q) {
  this.actualiza_empresa = function(user,objeto) {
    var d = $q.defer();
    $http.post("/empresa/update/"+user,objeto).then(function(response) {
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
app.controller('empresaController', ['$scope', 'altaEmpresaServicio','$cookies',function($scope, altaEmpresaServicio,$cookies) {
    console.log($cookies.cosa);
   $scope.empresaform = {
    idEmpresa: undefined,
    nombreEmpresa:"",
    rfc:"",
    razonSocial:"",
    pais:"",
    nombreContacto:"",
    telefono: undefined,
    email:""
  }
     $scope.guarda_cliente=function() {
    //console.log(altarequisicion);
      altaEmpresaServicio.alta_empresa($cookies.cosa,$scope.empresaform).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Empresa Guardada");
          location.href="#/Clientes/colsulta";
        })         
  }
}]);
app.controller('empresaMuestraController', ['$scope','eliminaEmpresaServicio','empresas_consultas','ActualizaEmpresaServicio','$cookies',function($scope,eliminaEmpresaServicio,empresas_consultas,ActualizaEmpresaServicio,$cookies) {
   console.log($cookies.cosa);
  $scope.empresas_consultas = empresas_consultas;
      console.log(empresas_consultas);
      
       $scope.elimina_empres=function(folio) {
    //console.log(altarequisicion);
    console.log(folio);
      eliminaEmpresaServicio.elimina_empresa(folio,$cookies.cosa).then(
        function(data) {
          console.log(data);
          alert("Empresa Eliminada");
          location.reload();
        })         
  }
    $scope.muestra_cliente=function(objeto) {
    console.log(objeto);
    $scope.detalle_cliente = objeto; 

    $scope.empresa_edi = {
    idEmpresa:$scope.detalle_cliente.idEmpresa,
    nombreEmpresa:$scope.detalle_cliente.nombreEmpresa,
    rfc:$scope.detalle_cliente.rfc,
    razonSocial:$scope.detalle_cliente.razonSocial,
    pais:$scope.detalle_cliente.pais,
    nombreContacto:$scope.detalle_cliente.nombreContacto,
    telefono:$scope.detalle_cliente.telefono,
    email:$scope.detalle_cliente.email
  }   
  }

    $scope.Actualiza_Empresa=function() {   

      ActualizaEmpresaServicio.actualiza_empresa($cookies.cosa,$scope.empresa_edi).then(
        function(data) {
          console.log(data);
          alert("Cliente Modificado");
          location.reload();
        })  
  }
}]);