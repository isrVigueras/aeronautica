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


import com.tikal.aeronautikal.dao.ValeDao;

import com.tikal.aeronautikal.entity.ValeEntity;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;


@Controller
@RequestMapping(value="/vale")
public class ValeController {

	
	 @Autowired
	 @Qualifier("valeDao")
	 ValeDao valeDao;
	 
	 
	 @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Vale");

	    }
	 
	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addComponenteGet(@ModelAttribute("entry") ValeEntity entry) {
		   System.out.println("si entra a vale controller");   
		   	try {
		   		
		   		//entry.setIdEvento(Long.parseLong("10023131"));
		   		entry.setEstatus("ABIERTO");
		   		entry.setFecha("10/01/2018");
		   		entry.setIdDiscrepancia(Long.parseLong("5348024557502464"));		  
	            System.out.println("si asign/ valor"+entry);
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("vale planchado guardado");	    
	        valeDao.save(entry);   //implementa el dao  
	        return "evento";
		}
	
	 
	 @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addEvento(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) throws IOException{
	    	  System.out.println("si entra al add vale por POST"+json);
	        try {
	        	AsignadorDeCharset.asignar(request, response);
	        	 System.out.println("request......."+request);
	        	 System.out.println("request......."+response);
	        	ValeEntity v =(ValeEntity) JsonConvertidor.fromJson(json, ValeEntity.class);
	        	// System.out.println("el nuevo objeto: "+orden );
	        	//pegar el valor de empresa, aeronave y contacato
	        	//cmp.setD_pendientes(50);//aqui va funcion para calcular cuantas piezas pendientes hay de cada componente
	        	//orden.setFolio(1111);
	        	valeDao.save(v);	            
	        } catch (RuntimeException ignored) {
	        	ignored.printStackTrace();
	            // getUniqueEntity should throw exception
	        }
	       
	    }
	 
	   /////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllVales(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<ValeEntity> lista = valeDao.findAll();
			if (lista == null) {
				lista = new ArrayList<ValeEntity>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	     
	   
	   
	   @RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.POST)
	   public void deleteEvento(HttpServletResponse response, HttpServletRequest request, @PathVariable Long id)
			   throws IOException {
		   System.out.println("si esta en delete vale"+id);
		   valeDao.delete(valeDao.consult(id));
		   System.out.println("vale eliminado....");
		   response.getWriter().println("ok");
		   
	   }
	   
		   
	   
	   
	   @RequestMapping(value = {"/update" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
				throws IOException {
			AsignadorDeCharset.asignar(request, response);
			ValeEntity v = (ValeEntity) JsonConvertidor.fromJson(json, ValeEntity.class);
			valeDao.update(v);
			response.getWriter().println(JsonConvertidor.toJson(v));
		}
	    
	   
	   @RequestMapping(value = { "/getByDiscrepancia/{idDiscrepancia}" }, method = RequestMethod.GET, produces = "application/json")
		public void findByDiscrepancia(HttpServletResponse response, HttpServletRequest request,
				@PathVariable Long idDiscrepancia) throws IOException {
		   System.out.println("ya entro a vale controller....");
			AsignadorDeCharset.asignar(request, response);
			List<ValeEntity> vs= valeDao.getByDiscrepancia(idDiscrepancia);
			System.out.println("lista de vales:"+vs);
			if (vs==null){
				vs= new ArrayList<ValeEntity>();
			}		
			response.getWriter().println(JsonConvertidor.toJson(vs));
			
		}

}
