var app = angular.module('app', ['ngRoute']);
//servicio para mandar los datos del formulario a la ruta del maping del controlador java mediante post
app.service('OrdenesService', [ '$http', '$q', function($http, $q) {
  this.genera_orden = function(fo) {
    var d = $q.defer();
    /*var enviar = {
      lista : folio,
              fechaApertura,
              con_nombre,    
              con_telefono,
              con_correo,
              empresa,
              a_matricula,
              a_modelo,
              n_serie,
              a_t_vuelo,
              a_t_aterrizaje
    }*/
    $http.post("/orden/add/",fo).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio alta Inventario
app.service('InventarioService', [ '$http', '$q', function($http, $q) {
  this.genera_inventario = function(inventario) {
    var d = $q.defer();
    $http.post("/componente/add",inventario).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);


//recursos remotos mediante el get generacion de promesas
function RemoteResource($http,$q, baseUrl) {
  this.get = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/dato.json'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

    this.listado = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/orden/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

      this.listado_inv = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/componente/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
}
//Provedor de recursos remotos , es el provedor que nos permite conectar las promesas con los datos json
function RemoteResourceProvider() {
  var _baseUrl;
  this.setBaseUrl = function(baseUrl) {
    _baseUrl = baseUrl;
  }
  this.$get = ['$http','$q',function($http,$q) {
      return new RemoteResource($http,$q, _baseUrl);
  }];
}

app.provider("remoteResource", RemoteResourceProvider);


app.constant("baseUrl", ".");
app.config(['baseUrl', 'remoteResourceProvider',
  function(baseUrl, remoteResourceProvider) {
    remoteResourceProvider.setBaseUrl(baseUrl);
  }
]);

//configuracion de rutas para el redireccionamiento
app.config(['$routeProvider',function($routeProvider) {
 
  $routeProvider.when('/', {
    templateUrl: "login.html",
    controller: "MainController"
  }); 
 
  $routeProvider.when('/Orden/generadas', {
    templateUrl: "Ordenes_generadas.html",
    controller: "OrdenesgeneradasController",
    resolve: {
      generadas:['remoteResource',function(remoteResource) {
        return remoteResource.listado();
      }]
    }
  });
     
  $routeProvider.when('/Orden/generar', {
    templateUrl: "Orden_de_trabajo.html",
    controller: "ordenController"
  });

  $routeProvider.when('/Orden/detalle', {
    templateUrl: "Detalle_discrepancias.html",
    controller: "detalledisController"
  });

    $routeProvider.when('/Orden/discrepancia', {
    templateUrl: "Discrepancias.html",
    controller: ""
  });      
        $routeProvider.when('/Inventario/alta', {
    templateUrl: "inventario.html",
    controller: "InventarioController"
  });     
           $routeProvider.when('/Inventario/colsulta', {
    templateUrl: "consul_inventario.html",
    controller: "InventarioController",
     resolve: {
      inv_altas:['remoteResource',function(remoteResource) {
        return remoteResource.listado_inv();
      }]
    }
  });  
  /*$routeProvider.when('/seguro/edit/:idSeguro', {
    templateUrl: "Orden_de_trabajo.html",
    controller: "DetalleSeguroController",
    resolve: {
      seguro:['remoteResource','$route',function(remoteResource,$route) {
        return remoteResource.get($route.current.params.idSeguro);
      }]
    }
  });*/
   
  $routeProvider.otherwise({
        redirectTo: '/'
  });   
 
}]);

//directiva de angular para mostrar el calendario usando el plugin de Jquery
app.directive('caDatepicker', [function(dateFormat) {
  return {
    restrict: 'A',
    link: function($scope, element, attributes) {
     
      element.datepicker({
        dateFormat: attributes.caDatepicker,
        onSelect: function() {
          $(this).trigger('change');
        }
      });
    }
  };
}]);

app.controller('ordenController', ['$scope','OrdenesService',function($scope,OrdenesService) {
 //$scope.fo = {}; alamcenar los datos en mi objeto para vidarlos
 $scope.fo = {

    folio:undefined,
    fechaApertura:new Date(),
    con_nombre:"",    
    con_telefono:undefined,
    con_correo:"",
    empresa:"",
    a_matricula:"",
    a_modelo:"",
    n_serie:"",
    a_t_vuelo:"",
    a_t_aterrizaje:""
  }

  //$scope.CurrentDate = new Date();

 $scope.guardar=function() {
    if ($scope.form.$valid) {
      alert("variable comprobada: "+$scope.fo.con_nombre+" y la fecha "+ $scope.fo.fechaApertura);
      OrdenesService.genera_orden($scope.fo).then(
        function(data) {
          console.log(data);
          alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
        })
              
    }else {
      alert("Hay datos inválidos");
    }
  }

}]);

app.controller('detalledisController', ['$scope', 'remoteResource',function($scope, remoteResource) {
 $scope.detalle ={

    folio:undefined,
    con_nombre:"",    
    con_telefono:undefined,
    con_correo:"",
    empresa:"",
    a_matricula:"",
    a_modelo:"",
    n_serie:"",
    a_t_vuelo:"",
    a_t_aterrizaje:""
  }


  remoteResource.get().then(function(detalle) {
      $scope.detalle = detalle;
    }, function(status) {
      alert("Ha fallado la petición. Estado HTTP:" + status);
    });

}]);
app.controller("OrdenesgeneradasController", ['$scope', 'generadas',function($scope,generadas) {
$scope.generadas = generadas;
}]);

app.controller("InventarioController", ['$scope','InventarioService','inv_altas',function($scope,InventarioService,inv_altas) {
 $scope.inventario = {
    id:undefined,
    fechaApertura:new Date(),
    d_componente:"",
    d_parte:"",
    d_cantidad:undefined,
    d_pendientes:undefined,    
    d_requisicion:"",
    d_vale:""
  }

   $scope.alta_inventario=function() {
    if ($scope.form.$valid) {
      alert("variable comprobada: "+$scope.inventario.d_componente+" y la fecha "+ $scope.inventario.fechaApertura);
      InventarioService.genera_inventario($scope.inventario).then(
        function(data) {
          console.log(data);
          alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
        })
              
    }else {
      alert("Hay datos inválidos");
    }
  }
  $scope.inv_altas =inv_altas;

}]);
app.controller("In", ['$scope',function($scope) {

}]);
app.controller("MainController", ['$scope',function($scope) {

}]);