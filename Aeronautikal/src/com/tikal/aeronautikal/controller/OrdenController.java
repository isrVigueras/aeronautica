package com.tikal.aeronautikal.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.OnLoad;
import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.dao.EmpresaDao;
import com.tikal.aeronautikal.dao.OrdenDao;
import com.tikal.aeronautikal.entity.Contador;
import com.tikal.aeronautikal.entity.EmpresaEntity;
//import com.tikal.aeronautikal.entity.OrdenEntity;
import com.tikal.aeronautikal.model.Aeronave;
import com.tikal.aeronautikal.service.OrdenService;
import com.tikal.aeronautikal.util.JsonConvertidor;
import com.tikal.aeronautikal.util.AsignadorDeCharset;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/orden")

public class OrdenController {
	
	
	@Autowired
	@Qualifier("empresaDao")
	EmpresaDao empresaDao;
	
	@Autowired
	@Qualifier("ordenDao")
	OrdenDao ordenDao;

	   @Autowired
	   private OrdenService ordenService;
	
	   
	   @RequestMapping(value = "/add_", method = RequestMethod.GET)
	    public String addOrdenGet(@ModelAttribute("entry") OrdenVo entry) {
		   System.out.println("si entra a Orden controller");   
		   	try {
		   		entry.setFolio("2017-12-1");
		   		entry.setEmpresa(Long.parseLong("1"));
				entry.setFechaApertura("01-12-2017");
				entry.setEmpresa(Long.parseLong("1"));
				entry.setAeronave("1");
				//entry.setDate(Calendar.getInstance().getTime());
	            System.out.println("si asign/ valor"+entry);
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("yaaaaa");	    
	        ordenService.save(entry);   //implementa el dao 
	    	Contador.incremeta();
	        return "Orden_de_trabajo";
		}
	
	   @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addOrden(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) throws IOException{
	    	  System.out.println("si entra al add por POST"+json);
	        try {
	        	AsignadorDeCharset.asignar(request, response);
	        	// System.out.println("request......."+request);
	        	// System.out.println("request......."+response);
	        	OrdenVo orden =(OrdenVo) JsonConvertidor.fromJson(json, OrdenVo.class);
	        	// System.out.println("el nuevo objeto: "+orden );
	        	//pegar el valor de empresa, aeronave y contacato
	        	//orden.setFolio(Long.parseLong("1111"));
	        	//crearListaIdsOT();
	        	 System.out.println("folio orden:"+orden.getFolio());
	        	 System.out.println("folio aeronave:"+orden.getAeronave());
	        	orden.setFolio(orden.getFolio()+orden.getAeronave());
	        	ordenDao.save(orden);	 
	        	Contador.incremeta();
	        } catch (RuntimeException ignored) {
	        	ignored.printStackTrace();
	            // getUniqueEntity should throw exception
	        }
	       
	    }


	   @RequestMapping(value={"/getFolio"},method = RequestMethod.GET)
	   
	   public void getFolio(HttpServletResponse response, HttpServletRequest request) throws IOException {
		  // response.getWriter().println("Prueba del mètodo PROBAR en Orden de trabajo");
		   Calendar c = Calendar.getInstance();		  
		   String folio =  Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH))+"-";
		   System.out.println("folio :"+folio);
		  // return folio;
		  response.getWriter().println((folio));

	    }
	   
	   
	   
	    @RequestMapping(value = { "/getEmpresas" }, method = RequestMethod.GET, produces = "application/json")
		public void getAllEmpresas(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
						
			List<EmpresaEntity> lista = empresaDao.getAllEmpresas();
			if (lista == null) {
				lista = new ArrayList<EmpresaEntity>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));
		}
	   
	   /////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllOrdenes(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<OrdenVo> lista = ordenDao.getAll();
			if (lista == null) {
				lista = new ArrayList<OrdenVo>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	   @RequestMapping(value = {"/delete/{folio}" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	   public void deleteOrden(HttpServletResponse response, HttpServletRequest request, @RequestBody String json,
		@PathVariable String folio) throws IOException {
		   System.out.println("ya entro a delete");
		   ordenDao.delete(ordenDao.consult(folio));
	   }
	   
	   @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Orden de trabajo");

	    }
	   
	 
	   @RequestMapping(value = { "/find/{folio}" }, method = RequestMethod.GET, produces = "application/json")
		public void findFolio(HttpServletResponse response, HttpServletRequest request,
				@PathVariable String folio) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			OrdenVo o=ordenDao.consult(folio);
			response.getWriter().println(JsonConvertidor.toJson(o));
		
		}
	   
	   @RequestMapping(value = { "/findAllN" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllOrdenesN(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<OrdenVo> lista = ordenDao.getAllN();
			if (lista == null) {
				lista = new ArrayList<OrdenVo>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	  public String getFolio(String aeronave){		  
		  Calendar c = Calendar.getInstance();		  
		  String folio =  Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH))+aeronave;
		  return folio;
		  
	  }
	   
	  
	  
}

	    
