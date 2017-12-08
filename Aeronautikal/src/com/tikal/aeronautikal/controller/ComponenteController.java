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

import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.ComponenteDao;
import com.tikal.aeronautikal.dao.OrdenDao;
import com.tikal.aeronautikal.dao.RequisicionDao;
import com.tikal.aeronautikal.entity.RequisicionEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.service.AeronaveService;
import com.tikal.aeronautikal.service.ComponenteService;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

@Controller
@RequestMapping(value="/componente")
public class ComponenteController {
	
	 @Autowired
	    private ComponenteService componenteService;
	 
	 @Autowired
	 @Qualifier("componenteDao")
	 ComponenteDao componenteDao;
	 
	 @Autowired
	 @Qualifier("requisicionDao")
	 RequisicionDao requisicionDao;
	 
	 @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Componente");

	    }
	 
	 ////////////////////////////////////////////////*****************************************************************
	 
	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addComponenteGet(@ModelAttribute("entry") ComponenteEntity entry) {
		   System.out.println("si entra a Orden controller");   
		   	try {
		   		
		   		entry.setId(Long.parseLong("1119"));
		   		entry.setD_componente("nombre del componente");
		   		entry.setD_descripcion("descripcion del componente");
		   		entry.setD_parte("no. de parte ");
		   		entry.setD_pendientes(3);
		   		entry.setD_cantidad(8);
		   		entry.setD_requisicion("num de requisicion");
		   		entry.setD_vale("numero de vale");
		   		entry.setFechaApertura("01/12/2017");
		  
	            System.out.println("si asign/ valor"+entry);
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("yaaaaa");	    
	        componenteService.save(entry);   //implementa el dao  
	        return "Orden_de_trabajo";
		}
	
	 /////////////////////////////////////////////////////********************************************************

	 
	 @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addComponente(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) throws IOException{
	    	  System.out.println("si entra al add por POST"+json);
	        try {
	        	AsignadorDeCharset.asignar(request, response);
	        	// System.out.println("request......."+request);
	        	// System.out.println("request......."+response);
	        	ComponenteEntity cmp =(ComponenteEntity) JsonConvertidor.fromJson(json, ComponenteEntity.class);
	        	// System.out.println("el nuevo objeto: "+orden );
	        	//pegar el valor de empresa, aeronave y contacato
	        	//orden.setFolio(1111);
	        	componenteDao.save(cmp);	            
	        } catch (RuntimeException ignored) {
	        	ignored.printStackTrace();
	            // getUniqueEntity should throw exception
	        }
	       
	    }
	 
	   /////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllComp(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<ComponenteEntity> lista = componenteDao.getAll();
			if (lista == null) {
				lista = new ArrayList<ComponenteEntity>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	   @RequestMapping(value = {"/delete/{folio}" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	   public void deleteOrden(HttpServletResponse response, HttpServletRequest request, @RequestBody String json,
		@PathVariable Long id) throws IOException {
		   componenteDao.delete(componenteDao.consult(id));
	   }
	   
	   //////////////////////////////////////////////////////////////////////////////////////////*******************
	   //////// update de existencias segun las requisiciones
	   /////////////   //////////id componente, folio de la req
	   
	   @RequestMapping(value = {"/upExistencias/{id}/{folio}" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	   public void updateExistencias(HttpServletResponse response, HttpServletRequest request, @RequestBody String json,
		@PathVariable Long id, @PathVariable Long folio, @PathVariable Integer d_pendientes) throws IOException {
		   System.out.println("wwwwwwwwwww");
		   ComponenteEntity old = componenteDao.consult(id);
		   RequisicionEntity r = requisicionDao.consult(folio);
		   Integer existencias = old.getD_cantidad()+r.getCantidad();
		   Integer pendientes = old.getD_pendientes()-r.getCantidad();
		   System.out.println("EXISTENCIAS:"+existencias);
		   System.out.println("PENDIENTES:"+pendientes);
		   old.setD_cantidad(existencias);
		   old.setD_pendientes(d_pendientes);  
		   componenteDao.update(old);
		 //  componenteDao.updateExistencias(componenteDao.updateExistencias(id,existencias,pendientes));
	   }
	   
	   
	   
	   
}
 