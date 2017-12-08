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


import com.tikal.aeronautikal.dao.DiscrepanciaDao;
import com.tikal.aeronautikal.entity.Contador;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
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
		 
		 
		 
		 @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
		   
		   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
			   response.getWriter().println("Prueba del mètodo PROBAR en Discrepancias");

		    }
		 
		 ////////////////////////////////////////////////*****************************************************************
		 
		 @RequestMapping(value = "/add_", method = RequestMethod.GET)
		    public String addDiscrepanciaGet(@ModelAttribute("entry") DiscrepanciaEntity entry) {
			   System.out.println("si entra a Discrepancia controller");   
			   	try {
			   	
			   		entry.setFolio(Long.parseLong("55555555"));
			   		entry.setAccion("reparar electricidad de....");
			   		entry.setDescripcion("123_descripcion del componente");
			   		entry.setFolioOrden(Long.parseLong("1111"));
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

		 
		 @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
		   public void addDiscrepancia(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)		
				   throws IOException{
		    	  System.out.println("si entra al add con el folio de orden :"+Contador.getFolioDiscrepancia()+"el json: "+json);
		        try {
		        	System.out.println("++++++++");
		        	AsignadorDeCharset.asignar(request, response);
		        	 System.out.println("request......."+request);
		        	 System.out.println("response......."+response);
		        	DiscrepanciaEntity d =(DiscrepanciaEntity) JsonConvertidor.fromJson(json, DiscrepanciaEntity.class);
		        	 System.out.println("el nuevo objeto: "+d );
		        	//pegar el valor de empresa, aeronave y contacato
		        	d.setFolioOrden(Contador.getFolioDiscrepancia());
		        	d.setFolio(Long.valueOf((d.getTaller()+d.getSeccion()+Long.toString(Contador.getFolioDiscrepancia()))).longValue());
		        	 System.out.println("el nuevo folio de discrepancia: "+ Long.valueOf((Long.toString(Contador.getFolioDiscrepancia())+d.getTaller()+d.getSeccion())).longValue());
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
		   
		   
		   @RequestMapping(value = { "/getByOrden/{folio}" }, method = RequestMethod.GET, produces = "application/json")
			public void findByOrden(HttpServletResponse response, HttpServletRequest request,
					@PathVariable Long folio) throws IOException {
				AsignadorDeCharset.asignar(request, response);
				Contador.setFolioDiscrepancia(folio);
				System.out.println("el nuevo folio : de Contador : "+Contador.getFolioDiscrepancia());
				List<DiscrepanciaEntity> dis= discrepanciaDao.getByOrden(folio);
				response.getWriter().println(JsonConvertidor.toJson(dis));
				
			}
		   
		   @RequestMapping(value = {"/update" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		   public void updateEmpresa(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
			throws IOException {
			   AsignadorDeCharset.asignar(request, response);
			   DiscrepanciaEntity d = (DiscrepanciaEntity) JsonConvertidor.fromJson(json, DiscrepanciaEntity.class);
			   discrepanciaDao.update(d);
			   response.getWriter().println(JsonConvertidor.toJson(discrepanciaDao.consult(d.getFolio())));
		   }
		   
		   
}