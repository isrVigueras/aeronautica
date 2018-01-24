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
import com.tikal.aeronautikal.dao.PuestoDao;
import com.tikal.aeronautikal.entity.PuestoEntity;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;



@Controller
@RequestMapping(value="/puesto")
public class PuestoController {

	
	 @Autowired
	 @Qualifier("puestoDao")
	 PuestoDao puestoDao;
	 

	 @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Puesto");

	    }
	 
	 ////////////////////////////////////////////////*****************************************************************
	 
	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addPuestoGet(@ModelAttribute("entry") PuestoEntity entry) {
		   System.out.println("si entra a Categoria controller");   
		   	try {
		   		
		   		entry.setClave("1");
		   		entry.setDescripcion("prueba de  catalogo de Puesto");
		   		
		  
	            System.out.println("si asign/ valor"+entry);
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("Puesto planchado guardado");	    
	        puestoDao.save(entry);   //implementa el dao  
	        return "categoria";
		}
	
	 /////////////////////////////////////////////////////********************************************************

	 
	 @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addPuesto(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) throws IOException{
	    	  System.out.println("si entra al add Puesto por POST"+json);
	        try {
	        	AsignadorDeCharset.asignar(request, response);
	        	 System.out.println("request......."+request);
	        	 System.out.println("request......."+response);
	        	 PuestoEntity p =(PuestoEntity) JsonConvertidor.fromJson(json,PuestoEntity.class);
	        	// System.out.println("el nuevo objeto: "+orden );
	        	//pegar el valor de empresa, aeronave y contacato
	        	//cmp.setD_pendientes(50);//aqui va funcion para calcular cuantas piezas pendientes hay de cada componente
	        	//orden.setFolio(1111);
	        	puestoDao.save(p);	            
	        } catch (RuntimeException ignored) {
	        	ignored.printStackTrace();
	            // getUniqueEntity should throw exception
	        }
	       
	    }
	 
	   /////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllPuestos(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<PuestoEntity> lista = puestoDao.getAll();
			if (lista == null) {
				lista = new ArrayList<PuestoEntity>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	     
	   
	   
	   @RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.POST)
	   public void deletePuesto(HttpServletResponse response, HttpServletRequest request, @PathVariable Long id)
			   throws IOException {
		   System.out.println("si esta en delete puesto"+id);
		   puestoDao.delete(puestoDao.consult(id));
		   System.out.println("Puesto eliminado....");
		   response.getWriter().println("ok");
		   
	   }
	   
		   
	   
	   
	   @RequestMapping(value = {"/update" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
				throws IOException {
			AsignadorDeCharset.asignar(request, response);
			PuestoEntity p = (PuestoEntity) JsonConvertidor.fromJson(json, PuestoEntity.class);
			puestoDao.update(p);
			response.getWriter().println(JsonConvertidor.toJson(p));
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
