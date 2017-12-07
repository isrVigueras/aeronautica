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
//servicio alta Discrepeancia
app.service('DiscrepanciaServicio', [ '$http', '$q', function($http, $q) {
  this.genera_discrepancia = function(discrepancia) {
    var d = $q.defer();
    $http.post("/discrepancia/add",discrepancia).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);



//recursos remotos para traer informacion, mediante el get generacion de promesas
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
     this.generacion_folio = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/orden/getFolio'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

    this.empresas = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/orden/getEmpresas'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
    this.discrepancias = function(folio) {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/discrepancia/getByOrden/'+folio
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

   this.Detalle_discrepancias = function(folio) {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/orden/find/'+folio
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
    controller: "ordenController",
    resolve: {
      new_folio:['remoteResource',function(remoteResource) {
        return remoteResource.generacion_folio();
      }],
       empresass:['remoteResource',function(remoteResource) {
        return remoteResource.empresas();
      }]
    }
  });

  $routeProvider.when('/Orden/detalle/:folio', {
    templateUrl: "Detalle_discrepancias.html",
    controller: "detalledisController",
     resolve: {
      detalle_dis:['remoteResource','$route',function(remoteResource,$route) {
        return remoteResource.Detalle_discrepancias($route.current.params.folio);
      }]
    }
  });

    $routeProvider.when('/Orden/discrepancia/:folio', {
    templateUrl: "Discrepancias.html",
    controller: "DiscrepanciamuestraController",
       resolve: {
      discrepancias:['remoteResource','$route',function(remoteResource,$route) {
        return remoteResource.discrepancias($route.current.params.folio);
      }]
    }

  });      
        $routeProvider.when('/Inventario/alta', {
    templateUrl: "inventario.html",
    controller: "InventarioController"
  });     
           $routeProvider.when('/Inventario/colsulta', {
    templateUrl: "consul_inventario.html",
    controller: "InventarioconsultaController",
     resolve: {
      inv_consultas:['remoteResource',function(remoteResource) {
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
app.filter("filteri18n",["$filter",function($filter) {
  var filterFn=$filter("filter");
   
  /** Transforma el texto quitando todos los acentos diéresis, etc. **/
  function normalize(texto) {
    texto = texto.replace(/[áàäâ]/g, "a");
    texto = texto.replace(/[éèëê]/g, "e");
    texto = texto.replace(/[íìïî]/g, "i");
    texto = texto.replace(/[óòôö]/g, "o");
    texto = texto.replace(/[úùüü]/g, "u");
    texto = texto.toUpperCase();
    return texto;
  }
    
  /** Esta función es el comparator en el filter **/
  function comparator(actual, expected) {
      if (normalize(actual).indexOf(normalize(expected))>=0) {
        return true;
      } else {
        return false;
      }
  }
   
  /** Este es realmente el filtro **/
  function filteri18n(array,expression) {
    //Lo único que hace es llamar al filter original pero pasado
    //la nueva función de comparator
    return filterFn(array,expression,comparator)
  }
   
  return filteri18n;
   
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

app.controller('ordenController', ['$scope','OrdenesService','new_folio',function($scope,OrdenesService,new_folio) {
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
$scope.new_folio = new_folio;
 $scope.guardar=function() {
    if ($scope.form.$valid) {
      alert("variable comprobada: "+$scope.fo.con_nombre+" y la fecha "+ $scope.fo.fechaApertura+"folio: "+ $scope.new_folio );
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

app.controller('detalledisController', ['$scope', 'detalle_dis',function($scope, detalle_dis) {
 $scope.detalle_dis = detalle_dis;
}]);
app.controller("OrdenesgeneradasController", ['$scope', 'generadas',function($scope,generadas) {
$scope.generadas = generadas;
}]);

app.controller("InventarioController", ['$scope','InventarioService',function($scope,InventarioService) {
  $scope.filtro = {
      d_componente: ""
    }
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
}]);
app.controller("InventarioconsultaController", ['$scope','inv_consultas',function($scope,inv_consultas) {
 $scope.inv_consultas =inv_consultas;
  console.log($scope.inv_consultas);
}]);
app.controller("DiscrepanciaController", ['$scope','DiscrepanciaServicio',function($scope,DiscrepanciaServicio) {

 $scope.discrepancia = {
    id:undefined,
    fechaApertura:new Date(),
    d_componente:"",
    d_parte:"",
    d_cantidad:undefined,
    d_pendientes:undefined,    
    d_requisicion:"",
    d_vale:""
  }
   $scope.alta_discrepancia=function() {
    if ($scope.form.$valid) {
      alert("variable comprobada: "+$scope.discrepancia.d_componente+" y la fecha "+ $scope.discrepancia.fechaApertura);
      DiscrepanciaServicio.genera_discrepancia($scope.discrepancia).then(
        function(data) {
          console.log(data);
          alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
        })
              
    }else {
      alert("Hay datos inválidos");
    }
  }
}]);
app.controller("DiscrepanciamuestraController", ['$scope','discrepancias',function($scope,discrepancias) {
$scope.discrepancias =discrepancias; 
}]);
app.controller("MainController", ['$scope',function($scope) {

}]);