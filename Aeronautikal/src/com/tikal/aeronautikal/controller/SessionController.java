package com.tikal.aeronautikal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.tikal.aeronautikal.dao.SessionDao;

import com.tikal.aeronautikal.entity.SessionEntity;

import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

@Controller
@RequestMapping(value="/session")
public class SessionController {
	   
	@Autowired
	@Qualifier("sessionDao")
	SessionDao sessionDao;

	    
	    @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	    
	    public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
	 	   response.getWriter().println("Prueba del mètodo Session");

	     }
	    
	    
	    @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
		   public void addAeronave(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) throws IOException{
		    	  System.out.println("si entra al add por POST"+json);
		        try {
		        	AsignadorDeCharset.asignar(request, response);
		        	SessionEntity s =(SessionEntity) JsonConvertidor.fromJson(json, SessionEntity.class);        	
		        	
		        	
		        	//a.setNumeroAeronave(Contador.getFolio());
		        	sessionDao.save(s);	            
		        } catch (RuntimeException ignored) {
		        	ignored.printStackTrace();
		        }
		       
		    }
	    
	    
	    @RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.POST)
		   public void deleteSession(HttpServletResponse response, HttpServletRequest request, @PathVariable Long id) 
				   throws IOException {
			   ////////////ojo cuando borra aeronave, checr muy bien lo de Static en el dao y el @override de daoimpl
			   System.out.println("si esta en delete"+id);
			   sessionDao.delete(sessionDao.consult(id.toString()));
			   System.out.println("session eliminada....");
			   response.getWriter().println("ok");
		   }
		   
		   @RequestMapping(value = {"/update" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
			public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
					throws IOException {
				System.out.println("obj de edgar:"+json);
				AsignadorDeCharset.asignar(request, response);
				SessionEntity s = (SessionEntity) JsonConvertidor.fromJson(json, SessionEntity.class);

				sessionDao.update(s);
				response.getWriter().println(JsonConvertidor.toJson(s));
			}

}
