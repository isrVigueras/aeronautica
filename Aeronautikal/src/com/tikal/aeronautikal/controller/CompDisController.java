package com.tikal.aeronautikal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.aeronautikal.dao.ComponenteDiscrepanciaDao;
import com.tikal.aeronautikal.service.AeronaveService;

@Controller
@RequestMapping(value="/componenteDiscrepancia")
public class CompDisController {

	  @Autowired
	    private AeronaveService componenteDiscrepanciaService;
	   
	    @Autowired
	    @Qualifier("componenteDiscrepanciaDao")
	    ComponenteDiscrepanciaDao componenteDiscrepanciaDao;
	    
	    @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	    
	    public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
	 	   response.getWriter().println("Prueba del mètodo PROBAR");

	     }
}
