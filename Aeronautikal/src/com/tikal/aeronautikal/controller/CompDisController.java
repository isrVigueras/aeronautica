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
import com.tikal.aeronautikal.entity.ComponenteDiscrepancia;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.EmpresaEntity;
import com.tikal.aeronautikal.entity.EventoEntity;
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
		        	// System.out.println("el nuevo objeto: "+orden );
		        	//pegar el valor de empresa, aeronave y contacato
		        	//orden.setFolio(1111);
		        	componenteDiscrepanciaDao.save(cd);	            
		        } catch (RuntimeException ignored) {
		        	ignored.printStackTrace();
		            // getUniqueEntity should throw exception
		        }
		       
		    }
	    
	    
	    @RequestMapping(value = { "/getByDiscrepancia/{idDiscrepancia}" }, method = RequestMethod.GET, produces = "application/json")
		public void findByOrden(HttpServletResponse response, HttpServletRequest request,
				@PathVariable Long idDiscrepancia) throws IOException {
		   System.out.println("discrennnnnnn");
			AsignadorDeCharset.asignar(request, response);
			List<ComponenteDiscrepancia> cds= componenteDiscrepanciaDao.getByDiscrepancia(idDiscrepancia);
			List<ComDisVo> cvos= new ArrayList<ComDisVo>();
			if (cds==null){
				cds= new ArrayList<ComponenteDiscrepancia>();
			}
			
			for (ComponenteDiscrepancia cd : cds){
				ComDisVo cdvo= new ComDisVo();
				cdvo.setDescripcion(componenteDao.consult(cd.getIdComponente()).getD_descripcion());
				cdvo.setNombre_componente(componenteDao.consult(cd.getIdComponente()).getD_componente());
				cdvo.setCantidad(cd.getCantidad());
				cvos.add(cdvo);
			}
			
			response.getWriter().println(JsonConvertidor.toJson(cvos));
			
		}
	    
		 
	    @RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
		   public void deleteEvento(HttpServletResponse response, HttpServletRequest request, @RequestBody String json,
			@PathVariable Long id) throws IOException {
			   componenteDiscrepanciaDao.delete(componenteDiscrepanciaDao.consult(id));
		   }
}
