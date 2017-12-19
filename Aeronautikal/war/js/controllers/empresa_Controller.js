//servicio alta empresa
app.service('altaEmpresaServicio', [ '$http', '$q', function($http, $q) {
  this.alta_empresa = function(empresa) {
    var d = $q.defer();
    $http.post("/empresa/add",empresa).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio elmina empresa
app.service('eliminaEmpresaServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_empresa = function(id) {
    var d = $q.defer();
    $http.post("/empresa/delete/"+id).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
app.controller('empresaController', ['$scope', 'altaEmpresaServicio',function($scope, altaEmpresaServicio) {
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
      altaEmpresaServicio.alta_empresa($scope.empresaform).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Empresa Guardada");
        })         
  }
}]);
app.controller('empresaMuestraController', ['$scope','eliminaEmpresaServicio','empresas_consultas',function($scope,eliminaEmpresaServicio,empresas_consultas) {
  $scope.empresas_consultas = empresas_consultas;
      console.log(empresas_consultas);
      
       $scope.elimina_empres=function(folio) {
    //console.log(altarequisicion);
    console.log(folio);
      eliminaEmpresaServicio.elimina_empresa(folio).then(
        function(data) {
          console.log(data);
          alert("Empresa Eliminada");
          location.reload();
        })         
  }
}]);