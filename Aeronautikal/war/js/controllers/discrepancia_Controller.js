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
//servicio update discrepancia
app.service('UpdateDiscrepanciaServicio', [ '$http', '$q', function($http, $q) {
  this.update_discrepancia = function(Objeto) {
    var d = $q.defer();
    $http.post("/discrepancia/update/",Objeto).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio alta evento en discrepancia
app.service('altaEventoServicio', [ '$http', '$q', function($http, $q) {
  this.alta_evento = function(Objeto) {
    var d = $q.defer();
    $http.post("/evento/add/",Objeto).then(function(response) {
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

  $scope.muestra_discrepancia=function(data) {
    console.log(data);
    $scope.detalle_discrepancia = data; 
  }
}]);
app.controller("EditarDiscrepanciaController", ['$scope','discrepancia','UpdateDiscrepanciaServicio','altaEventoServicio','eventos',function($scope,discrepancia,UpdateDiscrepanciaServicio,altaEventoServicio,eventos) {
$scope.discrepancia =discrepancia;
$scope.eventos =eventos;
console.log($scope.discrepancia);
console.log($scope.eventos);
/*$scope.eventos=[];
 //$scope para retener la informacion en el front
 $scope.evento_tabla = {
    nombreEvento:"",
    duracion:undefined,
    costo:undefined
  }

$scope.Agregar=function(){
  
      
      lista={
          nombreEvento:$scope.evento_tabla.nombreEvento,
          duracion:$scope.evento_tabla.duracion,
          costo:$scope.evento_tabla.costo
          }
          console.log(lista);
      $scope.eventos.push(lista);
      $scope.discrepancia.entos=$scope.eventos;
      console.log($scope.discrepancia.entos);
      }    

          $scope.Quitar=function(i){
        
      }  


 //$scope para rellenar los campos y poder editarlos
 $scope.discrepancia_fo = {
    id:$scope.discrepancia.id,
    folio:$scope.discrepancia.folio,
    folioOrden:$scope.discrepancia.folioOrden,
    taller:$scope.discrepancia.taller,
    seccion:$scope.discrepancia.seccion,
    descripcion:$scope.discrepancia.descripcion,
    accion:$scope.discrepancia.accion,
    fechaApertura:new Date(),
    eventos:$scope.discrepancia.entos
  } 
  */
  $scope.discrepancia_fo = {
    id:$scope.discrepancia.id,
    folio:$scope.discrepancia.folio,
    folioOrden:$scope.discrepancia.folioOrden,
    taller:$scope.discrepancia.taller,
    seccion:$scope.discrepancia.seccion,
    descripcion:$scope.discrepancia.descripcion,
    accion:$scope.discrepancia.accion,
    fechaApertura:new Date()
  } 

   $scope.evento_tabla={
            idDiscrepancia:$scope.discrepancia.id,
            nombreEvento:"",
            duracion:undefined,
            costo:undefined
          }

  $scope.Agregar=function(){
    console.log($scope.evento);
    altaEventoServicio.alta_evento($scope.evento_tabla).then(
        function(data) {
          console.log(data);
          alert("Evento Agregado");
          location.reload();
        })
  }
$scope.guardar_edit=function(){
      $scope.discrepancia_fo.eventos = $scope.discrepancia.entos;
      console.log($scope.discrepancia_fo);
      console.log($scope.discrepancia.entos);
      UpdateDiscrepanciaServicio.update_discrepancia($scope.discrepancia_fo).then(
        function(data) {
          console.log(data);
          alert("Discrepeancia Modificada");
          location.reload();
        })

      }   

}]);