//servicio alta Discrepeancia
app.service('DiscrepanciaServicio', [ '$http', '$q', function($http, $q) {
  this.genera_discrepancia = function(folio,discrepancia) {
    var d = $q.defer();
    $http.post("/discrepancia/add/"+folio,discrepancia).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);

app.controller("DiscrepanciamuestraController", ['$scope','discrepancias','foliarrastrado','DiscrepanciaServicio','insertaRequiServicio',function($scope,discrepancias,foliarrastrado,DiscrepanciaServicio,insertaRequiServicio) {
$scope.discrepancias =discrepancias;
console.log($scope.discrepancias); 
 $scope.discrepancia = {
    folio:undefined,
    fechaApertura:new Date(),
    taller:"",
    seccion:"",
    descripcion:"",
    accion:""
    
  }
  $scope.foliarrastrado =foliarrastrado;
   $scope.alta_discrepancia=function() {
    console.log($scope.foliarrastrado);
      alert("variable comprobada folio arrastrado: "+$scope.foliarrastrado);
      DiscrepanciaServicio.genera_discrepancia($scope.foliarrastrado,$scope.discrepancia).then(
        function(data) {
          console.log(data);
          alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
  
          location.reload();
        })
              
   
  }

 $scope.req = {
    fechaApertura:new Date(),
    r_nombre:"",
    r_cantidad:undefined
  }
      $scope.alta_requisicion=function() {
      alert("variable comprobada: ");
      insertaRequiServicio.inserta_requisicion($scope.req).then(
        function(data) {
          console.log(data);
         location.reload();
        })         
  }
}]);