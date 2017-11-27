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

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.OnLoad;
import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.dao.EmpresaDao;
import com.tikal.aeronautikal.dao.OrdenDao;
import com.tikal.aeronautikal.entity.EmpresaEntity;
import com.tikal.aeronautikal.entity.OrdenEntity;
import com.tikal.aeronautikal.model.Aeronave;
import com.tikal.aeronautikal.service.OrdenService;
import com.tikal.aeronautikal.util.JsonConvertidor;
import com.tikal.aeronautikal.util.AsignadorDeCharset;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/orden")

public class OrdenController {
	
	private static final int RECORDS_PER_PAGE = 5;

	@Autowired
	@Qualifier("empresaDao")
	EmpresaDao empresaDao;
	
	@Autowired
	@Qualifier("ordenDao")
	OrdenDao ordenDao;

	   @Autowired
	   private OrdenService ordenService;
	
	   
//	   @RequestMapping(value = "/add", method = RequestMethod.GET)
//	    public String addOrdenGet(@ModelAttribute("entry") OrdenEntity entry) {
//		   System.out.println("si entra a Orden controller");   
//		   	try {
//		   		entry.setFolio(1234);
//	           // entry.setCondiciones("todas las condiciones que deseen");
//				entry.setFechaApertura("01-12-2017");
//				//entry.setDate(Calendar.getInstance().getTime());
//	            System.out.println("si asign/ valor"+entry);
//	        } catch (RuntimeException ignored) {
//	            // getUniqueEntity should throw exception
//	        }
//		   System.out.println("yaaaaa");	    
//	        ordenService.save(entry);   //implementa el dao  
//	        return "Orden_de_trabajo";
//		   }
	
	   @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addOrden(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) throws IOException{
	    	  System.out.println("si entra al add por POST"+json);
	        try {
	        	AsignadorDeCharset.asignar(request, response);
	        	OrdenEntity orden =(OrdenEntity) JsonConvertidor.fromJson(json, EmpresaEntity.class);
	        	//pegar el valor de empresa, aeronave y contacato
	        	orden.setFolio(1111);
	        	ordenDao.save(orden);	            
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
	       
	    }


	   
	   
	   @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Orden de trabajo");

	    }
	   
	   
	   @RequestMapping(value = { "/getEmpresas" }, method = RequestMethod.GET, produces = "application/json")
		public void getAllEmpresas(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
						
			List<EmpresaEntity> lista = empresaDao.getAllEmpresas();
			if (lista == null) {
				lista = new ArrayList<EmpresaEntity>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));
		}
	   
	   /////////////////////////////////////////////////////////////////////////////////////////**********************
	   
	   //////////////////////////////////////////////////////////////////////////////////////////*******************
}
	    
