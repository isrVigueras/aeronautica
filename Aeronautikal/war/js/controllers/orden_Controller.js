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
//servicio para generar Xls
app.service('OrdenenDocumentoService', [ '$http', '$q', function($http, $q) {
  this.genera_Xls = function(idOrden) {
    var d = $q.defer();
    $http.post("/orden/generaOrdenXls/"+idOrden).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//Controlador de orden
app.controller('ordenController', ['$scope','OrdenesService','new_folio','empresas','aeronaves',function($scope,OrdenesService,new_folio,empresas,aeronaves) {
 //$scope.fo = {}; alamcenar los datos en mi objeto para vidarlos
 $scope.empresas = empresas;
 $scope.aeronaves=aeronaves;
 $scope.new_folio = new_folio;
  console.log($scope.empresas);
    console.log($scope.aeronaves);
 $scope.fo = {

    folio: $scope.new_folio,
    fechaApertura:new Date(),
    empresa:"",
    aeronave:undefined
  }

  //$scope.CurrentDate = new Date();
$scope.new_folio = new_folio;
 console.log($scope.new_folio);
 $scope.guardar=function() {
    if ($scope.form.$valid) {
      console.log($scope.fo)
      //alert("variable comprobada: "+$scope.fo.con_nombre+" y la fecha "+ $scope.fo.fechaApertura+"folio: "+ $scope.new_folio );
      OrdenesService.genera_orden($scope.fo).then(
        function(data) {
          console.log(data);
         // alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
         location.href="#/Orden/generadas";
         alert("orden generada");
        })
              
    }else {
      alert("Hay datos inválidos");
    }
  }

}]);

app.controller("OrdenesgeneradasController", ['$scope', 'generadas','OrdenenDocumentoService',function($scope,generadas,OrdenenDocumentoService) {
$scope.generadas = generadas;

 $scope.genera_Xls=function(id) {
  console.log(id)
      //alert("variable comprobada: "+$scope.fo.con_nombre+" y la fecha "+ $scope.fo.fechaApertura+"folio: "+ $scope.new_folio );
      OrdenenDocumentoService.genera_Xls(id).then(
        function(data) {
          console.log(data);
         // alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
         alert("Solicitud realizada");
        })
              
  }
}]);
app.controller('detalledisController', ['$scope', 'detalle_dis',function($scope, detalle_dis) {
 $scope.detalle_dis = detalle_dis;
 console.log(detalle_dis);
}]);

