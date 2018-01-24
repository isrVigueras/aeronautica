package com.tikal.aeronautikal.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.aeronautikal.dao.HorasHombreDao;
import com.tikal.aeronautikal.entity.HorasHombre;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

@Controller
@RequestMapping(value="/horasHombre")
public class HorasHombreController {
	
	 @Autowired
	 @Qualifier("horasHombreDao")
	 HorasHombreDao horasHombreDao;
	 

	 @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Categoria");

	    }
	 
	 ////////////////////////////////////////////////*****************************************************************
	 
	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addHorasGet(@ModelAttribute("entry") HorasHombre entry) {
		   System.out.println("si entra a Categoria controller");   
		   	try {
		   		
		   		entry.setAccion("Accion de la discrepancia");
		   		entry.setEstatus("ABIERTA");
		   		entry.setIdDiscrepancia(Long.parseLong("02029392930"));
		   		entry.setIdEmpleado(Long.parseLong("0101010101"));
		   		entry.setIdOrden(Long.parseLong("0202020202"));
		   		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd:hh:mm:ss");
				String fecha= sdf.format(new Date());
		   		entry.setHoraIncio(fecha);
		   		entry.setFinParcial(fecha);
		   		entry.setHoraFin(fecha);
		  
	            System.out.println("si asign/ valor"+entry);
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("Horas hombre planchado guardado");	    
	        horasHombreDao.save(entry);   //implementa el dao  
	       // calculaHoras(entry);
	        return "horasHombre";
		}
	
	 /////////////////////////////////////////////////////********************************************************

	 
	 @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addCategoria(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) throws IOException{
	    	  System.out.println("si entra al add HorasHombre por POST"+json);
	        try {
	        	AsignadorDeCharset.asignar(request, response);
	        	 System.out.println("request......."+request);
	        	 System.out.println("request......."+response);
	        	 HorasHombre h =(HorasHombre) JsonConvertidor.fromJson(json,HorasHombre.class);
	        	// System.out.println("el nuevo objeto: "+orden );
	        	//pegar el valor de empresa, aeronave y contacato
	        	//cmp.setD_pendientes(50);//aqui va funcion para calcular cuantas piezas pendientes hay de cada componente
	        	//orden.setFolio(1111);
	        	horasHombreDao.save(h);	            
	        	
	        } catch (RuntimeException ignored) {
	        	ignored.printStackTrace();
	            // getUniqueEntity should throw exception
	        }
	       
	    }
	 
	   /////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllHoras(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<HorasHombre> lista = horasHombreDao.getAll();
			if (lista == null) {
				lista = new ArrayList<HorasHombre>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	     
	   
	   
	   @RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.POST)
	   public void deleteHoras(HttpServletResponse response, HttpServletRequest request, @PathVariable Long id)
			   throws IOException {
		   System.out.println("si esta en delete horas"+id);
		   horasHombreDao.delete(horasHombreDao.consult(id));
		   System.out.println("Horas hombre eliminado....");
		   response.getWriter().println("ok");
		   
	   }
	   
		   
	   
	   
	   @RequestMapping(value = {"/update" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
				throws IOException {
			AsignadorDeCharset.asignar(request, response);
			HorasHombre h = (HorasHombre) JsonConvertidor.fromJson(json, HorasHombre.class);
			horasHombreDao.update(h);
			response.getWriter().println(JsonConvertidor.toJson(h));
		}
	    
	   @RequestMapping(value = {"/timer/{idHoras}/{estado}" }, method = RequestMethod.GET)
		public void start(HttpServletResponse response, HttpServletRequest request,@PathVariable Long idHoras, @PathVariable String estado)
				throws IOException {
			AsignadorDeCharset.asignar(request, response);
			HorasHombre h = horasHombreDao.consult(idHoras);
			Integer tiempoEnMilisegundos=1000;
			System.out.println("ya estoy en timerrrrrrrrrrrrrrrrrrr:");
			Timer timer = new Timer (tiempoEnMilisegundos, new ActionListener ()
					
			{ 
			    public void actionPerformed(ActionEvent e) 
			    { 
			        System.out.println("esta en el timer");
			     } 
			}); 
			//checar si ya hay hora de inicio de la tarea
			
			if (h.getHoraIncio().equals(null)){
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd:hh:mm:ss");
				String fecha= sdf.format(new Date());
				h.setHoraIncio(fecha);
				horasHombreDao.update(h);
				timer.start();
				System.out.println("timer primero:"+timer);
				System.out.println("hra inicio:"+h.getHoraIncio());
				
			}else{			
					switch (estado){
					case "start": timer.start();System.out.println("timer en start");
					case "stop": timer.stop();System.out.println("timer en stop");
					}
					System.out.println("timer:"+timer);
			}
		
			
			response.getWriter().println("ok en timer");
		}
	
	  
	  
	   
	   
	 public float  calculaHoras(HorasHombre h){
		 
		 float time=0;
		 System.out.println("inicio:"+h.getHoraIncio());
		 System.out.println("final:"+h.getHoraFin());
		 //time = h.getHoraIncio() - h.getHoraFin();
		 System.out.println("total:"+time);
		 return time;
	 }
	 
	 


}
