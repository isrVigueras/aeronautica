package com.tikal.aeronautikal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.tikal.aeronautikal.controller.vo.DetalleDiscrepanciaVo;
import com.tikal.aeronautikal.controller.vo.DetalleOrdenVo;
import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.dao.ComponenteDao;
import com.tikal.aeronautikal.dao.DiscrepanciaDao;
import com.tikal.aeronautikal.dao.EmpresaDao;
import com.tikal.aeronautikal.dao.OrdenDao;
import com.tikal.aeronautikal.dao.RequisicionDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.Contador;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.EmpresaEntity;
import com.tikal.aeronautikal.entity.EventoEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
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
			   		EventoEntity evento1 = new EventoEntity();
			   //		evento1.setIdEvento(("2121212121"));
			   		evento1.setNombreEvento("evento1");
			   		evento1.setDuracion(2);
			   		evento1.setCosto(20);
			   		
			   		List<EventoEntity> eventos = new ArrayList<EventoEntity>();
			   		eventos.add(evento1);
			   		entry.setEventos(eventos);
			   	  System.out.println("eventos:"+eventos);   
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
		        	List<EventoEntity> eventos = new ArrayList<EventoEntity>();
		        	d.setEventos(eventos);
		        	 System.out.println("el folio de la discre: "+d.getFolio());
		        	//pegar el valor de empresa, aeronave y contacato
		        	d.setFolioOrden(folio);
		        	System.out.println("el folio de la orden es--.: "+d.getFolioOrden() );
		        	d.setFolio((d.getTaller()+d.getSeccion()+"-"+ordenDao.consult(folio).getFolio()));
		        	 System.out.println("el nuevo folio de discrepancia: "+ d.getFolio());
		        	 ComponenteEntity c = new ComponenteEntity();
		        	 
		        	 c= componenteDao.consult(d.getFolio_componente());
		        	 Integer ex= c.getD_cantidad()-d.getNumero_piezas();
		        	 c.setD_cantidad(ex);
		        	 componenteDao.save(c);
		        	 discrepanciaDao.save(d);
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
		   
		   @RequestMapping(value = {"/update" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		   public void updateEmpresa(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
			throws IOException {
			   System.out.println("si entra a actualizar discrepancia:");
			   AsignadorDeCharset.asignar(request, response);
			   System.out.println("discrepancia que manda edgar:"+json);
			   DiscrepanciaEntity d = (DiscrepanciaEntity) JsonConvertidor.fromJson(json, DiscrepanciaEntity.class);
			   List<EventoEntity> eventos = d.getEventos();
			   System.out.println("eventos"+eventos);
			   List<OrdenVo> nacs= new ArrayList<OrdenVo>();
			   Contador.reiniciaE();
				for(EventoEntity e : eventos) {
					e.setIdEvento(Long.toString(d.getId())+"-"+Contador.getFolioEvento());
					Contador.incrementaE();
					 System.out.println("el id de evento es:"+e.getIdEvento());
					// System.out.println("miliegundo:"+Calendar.MILLISECOND);
				}
			   ////////////////////////////ojo ver si es necesario hacer un controller para evento....
				System.out.println("objDisc:"+d);
			   discrepanciaDao.update(d);
			  // System.out.println("objDisc:"d);
			   
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
		   
		   @RequestMapping(value = { "/findDetalle/{id}" }, method = RequestMethod.GET, produces = "application/json")
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
		   
		   
//		   @RequestMapping(value={"/getFolioEvento/{idDiscrepancia}"},method = RequestMethod.GET)		   
//		   public void getFolioEvento(HttpServletResponse response, HttpServletRequest request,@PathVariable Long idDiscrepancia) throws IOException {
//			  // response.getWriter().println("Prueba del mètodo PROBAR en Orden de trabajo");
//			   Calendar c = Calendar.getInstance();		  
//			   String folio =  (Integer.toString(c.get(Calendar.MILLISECOND))+"-"+"idDiscrepancia");
//			   System.out.println("folio :"+folio);
//			  // return folio;
//			  response.getWriter().println((folio));
//
//		    }
//		   
		   public DetalleDiscrepanciaVo getDetalleDiscrepancia(Long id){
			   
				DetalleDiscrepanciaVo det = new DetalleDiscrepanciaVo();
				//DiscrepanciaEntity dis = discrepanciaDao.consult(id);
			       System.out.println("estoy en getDetalleDiscrepancia.....idOrden");
			      
			     
			       DiscrepanciaEntity dis = discrepanciaDao.consult(id);
			       OrdenVo orden =ordenDao.consult(dis.getFolioOrden());
			       EmpresaEntity empresa= empresaDao.consult(orden.getEmpresa());
			       AeronaveEntity nave = aeronaveDao.consult(orden.getAeronave());
			       
			      System.out.println("orden"+orden);
			      System.out.println("empresa"+empresa);
			      System.out.println("nave"+nave);
			      System.out.println("dis"+dis);
			     //  ox.setAccionesDiscrepancia(acciones);C:/Users/Lenovo/Desktop/OTs/
			       det.setIdOrden(orden.getId());
			       det.setFolioOrden(orden.getFolio());
			       det.setFechaOrden(orden.getFechaApertura());
			       det.setNombreEmpresa(empresa.getNombreEmpresa());
			       det.setMatricula(nave.getMatricula());
			       det.setModelo(nave.getModelo());
			       det.setNoSerie(nave.getNumeroSerie());
			       det.setTaller(dis.getTaller());
			       det.setSeccion(dis.getSeccion());
			       det.setDescripcion(dis.getDescripcion());
			       det.setAccion(dis.getAccion());
			       det.setComponentes(getComponente(id));
			       det.setEventos(dis.getEventos());
			       det.setTelefono(empresa.getTelefono());
			              
				return det;	
			   
		   }
		   
		   public List<ComponenteEntity> getComponente(Long idDis){
			   	ComponenteEntity comp = componenteDao.consult(discrepanciaDao.consult(idDis).getFolio_componente());
			   	// faalta la busqueda de todos los componentes de una discrepancia y meterlos a comps
				  List<ComponenteEntity> comps = new ArrayList<ComponenteEntity>();
				  
				  //hacer el for para mandarlos a la lista de comps
			      comps.add(comp);
			    	 	    
				return comps;
				  
			  }
		   
		
		   
		   
		   
}