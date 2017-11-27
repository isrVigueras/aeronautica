package com.tikal.aeronautikal.controller;

	import java.io.IOException;

	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;

	import com.tikal.aeronautikal.entity.EmpresaEntity;
	import com.tikal.aeronautikal.service.EmpresaService;

	
	@Controller
	@RequestMapping(value="/empresa")

	public class EmpresaController {
		
			@Autowired
		   
		    private EmpresaService empresaService;   
		   
		   
		   @RequestMapping(value = "/add", method = RequestMethod.GET)
		    public String addEmpresaGet(@ModelAttribute("entry") EmpresaEntity entry) {
			   System.out.println("si entra aqui 1");
			   try {
		        
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
		    

		   
		   
		   
	}
