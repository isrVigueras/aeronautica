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

import com.tikal.aeronautikal.controller.vo.ComDisVo;
import com.tikal.aeronautikal.dao.ComponenteDao;
import com.tikal.aeronautikal.dao.ComponenteDiscrepanciaDao;
import com.tikal.aeronautikal.dao.RequisicionDao;
import com.tikal.aeronautikal.entity.ComponenteDiscrepancia;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.EmpresaEntity;
import com.tikal.aeronautikal.entity.EventoEntity;
import com.tikal.aeronautikal.entity.RequisicionEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.service.AeronaveService;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

@Controller
@RequestMapping(value="/componenteDiscrepancia")
public class CompDisController {

	  @Autowired
	    private AeronaveService componenteDiscrepanciaService;
	   
	    @Autowired
	    @Qualifier("componenteDiscrepanciaDao")
	    ComponenteDiscrepanciaDao componenteDiscrepanciaDao;
	    
	    @Autowired
	    @Qualifier("componenteDao")
	    ComponenteDao componenteDao;
	    
	    @Autowired
	    @Qualifier("requisicionDao")
	    RequisicionDao requisicionDao;
	    
	    @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	    
	    public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
	 	   response.getWriter().println("Prueba del mètodo PROBAR");

	     }
	    
	    
	    @RequestMapping(value = "/add", method = RequestMethod.GET)
	   // public String addEmpresaGet(@ModelAttribute("entry") EmpresaEntity entry) { 
		   public void addComponente(@ModelAttribute("cd") ComponenteDiscrepancia cd) { 
		    	  System.out.println("si entra al add con el folio de orden :");
		       
		        	System.out.println("++++++++");
		        
		        	//ComponenteDiscrepancia cd =(ComponenteDiscrepancia) JsonConvertidor.fromJson(json, ComponenteDiscrepancia.class);
		        	cd.setId(Long.parseLong("12121212"));
		        	cd.setIdComponente(Long.parseLong("6333186975989760"));
		        	cd.setIdDiscrepancia(Long.parseLong("5154510511013888"));
		        	cd.setCantidad(2);
		        	
		        	 componenteDiscrepanciaDao.save(cd);
		        	 
		      
		    }
	    
	    
	    @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
		   public void addCompDis(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) 
				   throws IOException{
		    	  System.out.println("si entra al add por POST"+json);
		        try {
		        	AsignadorDeCharset.asignar(request, response);
		        	// System.out.println("request......."+request);
		        	// System.out.println("request......."+response);
		        	ComponenteDiscrepancia cd =(ComponenteDiscrepancia) JsonConvertidor.fromJson(json, ComponenteDiscrepancia.class);
		        	
		        	if (cd.getCantidad()> componenteDao.consult(cd.getIdComponente()).getD_cantidad()){
		        		 System.out.println("cantidad requerida:"+cd.getCantidad());
			        	 System.out.println("cantidad en almacen:"+componenteDao.consult(cd.getIdComponente()).getD_cantidad());
			        	 Integer cantidad= cd.getCantidad()-componenteDao.consult(cd.getIdComponente()).getD_cantidad();
		        		System.out.println("Se generará una requisicion con este numero de piezas:"+(cd.getCantidad()-componenteDao.consult(cd.getIdComponente()).getD_cantidad()));
		        		addRequisicionAutomatica(cd.getIdComponente(),cd.getIdDiscrepancia(), cantidad);
		        		String var="REQ";
		        	}
		        	// System.out.println("el nuevo objeto: "+orden );
		        	//pegar el valor de empresa, aeronave y contacato
		        	//orden.setFolio(1111);
		        	componenteDiscrepanciaDao.save(cd);	       
		        	actualizaExistencias(cd.getIdComponente(),cd.getCantidad(),"add");
		        	response.getWriter().println(2);
		        } catch (RuntimeException ignored) {
		        	ignored.printStackTrace();
		            // getUniqueEntity should throw exception
		        }
		       
		    }
	    
	    
	    @RequestMapping(value = { "/getByDiscrepancia/{idDiscrepancia}" }, method = RequestMethod.GET, produces = "application/json")
		public void findByOrden(HttpServletResponse response, HttpServletRequest request,
				@PathVariable Long idDiscrepancia) throws IOException {
		   System.out.println("esta en getByDiscrepancia cin idDiscrepancia:"+idDiscrepancia);
			AsignadorDeCharset.asignar(request, response);
			List<ComponenteDiscrepancia> cds= componenteDiscrepanciaDao.getByDiscrepancia(idDiscrepancia);
			List<ComDisVo> cvos= new ArrayList<ComDisVo>();
			if (cds==null){
				cds= new ArrayList<ComponenteDiscrepancia>();
			}
			
			for (ComponenteDiscrepancia cd : cds){
				ComDisVo cdvo= new ComDisVo();
				System.out.println("objeto:"+cd.getCantidad());
				cdvo.setDescripcion(componenteDao.consult(cd.getIdComponente()).getD_descripcion());
				cdvo.setNombre_componente(componenteDao.consult(cd.getIdComponente()).getD_componente());
				cdvo.setCantidad(cd.getCantidad());
				cdvo.setId(cd.getId());
				cvos.add(cdvo);
			}
		System.out.append("lista de CompDisVo:"+cvos);	
			response.getWriter().println(JsonConvertidor.toJson(cvos));
			
		}
	    
		 
	    @RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.POST)
		   public void deleteEvento(HttpServletResponse response, HttpServletRequest request,@PathVariable Long id) 
				   throws IOException {
	    	   System.out.println("si esta en delete"+id);
			   actualizaExistencias(componenteDiscrepanciaDao.consult(id).getIdComponente(), componenteDiscrepanciaDao.consult(id).getCantidad(),"delete");
			   componenteDiscrepanciaDao.delete(componenteDiscrepanciaDao.consult(id));
			   System.out.println("Componente eliminado de la discrepancia....");
			   response.getWriter().println("ok");
		   }
	    
	    
	    public void actualizaExistencias(Long idComponente, Integer cantidad, String oper){
	    	 System.out.println("ACTUALIZANDO EXISTENCIAS CON ID COMPO:"+idComponente);
	    	 System.out.println("ACTUALIZANDO EXISTENCIAS CON ID cant:"+cantidad);
	    	 System.out.println("ACTUALIZANDO EXISTENCIAS CON ID oper:"+oper);
	    	 
	    	if (oper.equals("add")){
	    		System.out.println("Adddddddddddddddddddd");
	    		ComponenteEntity c =componenteDao.consult(idComponente);
	    		Integer existencias = c.getD_cantidad()-cantidad;
	    		System.out.println("Adddddddddddddddddddd"+existencias);
	    		c.setD_cantidad(existencias);
	    		componenteDao.save(c);
	    	}
	    	if (oper.equals("delete")){
	    		ComponenteEntity c =componenteDao.consult(idComponente);
	    		Integer existencias = c.getD_cantidad()+cantidad;
	    		System.out.println("DELETEeeeeeeeeeeeeeee"+existencias);
	    		c.setD_cantidad(existencias);
	    		componenteDao.save(c);
	    		
	    	}
	    	
	    }
	    	
	    	
	    public void addRequisicionAutomatica(Long idComponente, Long idDiscrepancia, Integer cantidad) { 
	    	  System.out.println("si entraa agregar requisicion automatica :");     	
	        
	        	RequisicionEntity r = new RequisicionEntity();
	        	//cd.setId(Long.parseLong("12121212"));
	        	r.setEstatus("ABIERTA");
	        	r.setFechaApertura(" 999");
	        	r.setFolio_componente(idComponente);
	        	r.setFolio_discrepancia(idDiscrepancia);
	        	r.setNumero_piezas(cantidad);
	        	
	        	requisicionDao.save(r);
	        	System.out.println("Requisicion expedida..........");
	        	
	      
	    }
	    
	    
	   
}
