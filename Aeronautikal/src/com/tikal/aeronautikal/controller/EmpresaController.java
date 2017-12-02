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
	import com.tikal.aeronautikal.dao.EmpresaDao;
	import com.tikal.aeronautikal.entity.EmpresaEntity;
	import com.tikal.aeronautikal.service.EmpresaService;
	import com.tikal.aeronautikal.util.AsignadorDeCharset;
	import com.tikal.aeronautikal.util.JsonConvertidor;

	
	@Controller
	@RequestMapping(value="/empresa")

	public class EmpresaController {
		
			@Autowired
		    private EmpresaService empresaService;   
			
			 @Autowired
			 @Qualifier("empresaDao")
			 EmpresaDao empresaDao;
		   
		   
		   @RequestMapping(value = "/add", method = RequestMethod.GET)
		    public String addEmpresaGet(@ModelAttribute("entry") EmpresaEntity entry) {
			   System.out.println("si entra aqui 1");
			   try {
				   	entry.setId(1);
		            entry.setNickName("SACSA"); 
		      
		            System.out.println("si asigna valor a contactos : "+entry);
		          
		        } catch (RuntimeException ignored) {
		            // getUniqueEntity should throw exception
		        }
			   System.out.println("yaaaaa");	    
		        empresaService.save(entry);   //implementa el dao
		        return "addEmpresa";
			   
		    }
		   
		   @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
		   
		   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
			   response.getWriter().println("Prueba del mètodo PROBAR en Empresa");

		    }
		    

		   @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
		   public void addEmpresa(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) throws IOException{
		    	  System.out.println("si entra al add por POST"+json);
		        try {
		        	AsignadorDeCharset.asignar(request, response);
		        	// System.out.println("request......."+request);
		        	// System.out.println("request......."+response);
		        	EmpresaEntity e =(EmpresaEntity) JsonConvertidor.fromJson(json, EmpresaEntity.class);
		        	// System.out.println("el nuevo objeto: "+orden );
		        	//pegar el valor de empresa, aeronave y contacato
		        	//orden.setFolio(1111);
		        	empresaDao.save(e);	            
		        } catch (RuntimeException ignored) {
		        	ignored.printStackTrace();
		            // getUniqueEntity should throw exception
		        }
		       
		    }
		 
		   /////////////////////////////////////////////////////////////////////////////////////////**********************
		   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
			public void findAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
				AsignadorDeCharset.asignar(request, response);
				List<EmpresaEntity> lista = empresaDao.getAll();
				if (lista == null) {
					lista = new ArrayList<EmpresaEntity>();
				}
				response.getWriter().println(JsonConvertidor.toJson(lista));

			}
		   
		   @RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
		   public void deleteOrden(HttpServletResponse response, HttpServletRequest request, @RequestBody String json,
			@PathVariable Long id) throws IOException {
			   empresaDao.delete(empresaDao.consult(id));
		   }
		   
		   //////////////////////////////////////////////////////////////////////////////////////////*******************
		   
		   
		   
	}
