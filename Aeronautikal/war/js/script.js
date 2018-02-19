var app = angular.module('app', ['ngRoute', 'ngCookies']);
//servicio alta requisicion
app.service('insertaRequiServicio', [ '$http', '$q', function($http, $q) {
  this.inserta_requisicion = function(cosas) {
    var d = $q.defer();
    $http.post("/requisicion/add/",cosas).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
    });
    return d.promise;
  }
} ]);
//recursos remotos para traer informacion, mediante el get generacion de promesas
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

    this.listado = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/orden/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

      this.listado_inv = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/componente/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
     this.generacion_folio = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/orden/getFolio'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

    this.empresas = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/orden/getEmpresas'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
    this.discrepancias = function(folio) {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/discrepancia/getByOrden/'+folio
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

   this.Detalle_discrepancias = function(folio) {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/orden/find/'+folio
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

     this.lista_requisiciones = function(folio) {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/requisicion/getByComponente/'+folio
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

       this.aeronaves = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/aeronave/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
  
      this.discrepancia = function(folio) {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/discrepancia/find/'+folio
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
  
    this.listado_aeronaves = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/aeronave/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
  
  this.listado_empresas = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/empresa/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

  this.eventos = function(folio) {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/evento/getByDiscrepancia/'+folio
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
    this.componentes = function(folio) {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/componenteDiscrepancia/getByDiscrepancia/'+folio
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
      this.componentes_0 = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/componente/findAll0'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
   this.componentes_exis = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/componente/findAllFilter'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
  
   this.listado_categorias = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/categoria/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
     this.listado_unidades = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/unidad/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
   this.listado_condiciones = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/condicion/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

   this.categoria = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/categoria/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
  this.unidad = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/unidad/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
  this.condicion = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/condicion/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

  this.discrepancia_vale = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/vale/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
  
  this.alertas = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/alerta/findAlertas'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
    this.puestos_lista = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/puesto/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
   this.empleados_lista = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/empleado/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

   this.puestos_lista = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/puesto/findAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  } 
     this.disHoras_lista = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/horasHombre/getNoAsignadas'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
       this.dis_asignadas_lista = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/horasHombre/getAsignadas'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

         this.perfil_lista = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/perfil/getAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }

           this.usuarios_lista = function() {
    var defered=$q.defer();
    var promise=defered.promise;
    
    $http({
      method: 'GET',
      url: baseUrl + '/usuario/getAll'
    }).success(function(data, status, headers, config) {
      defered.resolve(data);
    }).error(function(data, status, headers, config) {
      defered.reject(status);
    });
    
    return promise;
    
  }
}
//Provedor de recursos remotos , es el provedor que nos permite conectar las promesas con los datos json
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

//configuracion de rutas para el redireccionamiento
app.config(['$routeProvider',function($routeProvider) {
 
  $routeProvider.when('/', {
    templateUrl: "login.html",
    controller: "navigation"
  }); 
 
  $routeProvider.when('/Orden/generadas', {
    templateUrl: "Ordenes_generadas.html",
    controller: "OrdenesgeneradasController",
    resolve: {
      generadas:['remoteResource',function(remoteResource) {
        return remoteResource.listado();
      }]
    }
  });
     
  $routeProvider.when('/Orden/generar', {
    templateUrl: "Orden_de_trabajo.html",
    controller: "ordenController",
    resolve: {
      new_folio:['remoteResource',function(remoteResource) {
        return remoteResource.generacion_folio();
      }],
       empresas:['remoteResource',function(remoteResource) {
        return remoteResource.empresas();
      }],
       aeronaves:['remoteResource',function(remoteResource) {
        return remoteResource.aeronaves();
      }]
    }
  });

  $routeProvider.when('/Orden/detalle/:folio', {
    templateUrl: "Detalle_discrepancias.html",
    controller: "detalledisController",
     resolve: {
      detalle_dis:['remoteResource','$route',function(remoteResource,$route) {
        return remoteResource.Detalle_discrepancias($route.current.params.folio);
      }]
    }
  });

    $routeProvider.when('/Orden/discrepancia/:folio', {
    templateUrl: "Discrepancias.html",
    controller: "DiscrepanciamuestraController",
       resolve: {
      discrepancias:['remoteResource','$route',function(remoteResource,$route) {
        return remoteResource.discrepancias($route.current.params.folio);
      }],
      foliarrastrado:['remoteResource','$route',function(remoteResource,$route) {
        return ($route.current.params.folio);
      }],
      inv_consultas:['remoteResource',function(remoteResource) {
        return remoteResource.listado_inv();
      }]
    }

  });      
        $routeProvider.when('/Inventario/alta', {
    templateUrl: "inventario.html",
    controller: "InventarioController",
     resolve: {
      categoria:['remoteResource','$route',function(remoteResource) {
        return remoteResource.categoria();
      }],
      unidad:['remoteResource','$route',function(remoteResource) {
        return remoteResource.unidad();
      }],
      condicion:['remoteResource','$route',function(remoteResource) {
        return remoteResource.condicion();
      }]
    }
  });     
           $routeProvider.when('/Inventario/colsulta', {
    templateUrl: "consul_inventario.html",
    controller: "InventarioconsultaController",
     resolve: {
      inv_consultas:['remoteResource',function(remoteResource) {
        return remoteResource.listado_inv();
      }]
    }
  });  
       $routeProvider.when('/Inventario/requisicion/:folio', {
    templateUrl: "atender_requisicion.html",
    controller: "RequisicionesController",
     resolve: {
      requisiciones:['remoteResource','$route',function(remoteResource,$route) {
        return remoteResource.lista_requisiciones($route.current.params.folio);
      }]
    }
  });
        $routeProvider.when('/Discrepancias/requisicion/:folio', {
    templateUrl: "alta_requisiciones.html",
    controller: "Requisiciones_altaController",
     resolve: {
      foliarrastrad:['remoteResource','$route',function(remoteResource,$route) {
        return ($route.current.params.folio);
      }],
       inv_consultas:['remoteResource',function(remoteResource) {
        return remoteResource.listado_inv();
      }]
    }
  });
        
         $routeProvider.when('/Aeronaves/alta', {
    templateUrl: "alta_Aeronaves.html",
    controller: "aeronaveController"
  });  
         $routeProvider.when('/Clientes/alta', {
    templateUrl: "alta_Clientes.html",
    controller: "empresaController"
  });  
   
   $routeProvider.when('/Discrepancia/editar/:folio', {
    templateUrl: "editar_discrepancia.html",
    controller: "EditarDiscrepanciaController",
     resolve: {
      discrepancia:['remoteResource','$route',function(remoteResource,$route) {
        return remoteResource.discrepancia($route.current.params.folio);
      }],
      eventos:['remoteResource','$route',function(remoteResource,$route) {
        return remoteResource.eventos($route.current.params.folio);
      }],
      componentes:['remoteResource','$route',function(remoteResource,$route) {
        return remoteResource.componentes($route.current.params.folio);
      }],
      listado_inv:['remoteResource',function(remoteResource) {
        return remoteResource.componentes_exis();
      }],
      componentes_0:['remoteResource',function(remoteResource) {
        return remoteResource.componentes_0();
      }],
      categoria:['remoteResource','$route',function(remoteResource) {
        return remoteResource.categoria();
      }]
    }
  });     

     $routeProvider.when('/Aeronaves/consulta', {
    templateUrl: "consulta_Aeronave.html",
    controller: "aeronaveMuestraController",
     resolve: {
      aeronaves_consultas:['remoteResource',function(remoteResource) {
        return remoteResource.listado_aeronaves();
      }]
    }
  }); 
$routeProvider.when('/Clientes/colsulta', {
    templateUrl: "consulta_Clientes.html",
    controller: "empresaMuestraController",
     resolve: {
      empresas_consultas:['remoteResource',function(remoteResource) {
        return remoteResource.listado_empresas();
      }]
    }
  }); 
$routeProvider.when('/Inventario/categoria', {
    templateUrl: "alta_categorias.html",
    controller: "categoriaController"
  }); 
$routeProvider.when('/Inventario/categoria/colsulta', {
    templateUrl: "consulta_Categoria.html",
    controller: "categoriaMuestraController",
     resolve: {
      categorias_consultas:['remoteResource',function(remoteResource) {
        return remoteResource.listado_categorias();
      }]
    }
  }); 
$routeProvider.when('/Inventario/unidad', {
    templateUrl: "alta_Unidades.html",
    controller: "unidadController"
  }); 
$routeProvider.when('/Inventario/unidad/colsulta', {
    templateUrl: "consulta_Unidad.html",
    controller: "unidadMuestraController",
     resolve: {
      unidades_consultas:['remoteResource',function(remoteResource) {
        return remoteResource.listado_unidades();
      }]
    }
  }); 
$routeProvider.when('/Inventario/condicion', {
    templateUrl: "alta_Condiciones.html",
    controller: "condicionController"
  }); 
$routeProvider.when('/Inventario/condicion/colsulta', {
    templateUrl: "consulta_Condicion.html",
    controller: "condicionMuestraController",
     resolve: {
      condiciones_consultas:['remoteResource',function(remoteResource) {
        return remoteResource.listado_condiciones();
      }]
    }
  }); 
$routeProvider.when('/Consulta/PDF/:nombrepdf', {
    templateUrl: "pdf.html",
    controller: "ordenPDFController",
     resolve: {
      nombrepdf:['remoteResource','$route',function(remoteResource,$route) {
        return ($route.current.params.nombrepdf);
      }]
    }
  }); 
$routeProvider.when('/Vales/consulta', {
    templateUrl: "vales_Salida.html",
    controller: "MuestraValesController",
     resolve: {
      discrepancia_vale:['remoteResource',function(remoteResource) {
        return remoteResource.discrepancia_vale();
      }]
    }
  }); 
$routeProvider.when('/Notificaciones/Ver', {
    templateUrl: "Notificaciones.html",
    controller: "NotificacionesController",
    resolve: {
      aalertas:['remoteResource',function(remoteResource) {
        return remoteResource.alertas();
      }]
    }
  });
$routeProvider.when('/Inicio/paginaPrincipal', {
    templateUrl: "Bienvenida.html",
    controller: "MainController"
  });
$routeProvider.when('/Admin/Horas_Hombre', {
    templateUrl: "asigna_Horas.html",
    controller: "HorasHombreController",
    resolve: {
      usuarios_lista:['remoteResource',function(remoteResource) {
        return remoteResource.usuarios_lista();
      }],
       discrepancias:['remoteResource',function(remoteResource) {
        return remoteResource.disHoras_lista();
      }]
    }
  });
$routeProvider.when('/Usuario/Horas_Hombre', {
    templateUrl: "horas_hombre.html",
    controller: "UsuariosH_Hombre_Controller",
    resolve: {
      dis_asignadas:['remoteResource',function(remoteResource) {
        return remoteResource.dis_asignadas_lista();
      }]
    }
  });
$routeProvider.when('/Admin/alta_empleados', {
    templateUrl: "alta_Empleados.html",
    controller: "Empleados_Controller",
    resolve: {
      puestos:['remoteResource',function(remoteResource) {
        return remoteResource.puestos_lista();
      }]
    }
  });
$routeProvider.when('/Admin/Alta_Puestos', {
    templateUrl: "alta_Puestos.html",
    controller: "AdminController"
  });
$routeProvider.when('/Admin/Empleados/Consulta', {
    templateUrl: "consulta_Empleados.html",
    controller: "EmpleadoMuestraController",
    resolve: {
      empleados_lista:['remoteResource',function(remoteResource) {
        return remoteResource.empleados_lista();
      }],
      puestos:['remoteResource',function(remoteResource) {
        return remoteResource.puestos_lista();
      }]

    }
  });
$routeProvider.when('/Admin/Puestos/Consulta', {
    templateUrl: "consulta_Puestos.html",
    controller: "PuestoMuestraController",
    resolve: {
      puestos_lista:['remoteResource',function(remoteResource) {
        return remoteResource.puestos_lista();
      }]
    }
  });
$routeProvider.when('/Admin/Alta_Usuario', {
    templateUrl: "alta_Usuario.html",
    controller: "UsuarioController",
    resolve: {
      perfil_lista:['remoteResource',function(remoteResource) {
        return remoteResource.perfil_lista();
      }],
      puestos:['remoteResource',function(remoteResource) {
        return remoteResource.puestos_lista();
      }]
    }
  });
$routeProvider.when('/Admin/Consulta_Usuario', {
    templateUrl: "consul_Usuario.html",
    controller: "UsuarioMuestraController",
    resolve: {
      usuarios_lista:['remoteResource',function(remoteResource) {
        return remoteResource.usuarios_lista();
      }],
       perfil_lista:['remoteResource',function(remoteResource) {
        return remoteResource.perfil_lista();
      }]

    }
  });
$routeProvider.when('/Admin/Alta_Perfil', {
    templateUrl: "alta_Perfil.html",
    controller: "PerfilController"
  });
  $routeProvider.otherwise({
        redirectTo: '/'
  });   
 
}]);
app.filter("filteri18n",["$filter",function($filter) {
  var filterFn=$filter("filter");
   
  /** Transforma el texto quitando todos los acentos diéresis, etc. **/
  function normalize(texto) {
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
  function filteri18n(array,expression) {
    //Lo único que hace es llamar al filter original pero pasado
    //la nueva función de comparator
    return filterFn(array,expression,comparator)
  }
   
  return filteri18n;
   
}]);
//directiva de angular para mostrar el calendario usando el plugin de Jquery
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

//servicio inicia sesion ususario
app.service('IniSessServicio', [ '$http', '$q','$rootScope', function($http, $q,$rootScope) {
  this.inicia_session = function(objeto) {
    var d = $q.defer();
    $http.post("/user",objeto).then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
      if(response.status==403){
      alert("Usuario o Contraseña incorrectos");
      location.href="/";
      $rootScope.authenticated = false;
      console.log($rootScope);
                              }
      else {
        $rootScope.authenticated = true;
        $rootScope.variable = true;
         console.log($rootScope);
      }
    });
    return d.promise;
  }

  this.isAuthenticated = function() {
        var d = $q.defer();
        $http.get("/currentSession").success(function(data) {
          $rootScope.authenticated = true;
          d.resolve(data);
        }).error(function(data) {
          location.href="/";
        });
        return d.promise;
      }
} ]);
//servicio cerrar sesion ususario
app.service('CerrarSessServicio', [ '$http', '$q','$rootScope', function($http, $q,$rootScope) {
  this.cerrar_session = function() {
    var d = $q.defer();
    $http.get("usuario/cerrarSesion").then(function(response) {
      console.log(response);
      d.resolve(response.data);
    }, function(response) {
   
    });
    return d.promise;
  }
} ]);
app.service('sessionService', [
    '$rootScope',
    '$http',
    '$location',
    '$q',
    function($rootScope, $http, $location, $q) {
      this.authenticate = function(credentials, callback) {

        var headers = credentials ? {
          authorization : "Basic"
              + btoa(credentials.username + ":"
                  + credentials.password)
        } : {};
        $http.get('user', {
          headers : headers
        }).success(function(data) {
          if (data.usuario) {
            $rootScope.authenticated = true;
            $rootScope.variable = true;
            $rootScope.cargarEmpresasHeader();
            $location.path("/empresas/list");
          } else {
            $rootScope.authenticated = false;
          }
        }).error(function(data) {
          alert("Usuario o Contraseña incorrectos");
          $location.path("/login");
        });
      }
      
      this.isAuthenticated = function() {
        var d = $q.defer();
        $http.get("currentSession").success(function(data) {
          $rootScope.authenticated = true;
          d.resolve(data);
        }).error(function(data) {
          $location.path("/login");
        });
        return d.promise;
      }
    } ]);


app.controller("MainController", ['$scope','remoteResource','IniSessServicio','CerrarSessServicio','$rootScope','$cookieStore','$cookies',function($scope,remoteResource,IniSessServicio,CerrarSessServicio,$rootScope,$cookieStore,$cookies) {
  remoteResource.alertas().then(function(data){
    $scope.alertas = data;
  })
  console.log($scope.alertas);

   $scope.forminicia = {
    usuario:"",
    password: ""
  }

    $scope.inisession=function() {
    console.log($scope.forminicia)
      IniSessServicio.inicia_session($scope.forminicia).then(
        function(data) {
          console.log(data);
          $cookies.cosa = data;
           //$rootScope.authenticated = data;
            console.log("session almacenada");
          console.log($cookies);
          alert("Inicio session");
          location.href="#/Inicio/paginaPrincipal";
          location.reload();
          })     
      }

      $scope.cerrar_session=function() {
      CerrarSessServicio.cerrar_session().then(
        
        function(data) {
          console.log(data);
          alert("La session se cerrara");
          location.href="/";
          })     
      }

}]);

app.service('sessionService', [
    '$rootScope',
    '$http',
    '$location',
    '$q',
    function($rootScope, $http, $location, $q) {
      this.authenticate = function(credentials, callback) {

        var headers = credentials ? {
          authorization : "Basic"
              + btoa(credentials.username + ":"
                  + credentials.password)
        } : {};
        $http.get('/user', {
          headers : headers
        }).success(function(data) {
          if (data.usuario) {
            $rootScope.authenticated = true;
            $rootScope.variable = true;
            $rootScope.cargarEmpresasHeader();
            $location.path("/empresas/list");
          } else {
            $rootScope.authenticated = false;
          }
        }).error(function(data) {
          alert("Usuario o Contraseña incorrectos");
          $location.path("/login");
        });
      }
      
      this.isAuthenticated = function() {
        var d = $q.defer();
        $http.get("currentSession").success(function(data) {
          $rootScope.authenticated = true;
          d.resolve(data);
        }).error(function(data) {
          $location.path("/login");
        });
        return d.promise;
      }
    } ]);

app.controller('navigation', [ 'sessionService', '$rootScope', '$scope',
    '$http', '$location',

    function(sessionService, $rootScope, $scope, $http, $location) {

      $scope.credentials = {};
      $scope.login = function() {
        console.log($scope.credentials);
        sessionService.authenticate($scope.credentials, function() {
          if ($rootScope.authenticated) {
            $scope.error = false;
            $location.path("/");
          } else {
            $location.path("/login");
            $scope.error = true;
          }
        });
      };
    } ]);