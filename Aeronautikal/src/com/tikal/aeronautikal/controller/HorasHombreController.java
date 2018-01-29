package com.tikal.aeronautikal.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.management.timer.Timer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.swing.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.repackaged.com.google.common.flogger.parser.ParseException;
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
		   		Locale l = new Locale("es","MX");
				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"),l);
				Date fec =cal.getTime();
		   		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd:hh:mm:ss");
				//String fecha= sdf.format(new Date());
		   		//entry.setHoraIncio(fec);
		   	//	entry.setFinParcial(fecha);
		   	//	entry.setHoraFin(fecha);
		  
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
	   
	     @RequestMapping(value = { "/getByEmpleado/{idEmpleado}" }, method = RequestMethod.GET, produces = "application/json")
		public void findByEmpleado(HttpServletResponse response, HttpServletRequest request,
				@PathVariable Long idEmpleado) throws IOException {
		   System.out.println("ya entro a buscar horas hombe por empleado");
			AsignadorDeCharset.asignar(request, response);
			List<HorasHombre> hrs= horasHombreDao.getByEmpleado(idEmpleado);
			if (hrs==null){
				hrs= new ArrayList<HorasHombre>();
			}
			
			response.getWriter().println(JsonConvertidor.toJson(hrs));
			
		}
	   
	   
	   @RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.POST)
	   public void deleteHoras(HttpServletResponse response, HttpServletRequest request, @PathVariable Long id)
			   throws IOException {
		   System.out.println("si esta en delete horas"+id);
		   horasHombreDao.delete(horasHombreDao.consult(id));
		   System.out.println("Horas hombre eliminado....");
		   response.getWriter().println("ok");
		   
	   }
	   
		   
	   @RequestMapping(value = {"/asignar" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void asigna(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
				throws IOException {
			AsignadorDeCharset.asignar(request, response);
			HorasHombre h = (HorasHombre) JsonConvertidor.fromJson(json, HorasHombre.class);
			horasHombreDao.update(h);
			response.getWriter().println(JsonConvertidor.toJson(h));
		}
	   
	   
	   @RequestMapping(value = {"/update" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
				throws IOException {
			AsignadorDeCharset.asignar(request, response);
			HorasHombre h = (HorasHombre) JsonConvertidor.fromJson(json, HorasHombre.class);
			horasHombreDao.update(h);
			response.getWriter().println(JsonConvertidor.toJson(h));
		}
	    
	   @RequestMapping(value = {"/inicia/{id}" }, method = RequestMethod.GET)
		public void ini(@PathVariable Long id)
				throws IOException {
		   System.out.println("si entraaaaaaa  INICIA");
			//AsignadorDeCharset.asignar(request, response);
			HorasHombre h = horasHombreDao.consult(id);
			Locale l = new Locale("es","MX");
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"),l);
			Date fec =cal.getTime();
			//Calendar  resta = cal-Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"),l);
		//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd::HH:mm:ss");
			//String fec= sdf.format(cal.getTime());
			System.out.println("fecha mexico:"+fec.toString());
			Date dia = new Date();
			////System.out.println("Date:"+dia);
			h.setHoraIncio(fec);
		//	h.setHoraFin();
			//h.setHoraFin(h.getHoraIncio()-h.getHoraFin());
			horasHombreDao.update(h);
			//response.getWriter().println(JsonConvertidor.toJson(h));
		}
	   
	   @RequestMapping(value = {"/stop/{id}" }, method = RequestMethod.GET)
		public void stop(@PathVariable Long id)
				throws IOException, java.text.ParseException {
		   System.out.println("si entraaaaaaa  stop");
			//AsignadorDeCharset.asignar(request, response);
			HorasHombre h = horasHombreDao.consult(id);
			Locale l = new Locale("es","MX");
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"),l);
			Date fecStop =cal.getTime();
			System.out.println("la hora del inicio es :"+h.getHoraIncio());
			System.out.println("la hora del stop es :"+fecStop);
			double dif = diferenciasDeFechas(h.getHoraIncio(),fecStop);
			//Calendar  resta = cal-Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"),l);
		//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd::HH:mm:ss");
			//String fec= sdf.format(cal.getTime());
			
			System.out.println("dias de diferencia:"+dif);
			//Date dia = new Date();
			////System.out.println("Date:"+dia);
			//h.setHoraIncio(fec);
		//	h.setHoraFin();
			//h.setHoraFin(h.getHoraIncio()-h.getHoraFin());
			//horasHombreDao.update(h);
			//response.getWriter().println(JsonConvertidor.toJson(h));
		}
//	   @RequestMapping(value = {"/timer/{idHoras}/{estado}" }, method = RequestMethod.GET)
//		public void start(HttpServletResponse response, HttpServletRequest request,@PathVariable Long idHoras, @PathVariable String estado)
//				throws IOException {
//			AsignadorDeCharset.asignar(request, response);
//			HorasHombre h = horasHombreDao.consult(idHoras);
//			//Integer tiempoEnMilisegundos=(600000000);
//			System.out.println("ya estoy en timerrr y el estado del timer es::"+estado);
//			Timer timer= new Timer();
//			System.out.println("ya estoy en timerrr y el estado del}2");
////			Timer timer = new Timer (tiempoEnMilisegundos, new ActionListener ()
////					
////			{ 
////			    public void actionPerformed(ActionEvent e) 
////			    { 
////			        System.out.println("esta en el timer");//bfuncion de start
////			     } 
////			}); 
//			//checar si ya hay hora de inicio de la tarea
//			//timer.stop();
//			
//			
//			
//			System.out.println("checando si hay hora inicio:"+h.getHoraIncio());
//			if (h.getHoraIncio()== null){
//				System.out.println("pasa el ifffff");
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd:hh:mm:ss");
//				String fecha= sdf.format(new Date());
//				h.setHoraIncio(fecha);
//				horasHombreDao.update(h);
//				timer.start();
//				System.out.println("timer primero:"+timer.getNbNotifications());
//				System.out.println("hora inicio:"+h.getHoraIncio());
//				
//			}else{			
//				System.out.println("pasa al esle");
//					switch (estado){
//					case "start": timer.start();System.out.println("timer en start:"); break;
//					case "stop": timer.stop();System.out.println("timer en stop");break;
//					}
//					System.out.println("timer:"+timer);
//			}
//		
//			
//			response.getWriter().println("ok en timer");
//		}
//	
//	  
	  
	   
	   
	 //Diferencias entre dos fechas
	    //@param fechaInicial La fecha de inicio
	    //@param fechaFinal  La fecha de fin
	    //@return Retorna el numero de dias entre dos fechas
	    public static synchronized double diferenciasDeFechas(Date fechaInicial, Date fechaFinal) throws java.text.ParseException {

//	        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
//	        String fechaInicioString = df.format(fechaInicial);
//	        try {
//	            fechaInicial = df.parse(fechaInicioString);
//	        } catch (ParseException ex) {
//	        }
//
//	        String fechaFinalString = df.format(fechaFinal);
//	        try {
//	            fechaFinal = df.parse(fechaFinalString);
//	        } catch (ParseException ex) {
//	        }

	        long fechaInicialMs = fechaInicial.getTime();
	        long fechaFinalMs = fechaFinal.getTime();
	        long diferencia = fechaFinalMs - fechaInicialMs;
	        System.out.println("diferencia en milisegundos:"+diferencia);
	       // double dias = Math.floor(diferencia);
	       double dif =  (diferencia / (1000 * 60));
	       System.out.println("dif:"+dif);
	        return diferencia;
	    }
	 
	 


}
