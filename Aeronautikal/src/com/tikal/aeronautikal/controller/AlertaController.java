package com.tikal.aeronautikal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.aeronautikal.dao.ComponenteDao;
import com.tikal.aeronautikal.dao.RequisicionDao;
import com.tikal.aeronautikal.entity.EmpresaEntity;
import com.tikal.aeronautikal.entity.RequisicionEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

@Controller
@RequestMapping(value="/alerta")


public class AlertaController {

	@Autowired
	@Qualifier("componenteDao")
	ComponenteDao componenteDao;
	
	@Autowired
	@Qualifier("requisicionDao")
	RequisicionDao requisicionDao;
	
	 @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Orden de trabajo");

	    }
	 
	   @RequestMapping(value = { "/findAlertas" }, method = RequestMethod.GET, produces = "application/json")
			public void findComp(HttpServletResponse response, HttpServletRequest request) throws IOException {
				AsignadorDeCharset.asignar(request, response);
			//	List<List<ComponenteEntity>,List<RequisicionEntity>> ldel = new ArrayList<List<ComponenteEntity>,List<RequisicionEntity>>();
				List<ComponenteEntity> lista = componenteDao.getMaxMin();
				//System.out.println("Lista:");
				
				if (lista.size()==0) {
					lista = new ArrayList<ComponenteEntity>();
			
					System.out.println("No hay componentes en alerta");
					//response.getWriter().println(JsonConvertidor.toJson(lista));
				}else{
					System.out.println("Los componentes en alerta son :"+lista);
					//response.getWriter().println(JsonConvertidor.toJson(lista));
				}
				
				///para requisiciones
				
				
				List<RequisicionEntity> reqs = requisicionDao.getAll();
				System.out.println("Reqs:"+reqs);
				
				if (reqs.size()==0) {
					reqs = new ArrayList<RequisicionEntity>();
					System.out.println("No hay requisiciones");
					//response.getWriter().println(JsonConvertidor.toJson(reqs));
				}else{
					System.out.println("Las requisiciones son :"+reqs);
					//response.getWriter().println(JsonConvertidor.toJson(reqs));
				}
				
				response.getWriter().println(JsonConvertidor.toJson(lista));
				response.getWriter().println(JsonConvertidor.toJson(reqs));
				

			}
	   
}
