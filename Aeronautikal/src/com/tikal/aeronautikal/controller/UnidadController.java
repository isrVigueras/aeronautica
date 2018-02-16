package com.tikal.aeronautikal.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.tikal.aeronautikal.dao.PerfilDAO;
import com.tikal.aeronautikal.dao.SessionDao;
import com.tikal.aeronautikal.dao.UnidadDao;
import com.tikal.aeronautikal.dao.UsuarioDao;
import com.tikal.aeronautikal.entity.Unidad;
import com.tikal.aeronautikal.service.UnidadService;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

@Controller
@RequestMapping(value="/unidad")
public class UnidadController {
	

	 @Autowired
	    private UnidadService unidadService;
	 
	 @Autowired
	 @Qualifier("unidadDao")
	 UnidadDao unidadDao;
	 
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
		   response.getWriter().println("Prueba del mètodo PROBAR en unidad");

	    }
	 
	 ////////////////////////////////////////////////*****************************************************************
	 
	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addUnidadGet(@ModelAttribute("entry") Unidad entry) {
		   System.out.println("si entra a Unidad controller");   
		   	try {
		   		
		   		entry.setClave("1");
		   		entry.setDescripcion("prueba de  catalogo de Unidad");
		   		
		  
	            System.out.println("si asign/ valor"+entry);
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("Unidad planchado guardado");	    
	        unidadService.save(entry);   //implementa el dao  
	        return "unidad";
		}
	
	 /////////////////////////////////////////////////////********************************************************

	 
	 @RequestMapping(value = {"/add/{userName}"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addUnidad(HttpServletResponse response, HttpServletRequest request,
			   @RequestBody String json, @PathVariable String userName) throws IOException{
	       System.out.println("si entra al add Unidad por POST"+json);
	       if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 27, sessionDao,userName)){  
		        try {
		        	AsignadorDeCharset.asignar(request, response);
		        	 System.out.println("request......."+request);
		        	 System.out.println("request......."+response);
		        	 Unidad u =(Unidad) JsonConvertidor.fromJson(json, Unidad.class);
		        	// System.out.println("el nuevo objeto: "+orden );
		        	//pegar el valor de empresa, aeronave y contacato
		        	//cmp.setD_pendientes(50);//aqui va funcion para calcular cuantas piezas pendientes hay de cada componente
		        	//orden.setFolio(1111);
		        	unidadDao.save(u);	            
		        } catch (RuntimeException ignored) {
		        	ignored.printStackTrace();
		            // getUniqueEntity should throw exception
		        }
	       }else{
				response.sendError(403);
			}
	    }
	 
	   /////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllUnidades(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Unidad> lista = unidadDao.getAll();
			if (lista == null) {
				lista = new ArrayList<Unidad>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	     
	   
	   
	   @RequestMapping(value = {"/delete/{id}/{userName}" }, method = RequestMethod.POST)
	   public void deleteUnidad(HttpServletResponse response, HttpServletRequest request,
			   @PathVariable Long id,  @PathVariable String userName)   throws IOException {
		   System.out.println("si esta en delete"+id);
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 29, sessionDao,userName)){ 
			   unidadDao.delete(unidadDao.consult(id));
			   System.out.println("Unidad eliminado....");
			   response.getWriter().println("ok");
		   }else{
				response.sendError(403);
			}
	   }
	   
		   
	   
	   
	   @RequestMapping(value = {"/update/{userName}" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request, 
				@RequestBody String json,  @PathVariable String userName)	throws IOException {
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 28, sessionDao,userName)){  
				AsignadorDeCharset.asignar(request, response);
				Unidad u = (Unidad) JsonConvertidor.fromJson(json, Unidad.class);
				unidadDao.update(u);
				response.getWriter().println(JsonConvertidor.toJson(u));
		   }else{
				response.sendError(403);
			}
		}
	    
	   
	 /*  @RequestMapping(value = { "/getByDiscrepancia/{idDiscrepancia}" }, method = RequestMethod.GET, produces = "application/json")
		public void findByDiscrepancia(HttpServletResponse response, HttpServletRequest request,
				@PathVariable Long idDiscrepancia) throws IOException {
		   System.out.println("ya entro a evento controller....");
			AsignadorDeCharset.asignar(request, response);
			List<EventoEntity> evs= eventoDao.getByDiscrepancia(idDiscrepancia);
			if (evs==null){
				evs= new ArrayList<EventoEntity>();
			}
			
			response.getWriter().println(JsonConvertidor.toJson(evs));
			
		}*/
	 
	 
	 
	 
}
