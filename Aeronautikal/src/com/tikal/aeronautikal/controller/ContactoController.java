package com.tikal.aeronautikal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.aeronautikal.entity.ContactoEntity;
import com.tikal.aeronautikal.service.ContactoService;
@Controller
@RequestMapping(value="/contacto")

public class ContactoController {
	
		@Autowired
	   
	    private ContactoService contactoService;   
	   
	   
	   @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addContactoGet(@ModelAttribute("entry") ContactoEntity entry) {
		   System.out.println("si entra aqui 1");
		   try {
	        
	            entry.setNombre("Juan Perez");
	            entry.setCorreoElectronico("juan@perez");
	            entry.setTelefono("722-333-33-33");          
	      
	            System.out.println("si asigna valor a contactos : "+entry);
	          
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("yaaaaa");	    
	        contactoService.save(entry);   //implementa el dao
	        return "addContacto";
		   
	    }
	   
	   @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Contacto");

	    }
	    

}
