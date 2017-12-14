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