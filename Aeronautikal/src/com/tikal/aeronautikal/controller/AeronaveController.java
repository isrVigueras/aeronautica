package com.tikal.aeronautikal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.dao.PerfilDAO;
import com.tikal.aeronautikal.dao.SessionDao;
import com.tikal.aeronautikal.dao.UsuarioDao;
//import com.tikal.aeronautikal.dao.ComponenteDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.Contador;
import com.tikal.aeronautikal.entity.EventoEntity;
//import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.exception.ObjectNotFoundException;
import com.tikal.aeronautikal.model.Aeronave;
import com.tikal.aeronautikal.service.AeronaveService;
//import com.tikal.aeronautikal.service.ComponenteService;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/aeronave")


public class AeronaveController  {
	
    @Autowired
    private AeronaveService aeronaveService;
   
    @Autowired
    @Qualifier("aeronaveDao")
    AeronaveDao aeronaveDao;
    
    @Autowired
	 @Qualifier("sessionDao")
	 SessionDao sessionDao;
	 
	@Autowired
	@Qualifier ("usuarioDao")
	UsuarioDao usuarioDao;

		
	@Autowired
	PerfilDAO perfilDAO; 

 

   
   @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
   
   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
	   response.getWriter().println("Prueba del mètodo PROBAR");

    }
    
//    @RequestMapping(value="/add_", method = RequestMethod.GET)
//    public String addAeroGet(@ModelAttribute("entry") AeronaveEntity entry) {
//		   System.out.println("si entra a Aeronave controller");   
//		   	try {
//		   		entry.setNumeroAeronave("1");
//		   		entry.setMatricula("AAW-21-37");
//		   		entry.setNumeroSerie("23432587Z");
//				entry.setModelo("A-4000");
//				entry.setTiempovuelo(4590);
//				entry.setAterrizaje("23 en toluca ...");
//				entry.setNacionalidad("MEXICANA");
//				//Contador.reinicia();
//				
//	            System.out.println("si asign/ valor"+entry);
//	        } catch (RuntimeException ignored) {
//	            // getUniqueEntity should throw exception
//	        }
//		   System.out.println("ya");	    
//	        aeronaveService.save(entry);   //implementa el dao 
//	        System.out.println("ya guardo la entity de aeronave");
//	    	Contador.incremeta();
//	        return "Orden_de_trabajo"; ///poner el html de aeronave alta
//		}
//    @RequestMapping(value="/add__", method = RequestMethod.GET)
//    public String addAeroGet_(@ModelAttribute("entry") AeronaveEntity entry) {
//		   System.out.println("si entra a Aeronave controller");   
//		   	try {
//		   		//entry.setId("121213");
//		   		entry.setNumeroAeronave("2");
//		   		entry.setMatricula("XSX-98-789");
//		   		entry.setNumeroSerie("C-2389AX");
//				entry.setModelo("F-2550");
//				entry.setTiempovuelo(1200);
//				entry.setAterrizaje("CIUDAD DE MEXICO ...");
//				entry.setNacionalidad("NORTEAMERICANA");
//				//Contador.reinicia();
//				
//	            System.out.println("si asign/ valor"+entry);
//	        } catch (RuntimeException ignored) {
//	            // getUniqueEntity should throw exception
//	        }
//		   System.out.println("ya");	    
//	        aeronaveService.save(entry);   //implementa el dao 
//	        System.out.println("ya guardo la entity de aeronave");
//	    	Contador.incremeta();
//	        return "Orden_de_trabajo"; ///poner el html de aeronave alta
//		}
    
    @RequestMapping(value = {"/add/{userName}"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addAeronave(HttpServletResponse response, HttpServletRequest request,
			   @RequestBody String json,  @PathVariable String userName) throws IOException{
	    	System.out.println("si entra al add por POST"+json);
	    	if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 14, sessionDao,userName)){   
		        try {
		        	AsignadorDeCharset.asignar(request, response);
		        	AeronaveEntity a =(AeronaveEntity) JsonConvertidor.fromJson(json, AeronaveEntity.class);        	
		        	
		        	if (a.getNacionalidad().equals("NORTEAMERICANO")){
						a.setNumeroAeronave(String.valueOf(Contador.getFolio())+"N");
						Contador.incremeta();
					}
		        	if (a.getNacionalidad().equals("NACIONAL")){
						a.setNumeroAeronave(String.valueOf(Contador.getFolio()));
						Contador.incremeta();
					}
		        	//a.setNumeroAeronave(Contador.getFolio());
		        	aeronaveDao.save(a);	            
		        } catch (RuntimeException ignored) {
		        	ignored.printStackTrace();
		        }
	    	}else{
				response.sendError(403);
			}
	       
	    }
    
    /////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllComp(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<AeronaveEntity> lista = aeronaveDao.getAll();
			if (lista == null) {
				lista = new ArrayList<AeronaveEntity>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	   
	   @RequestMapping(value = {"/delete/{id}/{userName}" }, method = RequestMethod.POST)
	   public void deleteAeronave(HttpServletResponse response, HttpServletRequest request,
			   @PathVariable Long id,  @PathVariable String userName) throws IOException {
		   ////////////ojo cuando borra aeronave, checr muy bien lo de Static en el dao y el @override de daoimpl
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 16, sessionDao,userName)){  
				   System.out.println("si esta en delete"+id);
				   aeronaveDao.delete(aeronaveDao.consult(id));
				   System.out.println("aeronave eliminada....");
				   response.getWriter().println("ok");
		   }else{
				response.sendError(403);
			}
	   }
	   
	   @RequestMapping(value = {"/update/{userName}" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request,
				@RequestBody String json , @PathVariable String userName)throws IOException {
			System.out.println("obj de edgar:"+json);
			if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 15, sessionDao,userName)){  
			
				AsignadorDeCharset.asignar(request, response);
				AeronaveEntity a = (AeronaveEntity) JsonConvertidor.fromJson(json, AeronaveEntity.class);
	
				aeronaveDao.update(a);
				response.getWriter().println(JsonConvertidor.toJson(a));
			}else{
				response.sendError(403);
			}
			
		}
	   
	   //////////////////////////////////////////////////////////////////////////////////////////*******************
     
   
}