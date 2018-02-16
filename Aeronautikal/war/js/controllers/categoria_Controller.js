//servicio alta empresa
app.service('altaCategoriaServicio', [ '$http', '$q', function($http, $q) {
  this.alta_categoria = function(user,empresa) {
    var d = $q.defer();
    $http.post("/categoria/add/"+user,empresa).then(function(response) {
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
app.service('eliminaCategoriaServicio', [ '$http', '$q', function($http, $q) {
  this.elimina_categoria = function(id,user) {
    var d = $q.defer();
    $http.post("/categoria/delete/"+id+"/"+user).then(function(response) {
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
app.service('ActualizaCategoriaServicio', [ '$http', '$q', function($http, $q) {
  this.actualiza_categoria = function(user,objeto) {
    var d = $q.defer();
    $http.post("/categoria/update/"+user,objeto).then(function(response) {
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
app.controller('categoriaController', ['$scope', 'altaCategoriaServicio','$cookies',function($scope, altaCategoriaServicio,$cookies) {
   console.log($cookies.cosa);
   $scope.categoriaform = {
    id: undefined,
    clave: undefined,
    descripcion:""
  }
     $scope.guarda_categoria=function() {
    console.log($scope.categoriaform);
      altaCategoriaServicio.alta_categoria($cookies.cosa,$scope.categoriaform).then(
        function(data) {
          console.log(data);
          location.reload();
          alert("Categoria Guardada");
          location.href="#/Inventario/categoria/colsulta";
        })         
  }
}]);
app.controller('categoriaMuestraController', ['$scope','eliminaCategoriaServicio','categorias_consultas','ActualizaCategoriaServicio','$cookies',function($scope,eliminaCategoriaServicio,categorias_consultas,ActualizaCategoriaServicio,$cookies) {
  console.log($cookies.cosa);
  $scope.categorias_consultas = categorias_consultas;
      console.log(categorias_consultas);
      
       $scope.elimina_categoria=function(folio) {
    //console.log(altarequisicion);
    console.log(folio);
      eliminaCategoriaServicio.elimina_categoria(folio,$cookies.cosa).then(
        function(data) {
          console.log(data);
          alert("Categoria Eliminada");
          location.reload();
        })         
  }
    $scope.muestra_categoria=function(objeto) {
    console.log(objeto);
    $scope.detalle_categoria = objeto; 

    $scope.categoria_edi = {
    id:$scope.detalle_categoria.id,
    clave: $scope.detalle_categoria.clave,
    descripcion:$scope.detalle_categoria.descripcion
  }   
  }

    $scope.Actualiza_Categoria=function() {   

      ActualizaCategoriaServicio.actualiza_categoria($cookies.cosa,$scope.categoria_edi).then(
        function(data) {
          console.log(data);
          alert("Categoria Modificada");
          location.reload();
        })  
  }
}]);