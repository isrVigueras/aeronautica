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

import com.tikal.aeronautikal.dao.EmpleadoDao;
import com.tikal.aeronautikal.dao.PerfilDAO;
import com.tikal.aeronautikal.dao.SessionDao;
import com.tikal.aeronautikal.dao.UsuarioDao;
import com.tikal.aeronautikal.entity.EmpleadoEntity;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

@Controller
@RequestMapping(value="/empleado")
public class EmpleadoController {
	
	 @Autowired
	    @Qualifier("empleadoDao")
	    EmpleadoDao empleadoDao;

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
		   response.getWriter().println("Prueba del mètodo PROBAR empleado");

	    }
	 
	 @RequestMapping(value = {"/add"}, method = RequestMethod.GET) 
	   public void addComponenteG(@ModelAttribute("e") EmpleadoEntity e) throws IOException{
	    	 // System.out.println("si entra al add por GET en empleado"+json);
	        try {
	        	//AsignadorDeCharset.asignar(request, response);
	        	//EmpleadoEntity e =(EmpleadoEntity) JsonConvertidor.fromJson(json, EmpleadoEntity.class);        	
	        	e.setNombre("Erik");
	        	e.setApellidoPaterno("Vazquez");
	        	e.setApellidoMaterno("Alvarez");
	        	e.setIdPuesto(Long.parseLong("7737373737"));
	        	empleadoDao.save(e);	           
	        	System.out.println("nombre completo"+e.getNombreCompleto());
	        } catch (RuntimeException ignored) {
	        	ignored.printStackTrace();
	        }
	       
	    }
	 
	  @RequestMapping(value = {"/add/{userName}"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addEmpleado(HttpServletResponse response, HttpServletRequest request,
			   @RequestBody String json,  @PathVariable String userName) throws IOException{
		  if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 39, sessionDao,userName)){   
			  try {
			  		System.out.println("si entra al add por POST en empleado"+json);
		        
		        	AsignadorDeCharset.asignar(request, response);
		        	EmpleadoEntity e =(EmpleadoEntity) JsonConvertidor.fromJson(json, EmpleadoEntity.class);        	
		        	
		        	empleadoDao.save(e);	            
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
			List<EmpleadoEntity> lista = empleadoDao.getAll();
			if (lista == null) {
				lista = new ArrayList<EmpleadoEntity>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	   
	   @RequestMapping(value = {"/delete/{id}/{userName}" }, method = RequestMethod.POST)
	   public void deleteAeronave(HttpServletResponse response, HttpServletRequest request, @PathVariable Long id, @PathVariable String userName) 
			   throws IOException {
		   ////////////ojo cuando borra aeronave, checr muy bien lo de Static en el dao y el @override de daoimpl
		   System.out.println("si esta en delete empleado"+id);
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 41, sessionDao,userName)){ 
			   empleadoDao.delete(empleadoDao.consult(id));
			   System.out.println("empleado eliminado....");
			   response.getWriter().println("ok");
		   }else{
				response.sendError(403);
			}
	   }
	   
	   @RequestMapping(value = {"/update/{userName}" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json, @PathVariable String userName)
				throws IOException {
			System.out.println("obj de edgar:"+json);
			if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 40, sessionDao,userName)){  
				AsignadorDeCharset.asignar(request, response);
				EmpleadoEntity e = (EmpleadoEntity) JsonConvertidor.fromJson(json, EmpleadoEntity.class);
				empleadoDao.update(e);
				response.getWriter().println(JsonConvertidor.toJson(e));
			 }else{
					response.sendError(403);
				}
		}
	   
	   //////////////////////////////////////////////////////////////////////////////////////////*******************
    

}
