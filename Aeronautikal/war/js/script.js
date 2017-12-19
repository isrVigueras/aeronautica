var app = angular.module('app', ['ngRoute']);
//servicio alta requisicion
app.service('insertaRequiServicio', [ '$http', '$q', function($http, $q) {
  this.inserta_requisicion = function(cosas) {
    var d = $q.defer();
    $http.post("/requisicion/add/",cosas).then(function(response) {
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

     this.lista_requisiciones = function(folio) {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/requisicion/getByComponente/'+folio
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

       this.aeronaves = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/aeronave/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
  
   this.discrepancia = function(folio) {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/discrepancia/find/'+folio
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
  
    this.listado_aeronaves = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/aeronave/findAll'
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
       empresas:['remoteResource',function(remoteResource) {
        return remoteResource.empresas();
      }],
       aeronaves:['remoteResource',function(remoteResource) {
        return remoteResource.aeronaves();
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
      }],
      foliarrastrado:['remoteResource','$route',function(remoteResource,$route) {
        return ($route.current.params.folio);
      }],
      inv_consultas:['remoteResource',function(remoteResource) {
        return remoteResource.listado_inv();
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
       $routeProvider.when('/Inventario/requisicion/:folio', {
    templateUrl: "atender_requisicion.html",
    controller: "RequisicionesController",
     resolve: {
      requisiciones:['remoteResource','$route',function(remoteResource,$route) {
        return remoteResource.lista_requisiciones($route.current.params.folio);
      }]
    }
  });
        $routeProvider.when('/Discrepancias/requisicion/:folio', {
    templateUrl: "alta_requisiciones.html",
    controller: "Requisiciones_altaController",
     resolve: {
      foliarrastrad:['remoteResource','$route',function(remoteResource,$route) {
        return ($route.current.params.folio);
      }],
       inv_consultas:['remoteResource',function(remoteResource) {
        return remoteResource.listado_inv();
      }]
    }
  });
        
         $routeProvider.when('/Aeronaves/alta', {
    templateUrl: "alta_Aeronaves.html",
    controller: "aeronaveController"
  });  
         $routeProvider.when('/Clientes/alta', {
    templateUrl: "alta_Clientes.html",
    controller: "empresaController"
  });  
   
   $routeProvider.when('/Discrepancia/editar/:folio', {
    templateUrl: "editar_discrepancia.html",
    controller: "EditarDiscrepanciaController",
     resolve: {
      discrepancia:['remoteResource','$route',function(remoteResource,$route) {
        return remoteResource.discrepancia($route.current.params.folio);
      }]
    }
  });     

     $routeProvider.when('/Aeronaves/consulta', {
    templateUrl: "consulta_Aeronave.html",
    controller: "aeronaveController",
     resolve: {
      aeronaves_consultas:['remoteResource',function(remoteResource) {
        return remoteResource.listado_aeronaves();
      }]
    }
  }); 

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

app.controller('detalledisController', ['$scope', 'detalle_dis',function($scope, detalle_dis) {
 $scope.detalle_dis = detalle_dis;
}]);
app.controller("OrdenesgeneradasController", ['$scope', 'generadas',function($scope,generadas) {
$scope.generadas = generadas;
}]);

app.controller("InventarioconsultaController", ['$scope','inv_consultas',function($scope,inv_consultas) {
 $scope.inv_consultas =inv_consultas;
  console.log($scope.inv_consultas);
   $scope.muestra=function(data) {
    console.log(data);
    $scope.detalle_componentes = data; 
  }
}]);


app.controller("MainController", ['$scope',function($scope) {

}]);