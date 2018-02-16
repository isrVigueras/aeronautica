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

import com.tikal.aeronautikal.dao.CondicionDao;
import com.tikal.aeronautikal.dao.PerfilDAO;
import com.tikal.aeronautikal.dao.SessionDao;
import com.tikal.aeronautikal.dao.UsuarioDao;
import com.tikal.aeronautikal.entity.Condicion;

import com.tikal.aeronautikal.service.CondicionService;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;


@Controller
 @RequestMapping(value="/condicion")

public class CondicionController {
	


	 @Autowired
	    private CondicionService condicionService;
	 
	 @Autowired
	 @Qualifier("condicionDao")
	 CondicionDao condicionDao;
	 
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
		   response.getWriter().println("Prueba del mètodo PROBAR en Condicion");

	    }
	 
	 ////////////////////////////////////////////////*****************************************************************
	 
	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addCondicionGet(@ModelAttribute("entry") Condicion entry) {
		   System.out.println("si entra a Condicion controller");   
		   	try {
		   		
		   		entry.setClave("1");
		   		entry.setDescripcion("prueba de  catalogo de Condicion");
		   		
		  
	            System.out.println("si asign/ valor"+entry);
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("Condicion planchado guardado");	    
	        condicionService.save(entry);   //implementa el dao  
	        return "condicion";
		}
	
	 /////////////////////////////////////////////////////********************************************************

	 
	 @RequestMapping(value = {"/add/{userName}"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addCondicion(HttpServletResponse response, HttpServletRequest request,
			   @RequestBody String json, @PathVariable String userName) throws IOException{
	    	System.out.println("si entra al add Condicion por POST"+json);
	    	if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 30, sessionDao,userName)){  
		        try {
		        	AsignadorDeCharset.asignar(request, response);
		        	 System.out.println("request......."+request);
		        	 System.out.println("request......."+response);
		        	 Condicion c =(Condicion) JsonConvertidor.fromJson(json,Condicion.class);
		        	// System.out.println("el nuevo objeto: "+orden );
		        	//pegar el valor de empresa, aeronave y contacato
		        	//cmp.setD_pendientes(50);//aqui va funcion para calcular cuantas piezas pendientes hay de cada componente
		        	//orden.setFolio(1111);
		        	condicionDao.save(c);	            
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
		public void findAllCondiciones(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Condicion> lista = condicionDao.getAll();
			if (lista == null) {
				lista = new ArrayList<Condicion>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	     
	   
	   
	   @RequestMapping(value = {"/delete/{id}/{userName}" }, method = RequestMethod.POST)
	   public void deleteCondicion(HttpServletResponse response, HttpServletRequest request,
			   @PathVariable Long id,  @PathVariable String userName)   throws IOException {
		   System.out.println("si esta en delete"+id);
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 32, sessionDao,userName)){  
			   condicionDao.delete(condicionDao.consult(id));
			   System.out.println("Condicion eliminado....");
			   response.getWriter().println("ok");
		   }else{
				response.sendError(403);
			}
	   }
	   
		   
	   
	   
	   @RequestMapping(value = {"/update/{userName}" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request, 
				@RequestBody String json, @PathVariable String userName)	throws IOException {
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 31, sessionDao,userName)){  
				AsignadorDeCharset.asignar(request, response);
				Condicion c = (Condicion) JsonConvertidor.fromJson(json, Condicion.class);
				condicionDao.update(c);
				response.getWriter().println(JsonConvertidor.toJson(c));
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

