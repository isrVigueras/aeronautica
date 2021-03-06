package com.tikal.aeronautikal.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.aeronautikal.controller.vo.ComDisVo;
import com.tikal.aeronautikal.dao.ComponenteDao;
import com.tikal.aeronautikal.dao.ComponenteDiscrepanciaDao;
import com.tikal.aeronautikal.dao.PerfilDAO;
import com.tikal.aeronautikal.dao.RequisicionDao;
import com.tikal.aeronautikal.dao.SessionDao;
import com.tikal.aeronautikal.dao.UsuarioDao;
import com.tikal.aeronautikal.dao.ValeDao;
import com.tikal.aeronautikal.entity.ComponenteDiscrepancia;
import com.tikal.aeronautikal.entity.RequisicionEntity;
import com.tikal.aeronautikal.entity.ValeEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.service.ComponenteService;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;




@Controller
@RequestMapping(value="/componente")
public class ComponenteController {
	
	 @Autowired
	    private ComponenteService componenteService;
	 
	 
	 @Autowired
	 @Qualifier("sessionDao")
	 SessionDao sessionDao;
	 
	 @Autowired
		@Qualifier ("usuarioDao")
		UsuarioDao usuarioDao;
//		UsuarioDAO usuarioImp;
		
		@Autowired
		PerfilDAO perfilDAO; 
		
	 
	 @Autowired
	 @Qualifier("componenteDao")
	 ComponenteDao componenteDao;
	 
	 @Autowired
	 @Qualifier("requisicionDao")
	 RequisicionDao requisicionDao;
	 
	 @Autowired
	 @Qualifier("valeDao")
	 ValeDao valeDao;
	 
	 @Autowired
	 @Qualifier("componenteDiscrepanciaDao")
	 ComponenteDiscrepanciaDao componenteDiscrepanciaDao;
	 
	 @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Componente");

	    }
	 
	 ////////////////////////////////////////////////*****************************************************************
	 
	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addComponenteGet(@ModelAttribute("entry") ComponenteEntity entry) {
		   System.out.println("si entra a Orden controller");   
		   	try {
		   		
		   		//entry.setId(Long.parseLong("1001"));
		   		entry.setD_componente("GASKET");
		   		entry.setD_descripcion("pieza que sirve para ...");
		   		entry.setD_parte("PARTE_1");
		   		//entry.setD_pendientes(3);
		   		entry.setD_cantidad(100);
		   		entry.setIdCategoria(Long.parseLong("4573968371548160"));
		   		entry.setIdCondicion(Long.parseLong("4714705859903488"));
		   		entry.setIdUnidad(Long.parseLong("4855443348258816"));
		   		//entry.setD_requisicion("num de requisicion");
		   		//entry.setD_vale("numero de vale");
		   		entry.setFechaApertura("01/12/2017");
		  
	            System.out.println("si asign/ valor"+entry);
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("yaaaaa");	    
	        componenteService.save(entry);   //implementa el dao  
	        return "inventario";
		}
	
	 /////////////////////////////////////////////////////********************************************************

	 
	 @RequestMapping(value = {"/add/{userName}"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addComponente(HttpServletResponse response, HttpServletRequest request, @RequestBody String json, @PathVariable String userName) throws IOException{
	    	  System.out.println("si entra al add por POST"+json);
	      if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 51, sessionDao,userName)){

		        try {
		        	AsignadorDeCharset.asignar(request, response);
		        	// System.out.println("request......."+request);
		        	// System.out.println("request......."+response);
		        	ComponenteEntity cmp =(ComponenteEntity) JsonConvertidor.fromJson(json, ComponenteEntity.class);
		        	// System.out.println("el nuevo objeto: "+orden );
		        	//pegar el valor de empresa, aeronave y contacato
		        	//cmp.setD_pendientes(50);//aqui va funcion para calcular cuantas piezas pendientes hay de cada componente
		        	//orden.setFolio(1111);
		        	componenteDao.save(cmp);	            
		        } catch (RuntimeException ignored) {
		        	ignored.printStackTrace();
		            // getUniqueEntity should throw exception
		        }
	      }else{
			response.sendError(403);
		  }
	       
	    }
	 
	   /////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll_antes" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllComp(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<ComponenteEntity> lista = componenteDao.getAll();
			if (lista == null) {
				lista = new ArrayList<ComponenteEntity>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	   
	   @RequestMapping(value = { "/findByDiscrepancia/{idDiscrepancia}" }, method = RequestMethod.GET, produces = "application/json")
		public void findByDis(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idDiscrepancia) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<ComponenteEntity> lista = componenteDao.getByDiscrepancia(idDiscrepancia);
			if (lista == null) {
				lista = new ArrayList<ComponenteEntity>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   /// trae TODOS los componentes
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllComplete(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			//List<ComponenteVo> cvos= new ArrayList<ComponenteVo>();
			List<ComponenteEntity> lista = componenteDao.getAll();			
					for (ComponenteEntity c : lista){
							c.setD_pendientes(requisicionDao.getPendientes(c.getId()));
							componenteDao.update(c);						   
					}					
			response.getWriter().println(JsonConvertidor.toJson(lista));
		}
	   
	   
	 /// trae los componentes con existencias mayor a cero ojooooo
	   @RequestMapping(value = { "/findAllFilter" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllF(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			System.out.println("si entra ");
			//List<ComponenteVo> cvos= new ArrayList<ComponenteVo>();
			List<ComponenteEntity> lista = componenteDao.getAllF();			
					for (ComponenteEntity c : lista){
							c.setD_pendientes(requisicionDao.getPendientes(c.getId()));
							componenteDao.update(c);						   
					}					
			response.getWriter().println(JsonConvertidor.toJson(lista));
		}
	   
	   @RequestMapping(value = { "/findAll0" }, method = RequestMethod.GET, produces = "application/json")
	 		public void findAll0(HttpServletResponse response, HttpServletRequest request) throws IOException {
	 			AsignadorDeCharset.asignar(request, response);
	 			System.out.println("si entra ");
	 			//List<ComponenteVo> cvos= new ArrayList<ComponenteVo>();
	 			List<ComponenteEntity> lista = componenteDao.getAll0();			
	 					for (ComponenteEntity c : lista){
	 							c.setD_pendientes(requisicionDao.getPendientes(c.getId()));
	 							componenteDao.update(c);						   
	 					}					
	 			response.getWriter().println(JsonConvertidor.toJson(lista));
	 		}
	   
	   @RequestMapping(value = { "/getByCategoria/{idCategoria}" }, method = RequestMethod.POST, produces = "application/json")
		public void getByCat(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idCategoria) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			System.out.println("si entraxxxxxxx ");
			//List<ComponenteVo> cvos= new ArrayList<ComponenteVo>();
			List<ComponenteEntity> lista = componenteDao.getByCategoria(idCategoria);			
//					for (ComponenteEntity c : lista){
//							c.setD_pendientes(requisicionDao.getPendientes(c.getId()));
//							componenteDao.update(c);						   
//					}					
			response.getWriter().println(JsonConvertidor.toJson(lista));
		}
	   
	   
	   @RequestMapping(value = {"/delete/{folio}/{userName}" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	   public void deleteOrden(HttpServletResponse response, HttpServletRequest request, @RequestBody String json,
		@PathVariable Long id, @PathVariable String userName) throws IOException {
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 53, sessionDao,userName)){ 
		   		componenteDao.delete(componenteDao.consult(id));
		   }else{
				response.sendError(403);
			}
	   }
	   
	   //////////////////////////////////////////////////////////////////////////////////////////*******************
	   //////// update de existencias segun las requisiciones
	   /////////////   //////////id componente, folio de la req
	   
	 
	 //  @RequestMapping(value = {
//		"/update" }, method = RequestMethod.POST, consumes = "application/json")
//public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
//		throws IOException {
	   
	   /**
		
		 * @param response
		 * @param request
		 * @param json
		 * @throws IOException
		 */
	  
	/*   @RequestMapping(value = {"/upExistencias_"}, method = RequestMethod.POST, consumes = "application/json")
	   public void updateExistencias_(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) 
		throws IOException {
		   System.out.println("si entra a actualizar existencias");
		   AsignadorDeCharset.asignar(request, response);
		   RequisicionEntity req = (RequisicionEntity) JsonConvertidor.fromJson(json, RequisicionEntity.class);
		   System.out.println("el  id del objeto que me manda es: "+req.getIdComponente());
		   
		   ComponenteEntity old = componenteDao.consult(req.getIdComponente());
		
		   Integer existencias = old.getD_cantidad()-req.getCantidad();
		   Integer pendientes = old.getD_pendientes()-req.getCantidad();
		   System.out.println("EXISTENCIAS:"+existencias);
		   System.out.println("PENDIENTES:"+pendientes);
		   old.setD_cantidad(existencias);
		   old.setD_pendientes(pendientes);  
		   componenteDao.update(old);
		  // response.getWriter().println(JsonConvertidor.toJson(old));

	   }*/
	   
	   
	   @RequestMapping(value = {"/upExistencias/{idRequisicion}/{userName}"}, method = RequestMethod.POST,  produces = "application/json" )
	   public void updateExistencias(HttpServletResponse response, HttpServletRequest request, 
			   @PathVariable Long idRequisicion, @PathVariable String userName) throws IOException {
		   System.out.println("si entra a actualizar existencias con este id de requisicion"+ idRequisicion);
		  // AsignadorDeCharset.asignar(request, response);
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 33, sessionDao,userName)){
				   RequisicionEntity req = requisicionDao.consult(idRequisicion);
				   System.out.println("el  id del componente, segun la req es: "+req.getFolio_componente());
				   
				   ComponenteEntity old = componenteDao.consult(req.getFolio_componente());
				
				   Integer existencias = (old.getD_cantidad()==0)? old.getD_cantidad():old.getD_cantidad()+req.getNumero_piezas();
				   Integer pendientes = (old.getD_pendientes()==0)? old.getD_pendientes():old.getD_pendientes()-req.getNumero_piezas();
				   System.out.println("EXISTENCIAS:"+existencias);
				   System.out.println("PENDIENTES:"+pendientes);
				   old.setD_cantidad(existencias);
				   old.setD_pendientes(pendientes);  
				   req.setEstatus("CERRADA");
				   requisicionDao.update(req);
				   componenteDao.update(old);
				   System.out.println("termino de actualizar.......Ahora se Genera el vale..");
				   //generar vale....
				   generaVale((requisicionDao.consult(idRequisicion)).getFolio_discrepancia(), req.getFolio_componente(),req.getNumero_piezas() );
				   
				  //// AsignadorDeCharset.asignar(request, response);
				  // response.getWriter().println(JsonConvertidor.toJson(old)); 
		   }else{
				response.sendError(403);
			}

	   }
	   
	   
	   @RequestMapping(value = {"/update/{userName}" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json, @PathVariable String userName)
				throws IOException {
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 52, sessionDao,userName)){
				AsignadorDeCharset.asignar(request, response);
				ComponenteEntity c = (ComponenteEntity) JsonConvertidor.fromJson(json, ComponenteEntity.class);
				//Empleado e= evo.getEmpleado();
				componenteDao.update(c);
				response.getWriter().println(JsonConvertidor.toJson(c));
		   }else{
				response.sendError(403);
			}
		}
	    
	   public void generaVale(Long idDiscrepancia, Long idComponente,Integer pzas){
			  //consultar componentes de la discrepancia que no tengan vale
			  System.out.println("************esta en generar vales con esta discrepancia:"+idDiscrepancia);	
			  ComponenteDiscrepancia cd= new ComponenteDiscrepancia();
				List<ComponenteDiscrepancia> cds= new ArrayList<ComponenteDiscrepancia>();
				
				cd.setCantidad(pzas);
				cd.setIdComponente(idComponente);
				cd.setIdDiscrepancia(idDiscrepancia);
				cd.setNombreComponente((componenteDao.consult(idComponente)).getD_componente());
				cd.setCantidad(pzas);
				cd.setCantOriginal(pzas);
				cd.setAuto("SI");
			    componenteDiscrepanciaDao.save(cd);
				cds.add(cd);
			//	cvos.add(cd);
				//aqui se guardaran los comps que no tienen vale
				ValeEntity v= new ValeEntity();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String fecha= sdf.format(new Date());
			    v.setFecha(fecha);
			    v.setEstatus("ABIERTO");
			    v.setComponente(componenteDao.consult(idComponente));
			    v.setIdDiscrepancia(idDiscrepancia);
			    v.setItems(cds);
			    
			    valeDao.save(v);
			    System.out.println("VALE EMITIDO x componente:"+v.getId());	
			    cd.setIdVale(v.getId()); //asigna el vale al comdis generado
			    componenteDiscrepanciaDao.save(cd);
			 
		  }

}
 