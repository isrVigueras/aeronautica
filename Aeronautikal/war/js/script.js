var app = angular.module('app', []);
 app.service('OrdenesService', [ '$http', '$q', '$cookieStore', function($http, $q, $cookieStore) {
  this.genera_orden = function(folio,fechaApertura,con_nombre,con_telefono,con_correo,empresa,a_matricula,a_modelo,n_serie,a_t_vuelo,a_t_aterrizaje) {
    var d = $q.defer();
    var enviar = {
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
    }
    $http.post("/OrdenController/add/", enviar).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
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
//
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

//provedor de recursos remotos
app.constant("baseUrl", ".");
app.config(['baseUrl', 'remoteResourceProvider',
  function(baseUrl, remoteResourceProvider) {
    remoteResourceProvider.setBaseUrl(baseUrl);
  }
]);

//este es el plugin para el calendario 
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

app.controller('ordenController', ['$scope', 'remoteResource',function($scope, remoteResource) {
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
      alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
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