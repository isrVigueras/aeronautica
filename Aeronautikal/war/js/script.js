var app = angular.module('app', ['ngRoute']);
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

app.config(['$routeProvider',function($routeProvider) {
 
  $routeProvider.when('/', {
    templateUrl: "login.html",
    controller: "MainController"
  }); 
 
  $routeProvider.when('/Orden/generadas', {
    templateUrl: "Ordenes_generadas.html",
    controller: ""
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
  
  $routeProvider.when('/seguro/edit/:idSeguro', {
    templateUrl: "Orden_de_trabajo.html",
    controller: "DetalleSeguroController",
    resolve: {
      seguro:['remoteResource','$route',function(remoteResource,$route) {
        return remoteResource.get($route.current.params.idSeguro);
      }]
    }
  });
   
  $routeProvider.otherwise({
        redirectTo: '/'
  });   
 
}]);

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
}

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

app.controller('ordenController', ['$scope', 'remoteResource','OrdenesService',function($scope, remoteResource,OrdenesService) {
 //$scope.fo = {}; alamcenar los datos en mi objeto para vidarlos
 $scope.fo = {

    folio:undefined,
    fechaApertura:undefined,
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

  remoteResource.get().then(function(fo) {
      $scope.fo = fo;
    }, function(status) {
      alert("Ha fallado la petición. Estado HTTP:" + status);
    });
  
 $scope.guardar=function() {
    if ($scope.form.$valid) {
      alert("lala"+$scope.fo.con_nombre);
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
app.controller("MainController", ['$scope',function($scope) {

}]);