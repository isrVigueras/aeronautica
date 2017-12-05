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

import com.tikal.aeronautikal.dao.RequisicionDao;
import com.tikal.aeronautikal.entity.RequisicionEntity;
import com.tikal.aeronautikal.service.RequisicionService;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

@Controller
@RequestMapping (value="/requisicion")

public class RequisicionController {
	
	 @Autowired
	    private RequisicionService requisicionService;
	 
	 @Autowired
	 @Qualifier("requisicionDao")
	 RequisicionDao requisicionDao;
	 
	 @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Requisicion");

	    }
	 
	 ////////////////////////////////////////////////*****************************************************************
	 
	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addReqGet(@ModelAttribute("entry") RequisicionEntity entry) {
		   System.out.println("si entra a Requisicion controller");   
		   	try {
		   		
		   		entry.setFolio(Long.parseLong("99999"));
		   		entry.setNumero_parte("Tablero modelo mnku-34");
		   		entry.setCantidad(4);
	            System.out.println("si asign/ valor"+entry);
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("yaaaaa");	    
	        requisicionService.save(entry);   //implementa el dao  
	        return "Requisicion";
		}
	
	 /////////////////////////////////////////////////////********************************************************

	 
	 @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addRequisicion(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) throws IOException{
	    	  System.out.println("si entra al add por POST"+json);
	        try {
	        	AsignadorDeCharset.asignar(request, response);
	        	// System.out.println("request......."+request);
	        	// System.out.println("request......."+response);
	        	RequisicionEntity req =(RequisicionEntity) JsonConvertidor.fromJson(json, RequisicionEntity.class);
	        	// System.out.println("el nuevo objeto: "+orden );
	        	//pegar el valor de empresa, aeronave y contacato
	        	//orden.setFolio(1111);
	        	requisicionDao.save(req);	            
	        } catch (RuntimeException ignored) {
	        	ignored.printStackTrace();
	            // getUniqueEntity should throw exception
	        }
	       
	    }
	 
	   /////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllComp(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<RequisicionEntity> lista = requisicionDao.getAll();
			if (lista == null) {
				lista = new ArrayList<RequisicionEntity>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	   @RequestMapping(value = {"/delete/{folio}" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	   public void deleteOrden(HttpServletResponse response, HttpServletRequest request, @RequestBody String json,
		@PathVariable Long id) throws IOException {
		   requisicionDao.delete(requisicionDao.consult(id));
	   }
	

}