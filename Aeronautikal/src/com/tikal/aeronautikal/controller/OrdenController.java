package com.tikal.aeronautikal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.objectify.ObjectifyService;
import com.tikal.aeronautikal.entity.OrdenEntity;
import com.tikal.aeronautikal.model.Aeronave;
import com.tikal.aeronautikal.model.Empresa;
import com.tikal.aeronautikal.service.OrdenService;
import com.tikal.aeronautikal.util.JsonConvertidor;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/orden")

public class OrdenController {
	
	private static final int RECORDS_PER_PAGE = 5;

	
	   @Autowired
	   
	    private OrdenService ordenService;   
	   
	   static {
	        ObjectifyService.register(OrdenEntity.class);
	    }
	   
	   
//	   @RequestMapping(value = "/add", method = RequestMethod.GET)
//	    public String addOrdenGet(@ModelAttribute("entry") OrdenEntity entry) {
//	        return "Orden_de_trabajo";
//	    }
	   
	   
	   @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addOrdenGet(@ModelAttribute("entry") OrdenEntity entry) {
		   System.out.println("si entra a Orden controller");
		   
		   
		   try {
	        
	            entry.setFolio(1234);
	            
	            
	            entry.setCondiciones("todas las condiciones que deseen");
				//entry.setFechaApertura("");
	            System.out.println("si asigna valor"+entry);
	          
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("yaaaaa");	    
	        ordenService.save(entry);   //implementa el dao
	        return "Orden_de_trabajo";
		   
	    }
	   
	   @RequestMapping(value = "/add", method = RequestMethod.POST)   //
	    public String addOrdenPost(ModelMap model, @ModelAttribute("entry") OrdenEntity entry) {
	    	  System.out.println("si entra aqui2");
	        try {
	            // server validation. Link should be unique
	            Map<String, Object> conditions = new HashMap<String, Object>();	
	            conditions.put("folio", entry.getFolio());
	        
	            ordenService.getUniqueEntity(OrdenEntity.class, conditions);
	            //model.put("notUniqueFolio", true);
	            return "addOrden";
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
	       
	        //entry.setDate(Calendar.getInstance().getTime());
	        ordenService.save(entry);   //implementa el dao
	        //response.getWriter().printf("Ya se creo una orden de trabajo con un folio generado...");
	        return "redirect:/prueba";
	    }

	
	/* @RequestMapping(value = "/add", method = RequestMethod.POST)   //put folio
	    public String addOrdenPost(ModelMap model, @ModelAttribute("entry") OrdenEntity entry, HttpServletResponse response )throws IOException {
	    	  System.out.println("si entra aqui2");
	        try {
	            // server validation. Link should be unique
	            Map<String, Object> conditions = new HashMap<String, Object>();	
	            conditions.put("folio", entry.getFolio());
	            ordenService.getUniqueEntity(OrdenEntity.class, conditions);
	            model.put("notUniqueMatricula", true);
	            return "addAeronave";
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
	       
	        //entry.setDate(Calendar.getInstance().getTime());
	        ordenService.save(entry);   //implementa el dao
	        response.getWriter().printf("Ya se creo una orden de trabajo con un folio generado...");
	        return "redirect:/aeronave";
	    }
*/
	 //// EJEMPLO DE FUNCION CONSUMIENDO EL WEB SERVICE
	   @RequestMapping(value={"/guarda"},method = RequestMethod.GET, consumes="application/json")
	  
	   public void guardar(HttpServletResponse response, HttpServletRequest request,@RequestBody String json) throws IOException {
		   Aeronave a= (Aeronave) JsonConvertidor.fromJson(json, Aeronave.class);
		   String txt = a.getMatricula();
		   response.getWriter().printf("Prueba del mètodo guardar en Orden de trabajo",txt);

	    }
	   ////////////////////////////////////////////////////
	   
	   
	   @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Orden de trabajo");

	    }
	   
	   
	    

	   
}
