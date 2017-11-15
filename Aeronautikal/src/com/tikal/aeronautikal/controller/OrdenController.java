package com.tikal.aeronautikal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.Key;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.model.Aeronave;
import com.tikal.aeronautikal.service.SelectionService;
import com.tikal.aeronautikal.util.JsonConvertidor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/orden")



public class OrdenController {

	 //// EJEMPLO DE FUNCION CONSUMIENDO EL WEB SERVICE
	   @RequestMapping(value={"/guarda"},method = RequestMethod.GET, consumes="application/json")
	  
	   public void guardar(HttpServletResponse response, HttpServletRequest request,@RequestBody String json) throws IOException {
		   Aeronave a= (Aeronave) JsonConvertidor.fromJson(json, Aeronave.class);
		   String txt = a.getMatricula();
		   response.getWriter().printf("Prueba del mètodo guardar",txt);

	    }
	   ////////////////////////////////////////////////////
	   
	   
	   @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR");

	    }
	    

}
