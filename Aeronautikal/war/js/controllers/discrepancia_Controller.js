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
//servicio alta componente en discrepancia
app.service('altaComponenteServicio', [ '$http', '$q', function($http, $q) {
  this.alta_componente = function(Objeto) {
    var d = $q.defer();
    $http.post("/componenteDiscrepancia/add/",Objeto).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio borrar evento en discrepancia
app.service('deleteEventoServicio', [ '$http', '$q', function($http, $q) {
  this.delete_evento = function(id) {
    var d = $q.defer();
    $http.post("/evento/delete/"+id).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio borrar componente en discrepancia
app.service('deleteComponenteServicio', [ '$http', '$q', function($http, $q) {
  this.delete_componente = function(id) {
    var d = $q.defer();
    $http.post("/componenteDiscrepancia/delete/"+id).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio update evento en la discrepancia
app.service('UpdateEventoServicio', [ '$http', '$q', function($http, $q) {
  this.update_Evento = function(Objeto) {
    var d = $q.defer();
    $http.post("/evento/update/",Objeto).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//servicio que trae el detalle completo de la discrepancia
app.service('DetalleDiscreServicio', [ '$http', '$q', function($http, $q) {
  this.detalle_Discrepancia = function(id) {
    var d = $q.defer();
    $http.post("/discrepancia/findDetalle/"+id).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
app.controller("DiscrepanciamuestraController", ['$scope','inv_consultas','discrepancias','foliarrastrado','DiscrepanciaServicio','insertaRequiServicio','DetalleDiscreServicio',function($scope,inv_consultas,discrepancias,foliarrastrado,DiscrepanciaServicio,insertaRequiServicio,DetalleDiscreServicio) {
$scope.discrepancias =discrepancias;
 $scope.provincias=inv_consultas; 
  $scope.miProvinciaSeleccionada=null

console.log($scope.provincias); 
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
    console.log($scope.foliarrastrado+","+$scope.discrepancia.numero_piezas+","+$scope.discrepancia.folio_componente);
      alert("variable comprobada folio arrastrado: "+$scope.foliarrastrado);
      DiscrepanciaServicio.genera_discrepancia($scope.foliarrastrado,$scope.discrepancia).then(
        function(data) {
          console.log(data);
          alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
  
          location.reload();
        })
              
   
  }

  $scope.muestra_discrepancia=function(objeto) {
    console.log(objeto);
    $scope.detalle_discrepancia = objeto;
      DetalleDiscreServicio.detalle_Discrepancia($scope.detalle_discrepancia.id).then(
        function(data) {
          $scope.detalle_dis =data;
           console.log("scope");
          console.log($scope.detalle_dis);
        })

  }
}]);
app.controller("EditarDiscrepanciaController",
 ['$scope','discrepancia','UpdateDiscrepanciaServicio','altaEventoServicio','eventos','listado_inv','altaComponenteServicio','componentes','deleteEventoServicio','deleteComponenteServicio','componentes_0','insertaRequiServicio','UpdateEventoServicio',
 function($scope,discrepancia,UpdateDiscrepanciaServicio,altaEventoServicio,eventos,listado_inv,altaComponenteServicio,componentes,deleteEventoServicio,deleteComponenteServicio,componentes_0,insertaRequiServicio,UpdateEventoServicio) 
 {
$scope.discrepancia =discrepancia;
$scope.eventos =eventos;
$scope.listado_inv =listado_inv;
$scope.componentes =componentes;
$scope.componentes_0 =componentes_0;

console.log("datos discrepancia");
console.log($scope.discrepancia);
console.log("datos eventos");
console.log($scope.eventos);
console.log("datos inventario");
console.log($scope.listado_inv);
console.log("datos componentes");
console.log($scope.componentes);
console.log("datos componentes vacios");
console.log($scope.componentes_0);

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

   $scope.componente = {
    idDiscrepancia:$scope.discrepancia.id,
    idComponente:undefined,
    cantidad:undefined
  }

   $scope.requisicion = {
    fechaApertura:new Date(),
    folio:$scope.discrepancia.folioOrden,
    folio_componente:undefined,
    folio_discrepancia:$scope.discrepancia.id,
    numero_piezas:undefined,
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
    $scope.Agregar_componente=function(){
    console.log($scope.componente);
    altaComponenteServicio.alta_componente($scope.componente).then(
        function(data) {
         var d= data;
         console.log(d);

          if (d == 0) {
          alert("Componente Agregado");
          location.reload();
          }
          else
          {
            console.log("entro a la funcion requisicion")
            alert("Se realizo una requisicion. las piezas no son suficientes");
            location.reload();
          
          }
        })
  }
    $scope.borrar_componente=function(id){
    console.log(id);
    deleteComponenteServicio.delete_componente(id).then(
        function(data) {
          console.log(data);
          alert("Componente Agregado");
          location.reload();
        })
  }
      $scope.borrar_evento=function(id){
    console.log(id);
    deleteEventoServicio.delete_evento(id).then(
        function(data) {
          console.log(data);
          alert("Componente Borrado");
          location.reload();
        })
  }
      $scope.alta_requisicion=function() {
      console.log($scope.requisicion);
      insertaRequiServicio.inserta_requisicion($scope.requisicion).then(
        function(data) {
          console.log(data);
          location.href="#/Orden/discrepancia/"+$scope.requisicion.folio;
          location.reload();
          alert("Requisicion Agregada");
        })         
  }
     $scope.muestra_evento=function(data) {
    console.log(data);
    $scope.detalle_evento = data;

     $scope.evento_edi={
            idDiscrepancia:$scope.discrepancia.id,
            idEvento:$scope.detalle_evento.idEvento,
            nombreEvento:$scope.detalle_evento.nombreEvento,
            duracion:$scope.detalle_evento.duracion,
            costo:$scope.detalle_evento.costo
          } 
  }

   $scope.Actualizar_evento=function() {
    console.log($scope.evento_edi);
      UpdateEventoServicio.update_Evento($scope.evento_edi).then(
        function(data) {
          console.log(data);
          alert("Evento Modificado");
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
}]);