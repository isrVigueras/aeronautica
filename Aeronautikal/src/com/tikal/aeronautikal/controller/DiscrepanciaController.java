package com.tikal.aeronautikal.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.tikal.aeronautikal.controller.vo.ComDisVo;
import com.tikal.aeronautikal.controller.vo.DetalleDiscrepanciaVo;
import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.dao.ComponenteDao;
import com.tikal.aeronautikal.dao.ComponenteDiscrepanciaDao;
import com.tikal.aeronautikal.dao.DiscrepanciaDao;
import com.tikal.aeronautikal.dao.EmpresaDao;
import com.tikal.aeronautikal.dao.EventoDao;
import com.tikal.aeronautikal.dao.HorasHombreDao;
import com.tikal.aeronautikal.dao.OrdenDao;
import com.tikal.aeronautikal.dao.RequisicionDao;
import com.tikal.aeronautikal.dao.ValeDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.ComponenteDiscrepancia;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.EmpresaEntity;
import com.tikal.aeronautikal.entity.EventoEntity;
import com.tikal.aeronautikal.entity.HorasHombre;
import com.tikal.aeronautikal.entity.ValeEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.formatos.GeneraDiscrepanciaPdf;
import com.tikal.aeronautikal.formatos.GeneraOrdenPdf;
import com.tikal.aeronautikal.service.DiscrepanciaService;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

@Controller
@RequestMapping(value="/discrepancia")
public class DiscrepanciaController {

	
		@Autowired private DiscrepanciaService discrepanciaService;
		 
		 @Autowired
		 @Qualifier("discrepanciaDao")
		 DiscrepanciaDao discrepanciaDao;
		 
		 @Autowired
		 @Qualifier("componenteDao")
		 ComponenteDao componenteDao;
		 
		 @Autowired
		 @Qualifier("componenteDiscrepanciaDao")
		 ComponenteDiscrepanciaDao componenteDiscrepanciaDao;
		 
		 @Autowired
		 @Qualifier("eventoDao")
		 EventoDao eventoDao;
		 
		 @Autowired
		 @Qualifier("ordenDao")
		 OrdenDao ordenDao;
		 
		 
		 @Autowired
		 @Qualifier("aeronaveDao")
		 AeronaveDao aeronaveDao;
			
		 @Autowired
		 @Qualifier("empresaDao")
		 EmpresaDao empresaDao;
			
		 @Autowired
		 @Qualifier("requisicionDao")
		 RequisicionDao requisicionDao;
		 
		 @Autowired
		 @Qualifier("valeDao")
		 ValeDao valeDao;
		 
		 @Autowired
		 @Qualifier("horasHombreDao")
		 HorasHombreDao horasHombreDao;
		 
		 @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
		   
		   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
			   response.getWriter().println("Prueba del mètodo PROBAR en Discrepancias");

		    }
		 
		 ////////////////////////////////////////////////*****************************************************************
		 
		 @RequestMapping(value = "/add_", method = RequestMethod.GET)
		    public String addDiscrepanciaGet(@ModelAttribute("entry") DiscrepanciaEntity entry) {
			   System.out.println("si entra a Discrepancia controller");   
			   	try {
			   	    entry.setId(Long.parseLong("22222222222"));
			   		entry.setFolio("0707-2017-12-5_prueba");
			   		entry.setAccion("reparar electricidad de....");
			   		entry.setDescripcion("123_descripcion del componente");
			   		entry.setFolioOrden(Long.parseLong("2"));
			   		entry.setFechaApertura("12/12/2017");
			   		entry.setSeccion("03");
			   		entry.setTaller("03");
			   	/*	EventoEntity evento1 = new EventoEntity();
			   //		evento1.setIdEvento(("2121212121"));
			   		evento1.setNombreEvento("evento1");
			   		evento1.setDuracion(2);
			   		evento1.setCosto(20);
			   		
			   		List<EventoEntity> eventos = new ArrayList<EventoEntity>();
			   		eventos.add(evento1);
			   		entry.setEventos(eventos);
			   	  System.out.println("eventos:"+eventos);  */ 
			   		//entry.setEventos
			   		////entry.setInstaladoPor();
			   		//entry.setOriginadoPor();
			   		//entry.setFechaApertura("01/12/2017");
	            System.out.println("si asign/ valor"+entry);
		        } catch (RuntimeException ignored) {
		            // getUniqueEntity should throw exception 
		        }
			   System.out.println("yaaaaa");	     
		         discrepanciaService.save(entry);   //implementa el dao  
		        return "Orden_de_trabajo";
			}
		
		 /////////////////////////////////////////////////////********************************************************

		                            // id de la orden
		 @RequestMapping(value = {"/add/{folio}"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
		   public void addDiscrepancia(HttpServletResponse response, HttpServletRequest request, @RequestBody String json,
				   @PathVariable Long folio) throws IOException{
		    	  System.out.println("si entra al add con el folio de orden :"+folio+"el json: "+json);
		        try {
		        	System.out.println("++++++++");
		        	AsignadorDeCharset.asignar(request, response);
		        	 System.out.println("request......."+request);
		        	 System.out.println("response......."+response);
		        	DiscrepanciaEntity d =(DiscrepanciaEntity) JsonConvertidor.fromJson(json, DiscrepanciaEntity.class);
		        	
//		        	List<EventoEntity> eventos = new ArrayList<EventoEntity>();
//		        	d.setEventos(eventos);
//		        	 System.out.println("el folio de la discre: "+d.getFolio());
//		        	//pegar el valor de empresa, aeronave y contacato
		        	d.setFolioOrden(folio);
		        	System.out.println("el folio de la orden es--.: "+d.getFolioOrden() );
		        	d.setFolio((d.getTaller()+d.getSeccion()+"-"+ordenDao.consult(folio).getFolio()));
		        	 System.out.println("el nuevo folio de discrepancia: "+ d.getFolio());
		        	 d.setEstatus("ABIERTA");
		        	
		        	 
		        	 //ComponenteEntity c = new ComponenteEntity();
		        	// ACTUALIZABA LAS EXISTENCIAS 
//		        	 c= componenteDao.consult(d.getFolio_componente());
//		        	 Integer ex= c.getD_cantidad()-d.getNumero_piezas();
//		        	 c.setD_cantidad(ex);
//		        	 componenteDao.save(c);
		        	 discrepanciaDao.save(d);
		        	 // aqui se generan las horas hombre para cada discrepancia que se cree
		        	 creaHorasHombre(d.getId());
		        
		        	 response.getWriter().println(JsonConvertidor.toJson(d));
		        } catch (RuntimeException ignored) {
		        	ignored.printStackTrace();
		            // getUniqueEntity should throw exception
		        }
		    	//  response.getWriter().println(JsonConvertidor.toJson(d));
		       
		    }
		 
		   /////////////////////////////////////////////////////////////////////////////////////////**********************
		   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
			public void findAllDiscre(HttpServletResponse response, HttpServletRequest request) throws IOException {
				AsignadorDeCharset.asignar(request, response);
				List<DiscrepanciaEntity> lista = discrepanciaDao.getAll();
				if (lista == null) {
					lista = new ArrayList<DiscrepanciaEntity>();
				}
				response.getWriter().println(JsonConvertidor.toJson(lista));

			}
		   
		   @RequestMapping(value = {"/delete/{folio}" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
		   public void deleteOrden(HttpServletResponse response, HttpServletRequest request, @RequestBody String json,
			@PathVariable Long folio) throws IOException {
			   discrepanciaDao.delete(discrepanciaDao.consult(folio));
		   }
		   
		   
		   @RequestMapping(value = { "/getByOrden/{folioOrden}" }, method = RequestMethod.GET, produces = "application/json")
			public void findByOrden(HttpServletResponse response, HttpServletRequest request,
					@PathVariable Long folioOrden) throws IOException {
			   System.out.println("discrennnnnnn");
				AsignadorDeCharset.asignar(request, response);
				List<DiscrepanciaEntity> dis= discrepanciaDao.getByOrden(folioOrden);
				if (dis==null){
					dis= new ArrayList<DiscrepanciaEntity>();
				}
				
				response.getWriter().println(JsonConvertidor.toJson(dis));
				
			}
		   
		   @RequestMapping(value = { "/getAbiertasByOrden/{folioOrden}" }, method = RequestMethod.GET, produces = "application/json")
			public void findAbiertasByOrden(HttpServletResponse response, HttpServletRequest request,
					@PathVariable Long folioOrden) throws IOException {
			   System.out.println("checando discrepancias abiertas");
				AsignadorDeCharset.asignar(request, response);
				List<DiscrepanciaEntity> dis= discrepanciaDao.getAbiertasByOrden(folioOrden);
				if (dis==null){
					dis= new ArrayList<DiscrepanciaEntity>();
				}
				
				response.getWriter().println(JsonConvertidor.toJson(dis));
				
			}
		   
		   @RequestMapping(value = {"/update" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		   public void updateDis(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
			throws IOException {
			   System.out.println("si entra a actualizar discrepancia:");
			   AsignadorDeCharset.asignar(request, response);
			   System.out.println("discrepancia que manda edgar:"+json);
			   DiscrepanciaEntity d = (DiscrepanciaEntity) JsonConvertidor.fromJson(json, DiscrepanciaEntity.class);
//			   List<EventoEntity> eventos = d.getEventos();
//			   System.out.println("eventos"+eventos);				   			  
//			  Contador.reiniciaE();
//				for(EventoEntity e : eventos) {
//					e.setIdEvento(Long.toString(d.getId())+"-"+Contador.getFolioEvento());
//					Contador.incrementaE();
//				    System.out.println("el id de evento es:"+e.getIdEvento());
//				}
//			   
			   ////////////////////////////ojo ver si es necesario hacer un controller para evento....
				System.out.println("objDisc:"+d);
			   discrepanciaDao.update(d);
			  // System.out.println("objDisc:"d);
	        	 generarVales(d.getId());
	        	
			   response.getWriter().println(JsonConvertidor.toJson(discrepanciaDao.consult(d.getId())));
		   }
		   
		   @RequestMapping(value = { "/find/{id}" }, method = RequestMethod.GET, produces = "application/json")
			public void findFolio(HttpServletResponse response, HttpServletRequest request,
					@PathVariable Long id) throws IOException {
			   System.out.println("xxxxxxxxx");
				AsignadorDeCharset.asignar(request, response);
				//DetalleDiscrepanciaVo dd = getDetalleDiscrepancia(id);
				System.out.println("aaaaaaaaaa");
				//System.out.println("find/id"+dd);
				DiscrepanciaEntity d=discrepanciaDao.consult(id);
				response.getWriter().println(JsonConvertidor.toJson(d));
			
			}
		   
		   @RequestMapping(value = { "/findDetalle/{id}" }, method = RequestMethod.POST, produces = "application/json")
			public void findDetalle(HttpServletResponse response, HttpServletRequest request,
					@PathVariable Long id) throws IOException {
			   System.out.println("xxxxxxxxx");
				AsignadorDeCharset.asignar(request, response);
				DetalleDiscrepanciaVo dd = getDetalleDiscrepancia(id);
				System.out.println("aaaaaaaaaa");
				System.out.println("find/id"+dd);
				//DiscrepanciaEntity d=discrepanciaDao.consult(id);
				response.getWriter().println(JsonConvertidor.toJson(dd));
			
			}
		   
		   
		   public DetalleDiscrepanciaVo getDetalleDiscrepancia(Long id){
			   
				DetalleDiscrepanciaVo det = new DetalleDiscrepanciaVo();
				//DiscrepanciaEntity dis = discrepanciaDao.consult(id);
			       System.out.println("estoy en getDetalleDiscrepancia.....idOrden");
			      
			     
			       DiscrepanciaEntity dis = discrepanciaDao.consult(id);
			       OrdenVo orden =ordenDao.consult(dis.getFolioOrden());
			       EmpresaEntity empresa= empresaDao.consult(orden.getEmpresa());
			       AeronaveEntity nave = aeronaveDao.consult(orden.getAeronave());
			       List<EventoEntity> evs= eventoDao.getByDiscrepancia(id);
			       List<ComDisVo> cvos= new ArrayList<ComDisVo>();
			       List<ComponenteDiscrepancia> cds = componenteDiscrepanciaDao.getByDiscrepancia(id);	
			       for (ComponenteDiscrepancia cd : cds){
						ComDisVo cdvo= new ComDisVo();
						System.out.println("objeto:"+cd.getCantidad());
						cdvo.setDescripcion(componenteDao.consult(cd.getIdComponente()).getD_descripcion());
						cdvo.setNombre_componente(componenteDao.consult(cd.getIdComponente()).getD_componente());
						cdvo.setCantidad(cd.getCantidad());
						cdvo.setId(cd.getId());
						cdvo.setNoParte(componenteDao.consult(cd.getIdComponente()).getD_parte());
						cvos.add(cdvo);
					}
			      System.out.println("orden"+orden);
			      System.out.println("empresa"+empresa);
			      System.out.println("nave"+nave);
			      System.out.println("dis"+dis);
			      System.out.println("evs"+evs);
			     //  ox.setAccionesDiscrepancia(acciones);C:/Users/Lenovo/Desktop/OTs/
			      det.setId(id);
			       det.setIdOrden(orden.getId());
			       det.setFolioOrden(orden.getFolio());
			       det.setFechaOrden((orden.getFechaApertura()).substring(0, 10));
			       System.out.println("fechaOrden:"+det.getFechaOrden());
			       det.setNombreEmpresa(empresa.getNombreEmpresa());
			       det.setMatricula(nave.getMatricula());
			       det.setModelo(nave.getModelo());
			       det.setNoSerie(nave.getNumeroSerie());
			       det.setTaller(dis.getTaller());
			       det.setSeccion(dis.getSeccion());
			       det.setDescripcion(dis.getDescripcion());
			       det.setAccion(dis.getAccion());
			       det.setComponentes(cvos);
			       det.setEventos(evs);
			       det.setTelefono(empresa.getTelefono());
			       String nombre="pdf\\Discrepancias\\DIS_"+det.getId()+".pdf";
			       det.setNombreArchivo(nombre.replaceAll("[\n\r]",""));
				return det;	
			   
		   }
		   
		   
		
		   
			  @RequestMapping(value = { "/generaDiscrepanciaPdf/{idDiscrepancia}" }, method = RequestMethod.GET, produces = "application/pdf" )
				public void generaOrden(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idDiscrepancia) throws IOException {
				
				  response.setContentType("Application/Pdf");
				  DetalleDiscrepanciaVo dd = getDetalleDiscrepancia(idDiscrepancia);   
				  
			        File newExcelFile = new File(dd.getNombreArchivo());		 
			        if (!newExcelFile.exists()){
			            try {
			                newExcelFile.createNewFile();
			            } catch (IOException ioe) {
			                System.out.println("(Error al crear el fichero nuevo ......)" + ioe);
			            }
			        }
		
			        System.out.println("empiezo a generar Pdf..." );
			    	GeneraDiscrepanciaPdf generaDiscrepanciaPdf = new GeneraDiscrepanciaPdf(dd,  response.getOutputStream());
				    	System.out.println("nombre de archivo para edgar:"+dd.getNombreArchivo().substring(18) );
				    	//response.getWriter().println((dd.getNombreArchivo().substring(18)));
				    	  response.getOutputStream().flush();
					        response.getOutputStream().close();
				}
		   
		    
			  @RequestMapping(value = {"/cerrarDiscrepancia/{idDiscrepancia}" }, method = RequestMethod.POST)
			   public void cerrarDis(HttpServletResponse response, HttpServletRequest request,@PathVariable Long idDiscrepancia)
				throws IOException {
				   System.out.println("si entra a actualizar discrepancia:");
				   AsignadorDeCharset.asignar(request, response);
				   System.out.println("idDiscrepancia que manda edgar:"+idDiscrepancia);
				   DiscrepanciaEntity d = discrepanciaDao.consult(idDiscrepancia);
				   d.setEstatus("CERRADA");
				   discrepanciaDao.update(d);
				   
				   OrdenVo o = ordenDao.consult(d.getFolioOrden());
				   //checar si hay discrepancias abiertas aun...
				   List <DiscrepanciaEntity> abiertas = discrepanciaDao.getAbiertasByOrden(d.getFolioOrden());
				   if (abiertas.size() == 0){
					   o.setEstatus("CERRADA");
					   ordenDao.update(o);	       	
					   System.out.println("ORDEN CERRADA:"+o.getId());
				   }else{
					   System.out.println("NO se cerro la orden:"+o.getId());
				   }
		        	
				   response.getWriter().println("ok");
			   }
			  
			  
			  
			  public HorasHombre creaHorasHombre(long id){
				  DiscrepanciaEntity dis = discrepanciaDao.consult(id);
				  //OrdenVo orden =ordenDao.consult(dis.getFolioOrden());
				 // EmpresaEntity empresa= empresaDao.consult(orden.getEmpresa());
				  //AeronaveEntity nave = aeronaveDao.consult(orden.getAeronave());
				  
				  HorasHombre hh= new HorasHombre();
				  hh.setIdOrden(dis.getFolioOrden());
				  hh.setIdDiscrepancia(id);
				  hh.setAccion(dis.getAccion());
				  hh.setEstatus("ABIERTA");
				  hh.setTiempoParcial(0);
				  hh.setTiempoTotal(0);
				  horasHombreDao.save(hh);
				  return hh;
				  
			  }
			  
			  public void generarVales(Long idDiscrepancia){
				  //consultar componentes de la discrepancia que no tengan vale
				  System.out.println("************esta en generar vales con esta discrepancia:"+idDiscrepancia);	
				  List<ComponenteDiscrepancia> cds= componenteDiscrepanciaDao.getByDiscrepancia(idDiscrepancia);
					List<ComDisVo> cvos= new ArrayList<ComDisVo>();
					//aqui se guardaran los comps que no tienen vale
					List<ComponenteDiscrepancia> comps= new ArrayList<ComponenteDiscrepancia>();
					if (cds==null){
						cds= new ArrayList<ComponenteDiscrepancia>();
					}
					
					for (ComponenteDiscrepancia cd : cds){
						//ComDisVo cdvo= new ComDisVo();
						
						System.out.println("----------idVale:"+cd.getIdVale());
						if (cd.getIdVale()==null){	
							System.out.println("---si-------");
							//cdvo.setDescripcion(componenteDao.consult(cd.getIdComponente()).getD_descripcion());
							//cdvo.setNombre_componente(componenteDao.consult(cd.getIdComponente()).getD_componente());
							//cdvo.setCantidad(cd.getCantidad());
							//cdvo.setId(cd.getId());
							//cvos.add(cdvo);
							comps.add(cd);
							
						}
					}
				///////////cvos es la lista de los componenteDiscrepancia que no tienen vale
				System.out.println("********tamaño de lista de CompDisVo:"+comps.size());	
				/////creando el objeto para el vale
				if (comps.size()>0){   //si hay componentes sin vale, se emite vale
					ValeEntity v= new ValeEntity();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					String fecha= sdf.format(new Date());
					System.out.println("si va a emitir vale........");
				    v.setFecha(fecha);
				    v.setEstatus("ABIERTO");
				    v.setItems(comps);
				    v.setIdDiscrepancia(idDiscrepancia);
				    valeDao.save(v);
				    System.out.println("VALE EMITIDO:"+v.getId());	
				    //actualizando el idVale en la lista de componentes del vale
				    List<ComponenteDiscrepancia> c = v.getItems(); //ComDisVo
				    for (ComponenteDiscrepancia uno : c){
						uno.setIdVale(v.getId());
						valeDao.update(v);
					}
				    //// añadiendo el id del vale a cada comdis
				    for (ComponenteDiscrepancia cd : comps){
						cd.setIdVale(v.getId());
						componenteDiscrepanciaDao.update(cd);
					}
				    
				}else{
				System.out.println("*******NO SE EMITIO VALE*********:");
				}
			  }
			 
			  
			  
			  
}