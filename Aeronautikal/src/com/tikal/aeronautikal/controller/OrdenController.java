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
	
	   
	   @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addOrdenGet(@ModelAttribute("entry") OrdenVo entry) {
		   System.out.println("si entra a Orden controller");   
		   	try {
		   		entry.setFolio(Long.parseLong("99999"));
		   		
		   		entry.setA_matricula("LWA-123456");
		   		entry.setA_t_aterrizaje("23 minutos");
		   		entry.setA_t_vuelo(2342);
		   		entry.setCon_correo("a.e@mail.com");
		   		entry.setCon_nombre("Fulanito de tal");
		   		entry.setEmpresa("volaris");
		   		entry.setCon_telefono(Long.parseLong("300000012"));
		   		entry.setModelo("Super 16089");
		   		entry.setN_serie("aaa-111-ww09");
	           // entry.setCondiciones("todas las condiciones que deseen");
				entry.setFechaApertura("01-12-2017");
				Contador.getFolio();
				 System.out.println("Contador: "+Contador.getFolio());
				//entry.setFolio(Long.parseLong(crearListaIdsOT()));
				entry.setFolio(Contador.getFolio());
			
				
			
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
	        	orden.setFolio(Long.parseLong("1111"));
	        	//crearListaIdsOT();
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
		   response.getWriter().println(JsonConvertidor.toJson(Contador.getFolio()));

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
		@PathVariable Long folio) throws IOException {
		   System.out.println("ya entro a delete");
		   ordenDao.delete(ordenDao.consult(folio));
	   }
	   
	   @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Orden de trabajo");

	    }
	   
	 
	   
	  
}

	    
