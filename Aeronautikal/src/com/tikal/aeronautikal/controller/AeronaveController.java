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
import com.tikal.aeronautikal.dao.AeronaveDao;
//import com.tikal.aeronautikal.dao.ComponenteDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
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

 
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String addAeronaveGet(@ModelAttribute("entry") AeronaveEntity entry) {
//	   System.out.println("si entra aqui 1");
//	   try {
//        
//            entry.setMatricula("MAT-44477");
//            entry.setModelo("Super");
//            entry.setNumeroSerie("22221");
//            entry.setTiempovuelo(1620);
//            System.out.println("si asigna valores de aeronave : "+entry);
//          
//        } catch (RuntimeException ignored) {
//            // getUniqueEntity should throw exception
//        }
//	   System.out.println("yaaaaa estoy en add con get");	    
//	   aeronaveService.save(entry);   //implementa el dao
//        return "addAeronave";
//	   
//    }
//    //// EJEMPLO DE FUNCION CONSUMIENDO EL WEB SERVICE
 
   
   @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
   
   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
	   response.getWriter().println("Prueba del mètodo PROBAR");

    }
    
    @RequestMapping(value="/add_", method = RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request, ModelMap model) {

	      
                Entity nave = new Entity("Aeronave", "123");     

                DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
                datastore.put(nave);

                return new ModelAndView("redirect:list");

	}
    
    
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addComponente(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) throws IOException{
	    	  System.out.println("si entra al add por POST"+json);
	        try {
	        	AsignadorDeCharset.asignar(request, response);
	        	AeronaveEntity a =(AeronaveEntity) JsonConvertidor.fromJson(json, AeronaveEntity.class);
	        	aeronaveDao.save(a);	            
	        } catch (RuntimeException ignored) {
	        	ignored.printStackTrace();
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
	   
	   @RequestMapping(value = {"/delete/{numeroSerie}" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	   public void deleteAeronave(HttpServletResponse response, HttpServletRequest request, @RequestBody String json,
		@PathVariable String numeroSerie) throws IOException {
		   ////////////ojo cuando borra aeronave, checr muy bien lo de Static en el dao y el @override de daoimpl
		   aeronaveDao.delete(AeronaveDao.consult(numeroSerie));
	   }
	   
	   //////////////////////////////////////////////////////////////////////////////////////////*******************
     
   
}