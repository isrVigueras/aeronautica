//servicio Cerrar Vale
app.service('CerrarValeService', [ '$http', '$q', function($http, $q) {
  this.Cerrar_Vale = function(idVale) {
    var d = $q.defer();
    $http.post("/vale/cerrarVale/"+idVale).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio Imprime Vale
app.service('ImprimeValeService', [ '$http', '$q', function($http, $q) {
  this.imprime_Vale = function(idVale) {
    var d = $q.defer();
    $http.post("/vale/generaValePdf/"+idVale).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);


//filtro de vales
app.filter("filtroV",["$filter",function($filter) {
  var filterFn=$filter("filter");
   
  /** Transforma el texto quitando todos los acentos diéresis, etc. **/
  function normalize(texto) {
  	texto=texto+'';
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
  function filtroV(array,expression) {
    //Lo único que hace es llamar al filter original pero pasado
    //la nueva función de comparator
    return filterFn(array,expression,comparator)
  }
   
  return filtroV;
   
}]);
app.controller("MuestraValesController", ['$scope','discrepancia_vale','CerrarValeService','ImprimeValeService',function($scope,discrepancia_vale,CerrarValeService,ImprimeValeService) {
 $scope.discrepancia_vale =discrepancia_vale;
 $scope.filtro = {
      discrepancia: ""
    }
  console.log($scope.discrepancia_vale);

   $scope.muestraV=function(data) {
    console.log(data);
    $scope.detalle_Vale = data; 
  }

   $scope.Despachar_Vale=function(id) {
    console.log(id);
    CerrarValeService.Cerrar_Vale(id).then(
        function(data) {
          console.log(data);
           location.reload();
          alert("Este vale sera cerrado definitivamente por el sistema");
          //alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
            location.href="#/Vales/consulta";
        }) 
  }
     $scope.Imprime_Vale=function(id) {
    console.log(id);
    
      ImprimeValeService.imprime_Vale(id).then(
        function(data) {
          console.log(data)
           console.log("El pdf se genero");
            window.open('pdf/Vales/'+data, "nombre de la ventana");
        })

  }
}]);