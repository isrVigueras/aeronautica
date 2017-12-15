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

app.controller("DiscrepanciamuestraController", ['$scope','inv_consultas','discrepancias','foliarrastrado','DiscrepanciaServicio','insertaRequiServicio',function($scope,inv_consultas,discrepancias,foliarrastrado,DiscrepanciaServicio,insertaRequiServicio) {
$scope.discrepancias =discrepancias;
 $scope.provincias=inv_consultas; 
  $scope.miProvinciaSeleccionada=null

console.log($scope.provincias); 
 $scope.discrepancia = {
    folio:undefined,
    fechaApertura:new Date(),
    taller:"",
    seccion:"",
    descripcion:"",
    accion:"",
    folio_componente:undefined,
    numero_piezas:undefined
  }


  $scope.foliarrastrado =foliarrastrado;
   $scope.alta_discrepancia=function() {
    console.log($scope.foliarrastrado+","+$scope.discrepancia.numero_piezas+","+$scope.discrepancia.folio_componente);
      alert("variable comprobada folio arrastrado: "+$scope.foliarrastrado);
      DiscrepanciaServicio.genera_discrepancia($scope.foliarrastrado,$scope.discrepancia).then(
        function(data) {
          console.log(data);
          alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
  
          location.reload();
        })
              
   
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
app.controller("EditarDiscrepanciaController", ['$scope','discrepancia',function($scope,discrepancia) {
$scope.discrepancia =discrepancia;
 //$scope para rellenar los campos y poder editarlos
 $scope.discrepancia = {
    folio:undefined,
    fechaApertura:new Date(),
    taller:"",
    seccion:"",
    descripcion:"",
    accion:"",
    folio_componente:undefined,
    numero_piezas:undefined
  }
 //$scope para retener la informacion en el front
 $scope.evento_tabla = {
    fechaApertura:new Date(),
    nombreEvento:"",
    duracion:undefined,
    cosoto:undefined
  }

}]);