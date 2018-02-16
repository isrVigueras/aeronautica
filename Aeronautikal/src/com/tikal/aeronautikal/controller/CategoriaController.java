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

import com.tikal.aeronautikal.dao.CategoriaDao;
import com.tikal.aeronautikal.dao.PerfilDAO;
import com.tikal.aeronautikal.dao.SessionDao;
import com.tikal.aeronautikal.dao.UsuarioDao;
import com.tikal.aeronautikal.entity.Categoria;
import com.tikal.aeronautikal.service.CategoriaService;

import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

@Controller
 @RequestMapping(value="/categoria")
public class CategoriaController {
	

	 @Autowired
	    private CategoriaService categoriaService;
	 
	 @Autowired
	 @Qualifier("categoriaDao")
	 CategoriaDao categoriaDao;
	 
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
		   response.getWriter().println("Prueba del mètodo PROBAR en Categoria");

	    }
	 
	 ////////////////////////////////////////////////*****************************************************************
	 
	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addCategoriaGet(@ModelAttribute("entry") Categoria entry) {
		   System.out.println("si entra a Categoria controller");   
		   	try {
		   		
		   		entry.setClave("1");
		   		entry.setDescripcion("prueba de  catalogo de Categoria");
		   		
		  
	            System.out.println("si asign/ valor"+entry);
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("Categoria planchado guardado");	    
	        categoriaService.save(entry);   //implementa el dao  
	        return "categoria";
		}
	
	 /////////////////////////////////////////////////////********************************************************

	 
	 @RequestMapping(value = {"/add/{userName}"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addCategoria(HttpServletResponse response, HttpServletRequest request, 
			   @RequestBody String json,  @PathVariable String userName) throws IOException{
	       System.out.println("si entra al add Categoria por POST"+json);
	       if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 24, sessionDao,userName)){  
		        try {
		        	AsignadorDeCharset.asignar(request, response);
		        	 System.out.println("request......."+request);
		        	 System.out.println("request......."+response);
		        	 Categoria c =(Categoria) JsonConvertidor.fromJson(json,Categoria.class);
		        	// System.out.println("el nuevo objeto: "+orden );
		        	//pegar el valor de empresa, aeronave y contacato
		        	//cmp.setD_pendientes(50);//aqui va funcion para calcular cuantas piezas pendientes hay de cada componente
		        	//orden.setFolio(1111);
		        	categoriaDao.save(c);	            
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
		public void findAllCategorias(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<Categoria> lista = categoriaDao.getAll();
			if (lista == null) {
				lista = new ArrayList<Categoria>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	     
	   
	   
	   @RequestMapping(value = {"/delete/{id}/{userName}" }, method = RequestMethod.POST)
	   public void deleteCategoria(HttpServletResponse response, HttpServletRequest request, 
			   @PathVariable Long id, @PathVariable String userName)   throws IOException {
		   System.out.println("si esta en delete"+id);
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 26, sessionDao,userName)){  
			   categoriaDao.delete(categoriaDao.consult(id));
			   System.out.println("Categoria eliminado....");
			   response.getWriter().println("ok");
		   }else{
				response.sendError(403);
			}
	   }
	   
		   
	   
	   
	   @RequestMapping(value = {"/update/{userName}" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request,
				@RequestBody String json,  @PathVariable String userName)throws IOException {
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 25, sessionDao,userName)){  
				AsignadorDeCharset.asignar(request, response);
				Categoria c = (Categoria) JsonConvertidor.fromJson(json, Categoria.class);
				categoriaDao.update(c);
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
