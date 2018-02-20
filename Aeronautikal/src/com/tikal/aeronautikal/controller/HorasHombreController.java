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
import com.tikal.aeronautikal.dao.PerfilDAO;
import com.tikal.aeronautikal.dao.SessionDao;
import com.tikal.aeronautikal.dao.UsuarioDao;
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
	 
	 @Autowired
	 @Qualifier("sessionDao")
	 SessionDao sessionDao;
	 
	@Autowired
	@Qualifier ("usuarioDao")
	UsuarioDao usuarioDao;

		
	@Autowired
	PerfilDAO perfilDAO; 
	 

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
	   
		   
	   @RequestMapping(value = {"/asignar/{idEmpleado}/{idHorasHombre}/{userName}" }, method = RequestMethod.POST, produces = "application/json")
		public void asigna(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idEmpleado,
				@PathVariable Long idHorasHombre,  @PathVariable String userName)	throws IOException {
		   System.out.println("Horas hombre asignar...usu,horhom, user:."+idEmpleado+","+idHorasHombre+","+userName);
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 34, sessionDao,userName)){  
				AsignadorDeCharset.asignar(request, response);
				HorasHombre h = horasHombreDao.consult(idHorasHombre);
				h.setIdEmpleado(idEmpleado);  // en realidad es idUsuario
				h.setEstatus("ASIGNADA");
				h.setEmpleado(usuarioDao.consult(idEmpleado).getNombreCompleto());
				horasHombreDao.update(h);
				response.getWriter().println(JsonConvertidor.toJson(h));
		   }else{
				response.sendError(403);
			}
		}
	   
	   
	   @RequestMapping(value = {"/update" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
				throws IOException {
			AsignadorDeCharset.asignar(request, response);
			HorasHombre h = (HorasHombre) JsonConvertidor.fromJson(json, HorasHombre.class);
			horasHombreDao.update(h);
			response.getWriter().println(JsonConvertidor.toJson(h));
		}
	    
	   @RequestMapping(value = {"/start/{id}/{usrName}" }, method = RequestMethod.POST,produces = "application/json")
		public void ini(HttpServletResponse response, HttpServletRequest request,@PathVariable Long id, @PathVariable String userName)
				throws IOException, java.text.ParseException {
		   System.out.println("si entraaaaaaa  INICIA");
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 35, sessionDao,userName)){  
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
							//if (fec.before(getHoraParo())){
								long tiempoParo = diferenciasDeFechas(h.getInicioParcial(),getHoraParo());
								h.setTiempoParo(tiempoParo);
								System.out.println("tiempo paro2:"+h.getTiempoParo());
								System.out.println("inicio parcial:"+h.getInicioParcial());
								h.setEstatus("EN PROGRESO");
								
							//}
						//}else{
						//	System.out.println("presiono inicia cuando ya habia otro iniciado");
							
						//}
						//horasHombreDao.update(h);
					}
					horasHombreDao.update(h);
					response.getWriter().println(JsonConvertidor.toJson(h));
		   }else{
				response.sendError(403);
			}
		}
	   
	   @RequestMapping(value = {"/pausa/{id}/{userName}" }, method = RequestMethod.POST,produces = "application/json")
		public void stop(HttpServletResponse response, HttpServletRequest request,@PathVariable Long id,  @PathVariable String userName)
				throws IOException, java.text.ParseException {
		   System.out.println("si entraaaaaaa  pausa");
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 36, sessionDao,userName)){  

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
					if (paro){ ///////////suma el parcial mas el tiempo de paro
						System.out.println("debo hacer paro automatico");
						h.setTiempoTotal(h.getTiempoParcial()+h.getTiempoParo());
						//paro=false;
						String hp= formatoFecha(h.getTiempoTotal());
			 			h.setParcialEnHoras(hp);
						h.setTiempoParo(0);
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
		   }else{
				response.sendError(403);
			}
		}
	  
	   @RequestMapping(value = {"/stop/{id}/{userName}" }, method = RequestMethod.POST,produces = "application/json")
	 		public void termina(HttpServletResponse response, HttpServletRequest request,@PathVariable Long id,  @PathVariable String userName)
	 				throws IOException, java.text.ParseException {
	 		   System.out.println("si entraaaaaaa  termina");
	 		  if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 37, sessionDao,userName)){  
			 			AsignadorDeCharset.asignar(request, response);
			 			Boolean paro;
			 			HorasHombre h = horasHombreDao.consult(id);
			 			Locale l = new Locale("es","MX");
			 			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"),l);
			 			Date fecStop =cal.getTime();
			 			paro=checarParo(fecStop);
						if (paro){ ///////////si termina antes de la hora de entrada, suma el parcial mas el tiempo de paro
							h.setTiempoTotal(h.getTiempoParcial()+h.getTiempoParo());
							String hp= formatoFecha(h.getTiempoTotal());
				 			h.setParcialEnHoras(hp);
							h.setTiempoParo(0);
						}else{
				 			if (h.getTiempoParcial()==0){
				 				System.out.println("la hora del inicio es :"+h.getHoraIncio());
								System.out.println("la hora del stop es :"+fecStop);
								//paro=checarParo(fecStop);
								long dif = diferenciasDeFechas(h.getHoraIncio(),fecStop);
								h.setTiempoTotal(dif);
								
				 			}else{
				 				
				 				h.setTiempoTotal(h.getTiempoParcial());
				 			}
						}
					 	h.setTiempoHoras(formatoFecha(h.getTiempoTotal()));
					 	String hp= formatoFecha(h.getTiempoParcial());
					 	h.setParcialEnHoras(hp);
					 	h.setEstatus("TERMINADA");
					 	horasHombreDao.update(h);
					 	System.out.println("total de milisegundos"+h.getTiempoTotal());
					 	System.out.println("total en horas formateadas"+h.getTiempoHoras());	 						
						
			 			response.getWriter().println(JsonConvertidor.toJson(h));
	 		 }else{
					response.sendError(403);
				}
	   }
	   
	   
	   @RequestMapping(value = {"/restart/{id}/{userName}" }, method = RequestMethod.POST,produces = "application/json")
		public void restart(HttpServletResponse response, HttpServletRequest request,@PathVariable Long id, @PathVariable String userName)
				throws IOException, java.text.ParseException {
		   System.out.println("si entraaaaaaa  restart");
		   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 38, sessionDao,userName)){
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
		   }else{
				response.sendError(403);
			}
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
	        if (diferencia<0){
	        	diferencia=0;
	        }
	      
	        return diferencia;
	    }
	 
	 
	    public static synchronized boolean checarParo(Date fecha) throws java.text.ParseException {
	    	
	    	boolean paro=false;
	        long horaEntradaMs = getHoraEntrada().getTime();
	        long horaParoMs = getHoraParo().getTime();
	        long fechaMs =fecha.getTime();
	       ///calculando la hora si esta entre la hora de paro y la hora de entrda
	        if ((fechaMs>=horaEntradaMs)){
	        	System.out.println("PARO AUTOMATICO, porque no  hubo actividad en la discrepancia hasta la hora::"+fecha);
	        	paro=true;
	        }
//	        if (fechaMs>horaEntradaMs) {
//	        	System.out.println("Se realiza paro automatico, porque no  hubo actividad en la discrepancia::: ");
//	        }
//	        
//	        if (fechaMs>horaParoMs){	
//	        	System.out.println("Se realizo una pausa  despues del paro...NO HAY PARO AUTOMATICO :::: ");
//	        	//paro=true;
//	        }
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
	    	int hora= 18;
	    	int min = 0;
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
	    	horaSalida.set(anio, mes, dia+1, hora, min, seg);
	    	Date theDate = horaSalida.getTime();
	    	System.out.println("-------------------La hora de entrada es:"+theDate);

	    	return theDate;
	    }
}
