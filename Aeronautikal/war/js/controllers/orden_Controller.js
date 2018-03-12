//servicio para mandar los datos del formulario a la ruta del maping del controlador java mediante post
app.service('OrdenesService', [ '$http', '$q', function($http, $q) {
  this.genera_orden = function(id,fo) {
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
    $http.post("/orden/add/"+id,fo).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
         if(response.status==403){
      alert("No tienes Permisos");
      location.href="#/Inicio/paginaPrincipal";
      //$rootScope.authenticated = false;
                              }
    });
    return d.promise;
  }
} ]);
//servicio para generar Xls
app.service('OrdenenDocumentoService', [ '$http', '$q', function($http, $q) {
  this.genera_Xls = function(idOrden,user) {
    var d = $q.defer();
    $http.post("/orden/generaOrdenXls/"+idOrden+"/"+user).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
       if(response.status==403){
      alert("No tienes Permisos");
      location.href="#/Inicio/paginaPrincipal";
      //$rootScope.authenticated = false;
                              }
    });
    return d.promise;
  }
} ]);
//servicio elmina orden
app.service('EliminarOrdenService', [ '$http', '$q', function($http, $q) {
  this.elimina_orden = function(id,user) {
    var d = $q.defer();
    $http.post("/empleado/delete/"+id+"/"+user).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
       if(response.status==403){
      alert("No tienes Permisos");
      location.href="#/Inicio/paginaPrincipal";
      //$rootScope.authenticated = false;
                              }
    });
    return d.promise;
  }
} ]);
//Controlador de orden
app.controller('ordenController', ['$scope','OrdenesService','new_folio','empresas','aeronaves','$cookies',function($scope,OrdenesService,new_folio,empresas,aeronaves,$cookies) {
 //$scope.fo = {}; alamcenar los datos en mi objeto para vidarlos
  console.log($cookies.cosa);
 $scope.empresas = empresas;
 $scope.aeronaves=aeronaves;
 $scope.new_folio = new_folio;
  console.log($scope.empresas);
    console.log($scope.aeronaves);
 $scope.fo = {

    folio: $scope.new_folio,
    fechaApertura:new Date(),
    empresa:"",
    aeronave:undefined,
    accionGeneral:""
  }

  //$scope.CurrentDate = new Date();
$scope.new_folio = new_folio;
 console.log($scope.new_folio);
 $scope.guardar=function() {
    if ($scope.form.$valid) {
      console.log($scope.fo)
      //alert("variable comprobada: "+$scope.fo.con_nombre+" y la fecha "+ $scope.fo.fechaApertura+"folio: "+ $scope.new_folio );
      OrdenesService.genera_orden($cookies.cosa,$scope.fo).then(
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

app.controller("OrdenesgeneradasController", ['$scope', 'generadas','OrdenenDocumentoService','$cookies',function($scope,generadas,OrdenenDocumentoService,$cookies) {
console.log($cookies.cosa);
$scope.user =$cookies.cosa;
console.log($scope.user);
$scope.generadas = generadas;

 $scope.genera_Xls=function(id) {
  console.log("sdssss");
      //alert("variable comprobada: "+$scope.fo.con_nombre+" y la fecha "+ $scope.fo.fechaApertura+"folio: "+ $scope.new_folio );
      OrdenenDocumentoService.genera_Xls(id,$cookies.cosa).then(
        function(data) {
          console.log(data);
         // alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
        console.log("El pdf se genero");
      
      //window.open(data);

         //window.open('pdf/OTs/'+data, "nombre de la ventana", "width=300, height=200")
         //location.href="pdf/OTs/"+data;
         //location.href="#/Consulta/PDF/"+data;
        })
              
  }
   $scope.elimina_orden=function(id) {
  console.log("entro a eliminar orden");
      //alert("variable comprobada: "+$scope.fo.con_nombre+" y la fecha "+ $scope.fo.fechaApertura+"folio: "+ $scope.new_folio );
      EliminarOrdenService.elimina_orden(id,$cookies.cosa).then(
        function(data) {
          console.log(data);
         // alert("Los datos aqui se habrían enviado al servidor  y estarían validados en la parte cliente");
        console.log("se Elimino la Orden");
         location.href="#/Orden/generadas";
        })
              
  }

}]);
app.controller('detalledisController', ['$scope', 'detalle_dis','$cookies',function($scope, detalle_dis,$cookies) {
 console.log($cookies.cosa);
 $scope.detalle_dis = detalle_dis;
 console.log(detalle_dis);
}]);

