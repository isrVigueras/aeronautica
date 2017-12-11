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
//Controlador de orden
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