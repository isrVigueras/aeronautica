//servicio alta empresa
app.service('altaCategoriaServicio', [ '$http', '$q', function($http, $q) {
  this.alta_categoria = function(empresa) {
    var d = $q.defer();
    $http.post("/categoria/add",empresa).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio elmina empresa
app.service('eliminaCategoriaServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_categoria = function(id) {
    var d = $q.defer();
    $http.post("/empresa/delete/"+id).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
 //servicio actualiza empresa
app.service('ActualizaCategoriaServicio', [ '$http', '$q', function($http, $q) {
  this.actualiza_categoria = function(objeto) {
    var d = $q.defer();
    $http.post("/empresa/update",objeto).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
app.controller('categoriaController', ['$scope', 'altaCategoriaServicio',function($scope, altaCategoriaServicio) {
   $scope.categoriaform = {
    id: undefined,
    clave: undefined,
    descripcion:""
  }
     $scope.guarda_categoria=function() {
    //console.log(altarequisicion);
      altaCategoriaServicio.alta_categoria($scope.categoriaform).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Categoria Guardada");
          location.href="#/Inventario/categoria/colsulta";
        })         
  }
}]);
app.controller('categoriaMuestraController', ['$scope','eliminaCategoriaServicio','categorias_consultas','ActualizaCategoriaServicio',function($scope,eliminaCategoriaServicio,categorias_consultas,ActualizaCategoriaServicio) {
  $scope.categorias_consultas = categorias_consultas;
      console.log(categorias_consultas);
      
       $scope.elimina_categoria=function(folio) {
    //console.log(altarequisicion);
    console.log(folio);
      eliminaCategoriaServicio.elimina_categoria(folio).then(
        function(data) {
          console.log(data);
          alert("Categoria Eliminada");
          location.reload();
        })         
  }
    $scope.muestra_categoria=function(objeto) {
    console.log(objeto);
    $scope.detalle_cliente = objeto; 

    $scope.categoria_edi = {
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

    $scope.Actualiza_Categoria=function() {   

      ActualizaCategoriaServicio.actualiza_categoria($scope.categoria_edi).then(
        function(data) {
          console.log(data);
          alert("Categoria Modificada");
          location.reload();
        })  
  }
}]);