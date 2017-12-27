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

import com.tikal.aeronautikal.dao.EventoDao;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.EventoEntity;
import com.tikal.aeronautikal.service.EventoService;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

@Controller
@RequestMapping(value="/evento")
public class EventoController {

	

	 @Autowired
	    private EventoService eventoService;
	 
	 @Autowired
	 @Qualifier("eventoDao")
	 EventoDao eventoDao;
	 
	 
	 @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Evento");

	    }
	 
	 ////////////////////////////////////////////////*****************************************************************
	 
	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addComponenteGet(@ModelAttribute("entry") EventoEntity entry) {
		   System.out.println("si entra a Evento controller");   
		   	try {
		   		
		   		entry.setIdEvento(Long.parseLong("10023131"));
		   		entry.setCosto(345);
		   		entry.setDuracion(21);
		   		entry.setNombreEvento("renta de maquinaria especializada");
		   		entry.setIdDiscrepancia(Long.parseLong("5506354231902208"));
		  
	            System.out.println("si asign/ valor"+entry);
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("evento planchado guardado");	    
	        eventoService.save(entry);   //implementa el dao  
	        return "evento";
		}
	
	 /////////////////////////////////////////////////////********************************************************

	 
	 @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addEvento(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) throws IOException{
	    	  System.out.println("si entra al add evento por POST"+json);
	        try {
	        	AsignadorDeCharset.asignar(request, response);
	        	 System.out.println("request......."+request);
	        	 System.out.println("request......."+response);
	        	EventoEntity e =(EventoEntity) JsonConvertidor.fromJson(json, EventoEntity.class);
	        	// System.out.println("el nuevo objeto: "+orden );
	        	//pegar el valor de empresa, aeronave y contacato
	        	//cmp.setD_pendientes(50);//aqui va funcion para calcular cuantas piezas pendientes hay de cada componente
	        	//orden.setFolio(1111);
	        	eventoDao.save(e);	            
	        } catch (RuntimeException ignored) {
	        	ignored.printStackTrace();
	            // getUniqueEntity should throw exception
	        }
	       
	    }
	 
	   /////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllEventos(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<EventoEntity> lista = eventoDao.getAll();
			if (lista == null) {
				lista = new ArrayList<EventoEntity>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	     
	
	   
	   
	   @RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	   public void deleteEvento(HttpServletResponse response, HttpServletRequest request, @RequestBody String json,
		@PathVariable Long id) throws IOException {
		   System.out.println("hij");
		   eventoDao.delete(eventoDao.consult(id));
		   
	   }
	   
		   
	   
	   
	   @RequestMapping(value = {"/update" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
				throws IOException {
			AsignadorDeCharset.asignar(request, response);
			EventoEntity c = (EventoEntity) JsonConvertidor.fromJson(json, EventoEntity.class);
			//Empleado e= evo.getEmpleado();
			eventoDao.update(c);
			response.getWriter().println(JsonConvertidor.toJson(c));
		}
	    
	   
	   @RequestMapping(value = { "/getByDiscrepancia/{idDiscrepancia}" }, method = RequestMethod.GET, produces = "application/json")
		public void findByDiscrepancia(HttpServletResponse response, HttpServletRequest request,
				@PathVariable Long idDiscrepancia) throws IOException {
		   System.out.println("ya entro a evento controller");
			AsignadorDeCharset.asignar(request, response);
			List<EventoEntity> evs= eventoDao.getByDiscrepancia(idDiscrepancia);
			if (evs==null){
				evs= new ArrayList<EventoEntity>();
			}
			
			response.getWriter().println(JsonConvertidor.toJson(evs));
			
		}
	   
}
