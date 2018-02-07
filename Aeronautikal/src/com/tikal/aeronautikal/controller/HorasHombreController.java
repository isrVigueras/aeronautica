package com.tikal.aeronautikal.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
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
import com.tikal.aeronautikal.dao.EmpleadoDao;
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
	 
	 @Autowired
	 @Qualifier("empleadoDao")
	EmpleadoDao empleadoDao;
	 

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
		   		entry.setIdDiscrepancia(Long.parseLong("5471169859813376"));
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
			System.out.println("lista:"+lista);
			if (lista == null) {
				lista = new ArrayList<HorasHombre>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	   
	   //lista de horas hombre asignadas o en estatus EN PROGRESO, EN PAUSA O TERMINADA
	   @RequestMapping(value = { "/getAsignadas" }, method = RequestMethod.GET, produces = "application/json")
			public void findAsignadas(HttpServletResponse response, HttpServletRequest request) throws IOException {
				AsignadorDeCharset.asignar(request, response);
				List<HorasHombre> lista = horasHombreDao.getAsignadas();
				
				System.out.println("Asignadas::"+lista);
				if (lista == null) {
					lista = new ArrayList<HorasHombre>();
				}
				response.getWriter().println(JsonConvertidor.toJson(lista));

			}
	 
	   @RequestMapping(value = { "/getNoAsignadas" }, method = RequestMethod.GET, produces = "application/json")
		public void findNoAsignadas(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<HorasHombre> lista = horasHombreDao.getNoAsignadas();
			
			System.out.println("No Asignadas::"+lista);
			if (lista == null) {
				lista = new ArrayList<HorasHombre>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}

	   
	   @RequestMapping(value = { "/getAsignadasByEmpleado/{idEmpleado}" }, method = RequestMethod.GET, produces = "application/json")
		public void findByEmpleado(HttpServletResponse response, HttpServletRequest request,
				@PathVariable Long idEmpleado) throws IOException {
		   System.out.println("ya entro a buscar horas hombe por empleado");
			AsignadorDeCharset.asignar(request, response);
			List<HorasHombre> lista= horasHombreDao.getByEmpleado(idEmpleado);

			System.out.println("Asignadas::"+lista);
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
	   
		   
	   @RequestMapping(value = {"/asignar/{idEmpleado}/{idHorasHombre}" }, method = RequestMethod.POST, produces = "application/json")
		public void asigna(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idEmpleado, @PathVariable Long idHorasHombre)
				throws IOException {
			AsignadorDeCharset.asignar(request, response);
			HorasHombre h = horasHombreDao.consult(idHorasHombre);
			h.setIdEmpleado(idEmpleado);
			h.setEstatus("ASIGNADA");
			h.setEmpleado((empleadoDao.consult(idEmpleado)).getNombreCompleto());
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
	    
	   @RequestMapping(value = {"/start/{id}" }, method = RequestMethod.POST,produces = "application/json")
		public void ini(HttpServletResponse response, HttpServletRequest request,@PathVariable Long id)
				throws IOException, java.text.ParseException {
		   System.out.println("si entraaaaaaa  INICIA");
			AsignadorDeCharset.asignar(request, response);
			HorasHombre h = horasHombreDao.consult(id);
			Locale l = new Locale("es","MX");
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"),l);
			Date fec =cal.getTime();
			System.out.println("fecha mexico:"+fec.toString());		
			
			if (h.getHoraIncio()== null){
				System.out.println("pasa el ifffff");
				h.setHoraIncio(fec);
				///////////cada que inicien un periodo se calculan los ms hasta la hora del paro y la guarda en tiempoParo
				long tiempoParo = diferenciasDeFechas(h.getHoraIncio(),getHoraParo());
				h.setTiempoParo(tiempoParo);
				System.out.println("tiempo paro1:"+h.getTiempoParo());
				//Date salida=getHoraParo(fec);
				h.setEstatus("EN PROGRESO");
				//horasHombreDao.update(h);
			}else{			   //inicia un periodo
				System.out.println("pasa al esle con "+h.getInicioParcial());
				//if (h.getInicioParcial()==null){
					System.out.println("------------");
					h.setInicioParcial(fec);
					if (fec.before(getHoraParo())){
						long tiempoParo = diferenciasDeFechas(h.getInicioParcial(),getHoraParo());
						h.setTiempoParo(tiempoParo);
						System.out.println("tiempo paro2:"+h.getTiempoParo());
						System.out.println("inicio parcial:"+h.getInicioParcial());
						h.setEstatus("EN PROGRESO");
						
					}
				//}else{
				//	System.out.println("presiono inicia cuando ya habia otro iniciado");
					
				//}
				//horasHombreDao.update(h);
			}
			horasHombreDao.update(h);
			response.getWriter().println(JsonConvertidor.toJson(h));
		}
	   
	   @RequestMapping(value = {"/pausa/{id}" }, method = RequestMethod.POST,produces = "application/json")
		public void stop(HttpServletResponse response, HttpServletRequest request,@PathVariable Long id)
				throws IOException, java.text.ParseException {
		   System.out.println("si entraaaaaaa  pausa");
			AsignadorDeCharset.asignar(request, response);
			boolean paro;
			HorasHombre h = horasHombreDao.consult(id);
			Locale l = new Locale("es","MX");
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"),l);
			Date fecStop =cal.getTime();
			System.out.println("la hora de pausa es :"+fecStop);
			h.setFinParcial(fecStop);
			///////////////////// validar la hora de salidA 
			//String salida=calculaHoraParo(fecStop);
			//System.out.println("Es la hora de salida?"+salida);
			paro=checarParo(fecStop);
			if (paro==true){ ///////////suma el parcial mas el tiempo de paro
				h.setTiempoTotal(h.getTiempoParcial()+h.getTiempoParo());
			}else{
				if (h.getTiempoParcial() == 0 ){   /// si es el primer inicio
						System.out.println("la hora del inicio es :"+h.getHoraIncio());
						System.out.println("la hora del stop es :"+fecStop);
						long dif = diferenciasDeFechas(h.getHoraIncio(),fecStop);
						h.setTiempoParcial(dif);
						String hp= formatoFecha(h.getTiempoParcial());
			 			h.setParcialEnHoras(hp);
				}else{                            // si es un periodo
					System.out.println("ELSE*** ");				
					long aux= h.getTiempoParcial();
				//	if (h.getInicioParcial() == null){  /// si solo presionaron el boton de stop sin iniciar antes
				//		System.out.println("no han iniciado primero :");
				//	}else{                               /// si se inicio un periodo correctamente
						System.out.println("ELSE  else inicio es :"+h.getInicioParcial());
						System.out.println("ELSE else stop es :"+fecStop);
						long dif = diferenciasDeFechas(h.getInicioParcial(),fecStop);
						long newParcial= aux+dif;
						h.setTiempoParcial(newParcial);
						System.out.println("parcial de milisegundos"+h.getTiempoParcial());
			 			
						String hp= formatoFecha(h.getTiempoParcial());
			 			h.setParcialEnHoras(hp);
			 			System.out.println("parcial en horas formateadas"+h.getParcialEnHoras());
						h.setInicioParcial(null);
						//h.setFinParcial(null);
				//}
					
					//horasHombreDao.update(h);
					//double dif = diferenciasDeFechas(h.getInicioParcial(),fecStop);
					
				}
			}
			h.setEstatus("EN PAUSA");
			horasHombreDao.update(h);
			response.getWriter().println(JsonConvertidor.toJson(h));
		}
	  
	   @RequestMapping(value = {"/stop/{id}" }, method = RequestMethod.POST,produces = "application/json")
	 		public void termina(HttpServletResponse response, HttpServletRequest request,@PathVariable Long id)
	 				throws IOException, java.text.ParseException {
	 		   System.out.println("si entraaaaaaa  termina");
	 			AsignadorDeCharset.asignar(request, response);
	 			Boolean paro;
	 			HorasHombre h = horasHombreDao.consult(id);
	 			Locale l = new Locale("es","MX");
	 			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"),l);
	 			Date fecStop =cal.getTime();
	 			paro=checarParo(fecStop);
				if (paro==true){ ///////////suma el parcial mas el tiempo de paro
					h.setTiempoTotal(h.getTiempoParcial()+h.getTiempoParo());
				}else{
		 			if (h.getTiempoParcial()==0){
		 				System.out.println("la hora del inicio es :"+h.getHoraIncio());
						System.out.println("la hora del stop es :"+fecStop);
						paro=checarParo(fecStop);
						long dif = diferenciasDeFechas(h.getHoraIncio(),fecStop);
						h.setTiempoTotal(dif);
						
		 			}else{
		 				
		 				h.setTiempoTotal(h.getTiempoParcial());
			 			h.setTiempoHoras(formatoFecha(h.getTiempoTotal()));
			 			String hp= formatoFecha(h.getTiempoParcial());
			 			h.setParcialEnHoras(hp);
			 			h.setEstatus("TERMINADA");
			 			horasHombreDao.update(h);
			 			System.out.println("total de milisegundos"+h.getTiempoTotal());
			 			System.out.println("total en horas formateadas"+h.getTiempoHoras());
		 				
		 			}
				}
	 			response.getWriter().println(JsonConvertidor.toJson(h));
	   }
	   
	   
	   @RequestMapping(value = {"/restart/{id}" }, method = RequestMethod.POST,produces = "application/json")
		public void restart(HttpServletResponse response, HttpServletRequest request,@PathVariable Long id)
				throws IOException, java.text.ParseException {
		   System.out.println("si entraaaaaaa  restart");
			AsignadorDeCharset.asignar(request, response);
			HorasHombre h = horasHombreDao.consult(id);
			Locale l = new Locale("es","MX");
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"),l);
			Date fecStop =cal.getTime();
			
			h.setHoraIncio(null);
			h.setInicioParcial(null);
			h.setFinParcial(null);
			h.setTiempoHoras("");
			h.setTiempoParcial(0);
			h.setParcialEnHoras("00:00:00");
			h.setTiempoParo(0);
			h.setTiempoTotal(0);
			h.setEstatus("ASIGNADA");
			
			horasHombreDao.update(h);
	 		System.out.println("SE REINICIO EL CONTADOR DE HORAS....");
			response.getWriter().println(JsonConvertidor.toJson(h));
  }
	   
	   
	 //Diferencias entre dos fechas
	    //@param fechaInicial La fecha de inicio
	    //@param fechaFinal  La fecha de fin
	    //@return Retorna el numero de dias entre dos fechas
	    public static synchronized long diferenciasDeFechas(Date fechaInicial, Date fechaFinal) throws java.text.ParseException {
	    	

	        long fechaInicialMs = fechaInicial.getTime();
	        long fechaFinalMs = fechaFinal.getTime();
	        long diferencia = fechaFinalMs - fechaInicialMs;
	        System.out.println("diferencia en milisegundos:"+diferencia);
	        System.out.println("diferencia formateada:"+formatoFecha(diferencia)) ; 
	      
	        return diferencia;
	    }
	 
	 
	    public static synchronized boolean checarParo(Date fecha) throws java.text.ParseException {
	    	
	    	boolean paro=false;
	        long horaEntradaMs = getHoraEntrada().getTime();
	        long horaParoMs = getHoraParo().getTime();
	        long fechaMs =fecha.getTime();
	       ///calculando la hora si esta entre la hora de paro y la hora de entrda
	        if ((fechaMs>= horaParoMs) && (fechaMs<=horaEntradaMs)){
	        	System.out.println("Pausa despues del PARO, se tomara en cuenta la ultima pausa a las:"+fecha);
	        	
	        }
	        if (fechaMs<horaParoMs){
	        	System.out.println("La pausa se realizo antes del paro....NO HAY PARO AUTOMATICO AUN:::: ");
	        }else{	
	        	
	        	System.out.println("No hubo movimientos en deshoras de trabajo, se realizara PARO AUTOMATICO!");
	        	paro=true;
	        }
	        return paro;
	    }
	    
	    public static String formatoFecha(long milisegundos){	    	    System.out.println("entra a formatear fecha con estos milisegundos:"+milisegundos);
		       double dif =  (milisegundos / (1000 * 60*60));
		       double hora = Math.floor(milisegundos/3600000);
		       double  restohora = milisegundos%3600000;
		       double minuto = Math.floor(restohora/60000);
		       double restominuto=restohora%60000;
		       double seg = Math.floor(restominuto/1000);
		       String sh= String.valueOf((int)hora);
		       String sm= String.valueOf((int)minuto);
		       String ss= String.valueOf((int)seg);
		       String parcial= sh+":"+sm+":"+ss;
		       System.out.println(" ---------------------horas formateada ="+parcial);      
	    	
	    	return parcial;
	    	
	    }
	    
	    public static Date getHoraParo(){
	 
	    	Calendar now = Calendar.getInstance();
	    	int anio= now.get(Calendar.YEAR);
	    	int mes =now.get(Calendar.MONTH);
	    	int dia = now.get(Calendar.DATE);
	    	int hora= 13;
	    	int min = 40;
	    	int seg = 0;
	    	
	    	Calendar horaSalida= Calendar.getInstance();
	    	horaSalida.set(Calendar.YEAR, anio);
	    	horaSalida.set(anio, mes, dia, hora, min, seg);
	    	Date theDate = horaSalida.getTime();
	    	System.out.println("-------------------La hora de salida es:"+theDate);

	    	return theDate;
	    }

	    public static Date getHoraEntrada(){
	   	 
	    	Calendar now = Calendar.getInstance();
	    	int anio= now.get(Calendar.YEAR);
	    	int mes =now.get(Calendar.MONTH);
	    	int dia = now.get(Calendar.DATE);
	    	int hora= 9;
	    	int min = 0;
	    	int seg = 0;
	    	
	    	Calendar horaSalida= Calendar.getInstance();
	    	horaSalida.set(Calendar.YEAR, anio);
	    	horaSalida.set(anio, mes, dia, hora, min, seg);
	    	Date theDate = horaSalida.getTime();
	    	System.out.println("-------------------La hora de entrada es:"+theDate);

	    	return theDate;
	    }
}
