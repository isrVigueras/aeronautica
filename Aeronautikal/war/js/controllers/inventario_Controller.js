//servicio alta Inventario
app.service('InventarioService', [ '$http', '$q','$rootScope', function($http, $q,$rootScope) {
  this.genera_inventario = function(id,inventario) {
    var d = $q.defer();
    $http.post("/componente/add/"+id,inventario).then(function(response) {
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
//servicio elimina Inventario
app.service('EliminaInventarioService', [ '$http', '$q', function($http, $q) {
  this.elimina_inventario = function(id) {
    var d = $q.defer();
    $http.post("/componente/delete/"+id).then(function(response) {
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

//servicio actualiza Componente
app.service('ActualizaComponenteServicio', [ '$http', '$q', function($http, $q) {
  this.Actualiza_componente = function(user,objeto) {
    var d = $q.defer();
    $http.post("/componente/update/"+user,objeto).then(function(response) {
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
app.controller("InventarioController", ['$scope','InventarioService','categoria','unidad','condicion','$rootScope','$cookies',function($scope,InventarioService,categoria,unidad,condicion,$rootScope,$cookies) {
   console.log($cookies.cosa);
  /*$scope.Txt=true;
      if(!$rootScope.variable){
        location.href="/";
        alert("no tienes papi");
      }
      else
        {
          alert("si tiene permisos");
        }*/
$scope.categoria =categoria;
$scope.unidad =unidad;
$scope.condicion =condicion;

  $scope.filtro = {
      d_componente: ""
    }
 $scope.inventario = {
    id:undefined,
    fechaApertura:new Date(),
    d_componente:"",
    d_descripcion:"",
    d_parte:"",
    d_cantidad:undefined,
    d_pendientes:undefined,
    idCategoria:undefined,
    idCondicion:undefined,
    idUnidad:undefined,
    d_modelo:"",
    d_marca:"",
    d_observaciones:"",
    maximo:undefined,
    minimo:undefined,
    anaquel:"",
    repisa:"",
    imagen:"",
    noSerie:"",
    certificado8130:Boolean

  }

   $scope.alta_inventario=function() {
    console.log($scope.inventario);
      //alert("variable comprobada: "+$scope.inventario.d_componente+" y la fecha "+ $scope.inventario.fechaApertura);
      InventarioService.genera_inventario($cookies.cosa,$scope.inventario).then(
        function(data) {
          console.log(data);
          //alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
            location.href="#/Inventario/colsulta";
         alert("componente dado de alta");
        })
              
    
  }
}]);
app.controller("InventarioconsultaController", ['$scope','inv_consultas','EliminaInventarioService','$cookies','categoria','unidad','condicion','ActualizaComponenteServicio',function($scope,inv_consultas,EliminaInventarioService,$cookies,categoria,unidad,condicion,ActualizaComponenteServicio) {
 if($cookies.cosa == null){
      alert("session vacia");
      location.href="/";
    }
    else
    {
 console.log($cookies.cosa);
 $scope.inv_consultas =inv_consultas;
 $scope.categoria =categoria;
 $scope.unidad =unidad;
$scope.condicion =condicion;

  console.log($scope.inv_consultas);
   $scope.muestra=function(data) {
    console.log(data);
    $scope.detalle_componentes = data; 
  }
  $scope.Actualiza_componente=function(objeto) {

  console.log(objeto)
      ActualizaComponenteServicio.Actualiza_componente($cookies.cosa,objeto).then(
        function(data) {
          console.log(data);
          alert("Componente Modificado");
          location.reload();
        })  
  }
  }
}]);