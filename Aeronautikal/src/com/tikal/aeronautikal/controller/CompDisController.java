package com.tikal.aeronautikal.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.tikal.aeronautikal.dao.PerfilDAO;
import com.tikal.aeronautikal.dao.RequisicionDao;
import com.tikal.aeronautikal.dao.SessionDao;
import com.tikal.aeronautikal.dao.UsuarioDao;
import com.tikal.aeronautikal.dao.ValeDao;
import com.tikal.aeronautikal.entity.ComponenteDiscrepancia;
import com.tikal.aeronautikal.entity.RequisicionEntity;
import com.tikal.aeronautikal.entity.ValeEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;

@Controller
@RequestMapping(value="/componenteDiscrepancia")
public class CompDisController {
	   
	    @Autowired
	    @Qualifier("componenteDiscrepanciaDao")
	    ComponenteDiscrepanciaDao componenteDiscrepanciaDao;
	    
	    @Autowired
	    @Qualifier("componenteDao")
	    ComponenteDao componenteDao;
	    
	    @Autowired
	    @Qualifier("requisicionDao")
	    RequisicionDao requisicionDao;
	    
	    @Autowired
	    @Qualifier("valeDao")
	    ValeDao valeDao;
	    
	    @Autowired
		 @Qualifier("sessionDao")
		 SessionDao sessionDao;
		 
		@Autowired
		@Qualifier ("usuarioDao")
		UsuarioDao usuarioDao;

			
		@Autowired
		PerfilDAO perfilDAO; 
	    
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
	    
	    
	    @RequestMapping(value = {"/add/{userName}"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
		   public void addCompDis(HttpServletResponse response, HttpServletRequest request,
				   @RequestBody String json,  @PathVariable String userName) throws IOException{
		       System.out.println("si entra al add por POST"+json);
		       if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 21, sessionDao,userName)){  
			        try {
			        	AsignadorDeCharset.asignar(request, response);
			        	// System.out.println("request......."+request);
			        	// System.out.println("request......."+response);
			        	ComponenteDiscrepancia cd =(ComponenteDiscrepancia) JsonConvertidor.fromJson(json, ComponenteDiscrepancia.class);
			        	String r="0";
			        	///si la cantidad requerida es mayor a las existencias , se genera una requisicion
			        	cd.setCantOriginal(cd.getCantidad());
			        	cd.setAuto("NO");
			        	cd.setNombreComponente((componenteDao.consult(cd.getIdComponente())).getD_componente());
			        	componenteDiscrepanciaDao.save(cd);
			        	////generando requisicion
			        	if (cd.getCantidad()> componenteDao.consult(cd.getIdComponente()).getD_cantidad()){
			        		 System.out.println("cantidad requerida:"+cd.getCantidad());
				        	 System.out.println("cantidad en almacen:"+componenteDao.consult(cd.getIdComponente()).getD_cantidad());
				        	 System.out.println("idComDis:"+cd.getId());
				        	 Integer cantidad= cd.getCantidad()-componenteDao.consult(cd.getIdComponente()).getD_cantidad();
			        		System.out.println("Se generará una requisicion con este numero de piezas:"+(cd.getCantidad()-componenteDao.consult(cd.getIdComponente()).getD_cantidad()));
			        		Long idReq=addRequisicionAutomatica(cd.getIdComponente(),cd.getIdDiscrepancia(), cd.getId(),cantidad);
			        		actualizaPendientes(idReq);
			        		r=cantidad.toString();
			        		// actualiza la cantidad de las piezas del componente menos las que se requisitaron, cantidad original es cantOriginal
			        		
			        		//cd.setCantidad(cd.getCantidad()-componenteDao.consult(cd.getIdComponente()).getD_cantidad());
			        		cd.setCantidad(componenteDao.consult(cd.getIdComponente()).getD_cantidad());
			        		 System.out.println("cantidad nueva de componentes:"+cd.getCantidad());
			        		 componenteDiscrepanciaDao.save(cd);
			        		actualizaExistencias(cd.getIdComponente(),componenteDao.consult(cd.getIdComponente()).getD_cantidad(),"add");
			        	}else{
			        	actualizaExistencias(cd.getIdComponente(),cd.getCantidad(),"add");
			        	}
			        	response.getWriter().println(r);
			        } catch (RuntimeException ignored) {
			        	ignored.printStackTrace();
			            // getUniqueEntity should throw exception
			        }
		       }else{
					response.sendError(403);
				} 
		    }
	    
	    
	    @RequestMapping(value = { "/getByDiscrepancia/{idDiscrepancia}" }, method = RequestMethod.GET, produces = "application/json")
		public void findByOrden(HttpServletResponse response, HttpServletRequest request,
				@PathVariable Long idDiscrepancia) throws IOException {
		   System.out.println("esta en getByDiscrepancia cin idDiscrepancia:"+idDiscrepancia);
			AsignadorDeCharset.asignar(request, response);
			List<ComponenteDiscrepancia> cds= componenteDiscrepanciaDao.getByDiscrepancia(idDiscrepancia);
			List<ComDisVo> cvos= new ArrayList<ComDisVo>();
			if (cds==null){
				cds= new ArrayList<ComponenteDiscrepancia>();
			}
			
			for (ComponenteDiscrepancia cd : cds){
				ComDisVo cdvo= new ComDisVo();
				System.out.println("objeto:"+cd.getCantidad());
				cdvo.setDescripcion(componenteDao.consult(cd.getIdComponente()).getD_descripcion());
				cdvo.setNombre_componente(componenteDao.consult(cd.getIdComponente()).getD_componente());
				cdvo.setCantidad(cd.getCantidad());
				cdvo.setId(cd.getId());
				cvos.add(cdvo);
			}
		System.out.append("lista de CompDisVo:"+cvos);	
			response.getWriter().println(JsonConvertidor.toJson(cvos));
			
		}
	    
		 
	    @RequestMapping(value = {"/delete/{id}/ {userName}" }, method = RequestMethod.POST)
		   public void deleteComDis(HttpServletResponse response, HttpServletRequest request,
				   @PathVariable Long id, @PathVariable String userName) throws IOException {
	    	   System.out.println("si esta en delete"+id);
	    	   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 23, sessionDao,userName)){  
					   actualizaExistencias(componenteDiscrepanciaDao.consult(id).getIdComponente(), componenteDiscrepanciaDao.consult(id).getCantidad(),"delete");
					   ///borrando las requisiciones hechas en esa discrepancia y de ese componente
					   Long idR =requisicionDao.getByComDis(id);
					   System.out.println("idr="+idR);
					   if (idR==  Long.parseLong("0000000000")){
						   System.out.println("no hay requisiciones para ese componente ");
					   }else{
						   RequisicionEntity  r = requisicionDao.consult(idR);
						   requisicionDao.delete(r);
						   System.out.println("Requisicion automatica eliminada ....");
					  
					   }
					   //checando si hay vales de salida ya depachados
					   List<ValeEntity> vales= valeDao.getByDiscrepancia(componenteDiscrepanciaDao.consult(id).getIdDiscrepancia());
					   System.out.println("lista de vales por discrepancia:"+vales);
						for (ValeEntity v : vales){  //vales x discrepancia
							List<ComponenteDiscrepancia> comps= v.getItems(); //componentes x vale
							System.out.println("lista de comps en el vale antes:"+comps);
							System.out.println("componente buscado:"+id);
							if (comps.size()==1){
								System.out.println("se eliminara el vale"+v.getId());
								valeDao.delete(v);
							}else{
								for (ComponenteDiscrepancia c: comps){
									System.out.println("componente buscado:"+id);
									System.out.println("componente:"+c.getId());
									if (c.getId()==id.longValue()){
										comps.remove(c);
										System.out.println("lista de comps en el vale despues:"+comps);							
										v.setItems(comps);
										valeDao.update(v);
										System.out.println("se elimino componente"+c.getId());
										
									}else{
										System.out.println("no es el componente buscado");
									}
								}
							}
							
						}
					   //////////////////////////////////////////////////////////////
						System.out.println("ahora se borrara el comdis...");
					   componenteDiscrepanciaDao.delete(componenteDiscrepanciaDao.consult(id));
					   
					  
					   
					   System.out.println("Componente eliminado de la discrepancia....");
					   response.getWriter().println("ok");
	    	   }else{
					response.sendError(403);
				}
		   }
	    
	    
	    public void actualizaExistencias(Long idComponente, Integer cantidad, String oper){
	    	 System.out.println("ACTUALIZANDO EXISTENCIAS CON ID COMPO:"+idComponente);
	    	 System.out.println("ACTUALIZANDO EXISTENCIAS CON ID cant:"+cantidad);
	    	 System.out.println("ACTUALIZANDO EXISTENCIAS CON ID oper:"+oper);
	    	 
	    	if (oper.equals("add")){
	    		System.out.println("Adddddddddddddddddddd");
	    		ComponenteEntity c =componenteDao.consult(idComponente);
	    		Integer existencias = c.getD_cantidad()-cantidad;
	    		if (existencias<=0 ) existencias=0;
	    		System.out.println("Adddddddddddddddddddd"+existencias);
	    		c.setD_cantidad(existencias);
	    		componenteDao.save(c);
	    	}
	    	if (oper.equals("delete")){
	    		ComponenteEntity c =componenteDao.consult(idComponente);
	    		Integer existencias = c.getD_cantidad()+cantidad;
	    		System.out.println("DELETEeeeeeeeeeeeeeee"+existencias);
	    		c.setD_cantidad(existencias);
	    		componenteDao.save(c);
	    		
	    	}
	    	
	    }
	    	
	    	
	    public Long addRequisicionAutomatica(Long idComponente, Long idDiscrepancia, Long idComDis ,Integer cantidad) { 
	    	  System.out.println("si entraa agregar requisicion automatica :");     	
	        
	        	RequisicionEntity r = new RequisicionEntity();
	        	//cd.setId(Long.parseLong("12121212"));
	        	r.setEstatus("ABIERTA");
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String fecha= sdf.format(new Date());
	        	r.setFechaApertura(fecha);
	        	r.setFolio_componente(idComponente);
	        	r.setFolio_discrepancia(idDiscrepancia);
	        	r.setNumero_piezas(cantidad);
	        	r.setIdComDis(idComDis);
	        	requisicionDao.save(r);
	        
	        	System.out.println("Requisicion expedida..........");
	        	
	        	return r.getFolio();
	    }
	    
	    public void actualizaPendientes(Long folioRequisicion){
			   
			   RequisicionEntity req = requisicionDao.consult(folioRequisicion);
			   System.out.println("REQUISICION :"+req);
			   ComponenteEntity com = componenteDao.consult(req.getFolio_componente());
			   System.out.println("componente:"+com);
			   Integer pendientes= com.getD_pendientes()+req.getNumero_piezas();
			   System.out.println("pendientes:"+pendientes);
			   com.setD_pendientes(pendientes);
			   componenteDao.save(com);
			 
		   }
	   
}
