package com.tikal.aeronautikal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.tikal.aeronautikal.formatos.*;

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
import com.tikal.aeronautikal.controller.vo.DetalleOrdenVo;
import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.controller.vo.OrdenXlsVo;
import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.dao.DiscrepanciaDao;
import com.tikal.aeronautikal.dao.EmpresaDao;
import com.tikal.aeronautikal.dao.OrdenDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.Contador;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.EmpresaEntity;
//import com.tikal.aeronautikal.entity.OrdenEntity;
import com.tikal.aeronautikal.model.Aeronave;
import com.tikal.aeronautikal.service.OrdenService;
import com.tikal.aeronautikal.util.JsonConvertidor;
import com.tikal.aeronautikal.util.AsignadorDeCharset;

import java.io.File;
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
	@Qualifier("aeronaveDao")
	AeronaveDao aeronaveDao;
	
	@Autowired
	@Qualifier("empresaDao")
	EmpresaDao empresaDao;
	
	@Autowired
	@Qualifier("ordenDao")
	OrdenDao ordenDao;
	
	@Autowired
	@Qualifier("discrepanciaDao")
	DiscrepanciaDao discrepanciaDao;

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
	        	 orden.setNombreEmpresa(empresaDao.consult(orden.getEmpresa()).getNombreEmpresa());
	        	 
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
	   
	   @RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	   public void deleteOrden(HttpServletResponse response, HttpServletRequest request, @RequestBody String json,
		@PathVariable Long id) throws IOException {
		   System.out.println("ya entro a delete");
		   ordenDao.delete(ordenDao.consult(id));
	   }
	   
	   @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Orden de trabajo");

	    }
	   
	 
	   @RequestMapping(value = { "/find/{id}" }, method = RequestMethod.GET, produces = "application/json")
		public void findFolio(HttpServletResponse response, HttpServletRequest request,
				@PathVariable Long id) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			DetalleOrdenVo detO = getDetalleOrden(id);
			//OrdenVo o=ordenDao.consult(id);
			response.getWriter().println(JsonConvertidor.toJson(detO));
		
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
	   
	   @RequestMapping(value = { "/findAllNac" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllOrdenesNac(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<OrdenVo> lista = ordenDao.getAllNac();
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
	   
	  @RequestMapping(value = { "/generaOrdenXls/{idOrden}" }, method = RequestMethod.GET)
		public void generaOrden(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idOrden) throws IOException {
		  EditaOrdenXls eox = new EditaOrdenXls();
	        File newExcelFile = new File("C:/Users/Lenovo/Desktop/OTs/OT__.xls");		 
	        if (!newExcelFile.exists()){
	            try {
	                newExcelFile.createNewFile();
	            } catch (IOException ioe) {
	                System.out.println("(Error al crear el fichero nuevo mmmmmmm)" + ioe);
	            }
	        }
	        ////sacando sopa
	        
	       OrdenXlsVo ox = getObjectXls(idOrden);   
	       
	        String origen ="C:/Users/Lenovo/Desktop/OTs/OrdenDeTrabajo.xls";
	   //     String destino ="C:/Users/Lenovo/Desktop/OTs/O.T."+orden.getFolio()+" "+nave.getMatricula()+".xls";
	       // eox.FileCopy(origen, destino);  
			//EditaOrdenXls.readWriteExcelFile();
	        System.out.println("Empezando a ecribir en el Xls..." );
	        EditaOrdenXls.WriteXls(ox);
	        System.out.println("regrese de escribir en el Xls..." );
		}
	  
	  
	  public OrdenXlsVo getObjectXls(Long idOrden){
		  
		  OrdenXlsVo ox = new OrdenXlsVo();
	       
	       OrdenVo orden =ordenDao.consult(idOrden);
	       EmpresaEntity empresa= empresaDao.consult(orden.getEmpresa());
	       AeronaveEntity nave = aeronaveDao.consult(orden.getIdAeronave());
	      
	     //  ox.setAccionesDiscrepancia(acciones);C:/Users/Lenovo/Desktop/OTs/
	       ox.setNombreArchivo("OT"+orden.getFolio()+"_"+nave.getMatricula()+".xls");
	       System.out.println("xls:."+orden.getFolio());
	       ox.setFechaOrden(orden.getFechaApertura());
	       ox.setNombreEmpresa(empresa.getNombreEmpresa());
	       ox.setFolioOrden(orden.getFolio());
	       ox.setMarcaAeronave(nave.getMarca());
	       ox.setModeloAeronave(nave.getModelo());
	       ox.setNumeroSerie(nave.getNumeroSerie());
	       ox.setMatricula(nave.getMatricula());
	       ox.setPlaneador(nave.getPlaneador());
	       ox.setMotor1(nave.getMotor1());
	       ox.setMotor2(nave.getMotor2());
	       ox.setMarcas(nave.getMarcas());
	       ox.setAccionesDiscrepancia(getAccionesDiscrepancia(idOrden));
	              
		return ox;	
		  
		  
	  }
	  
public DetalleOrdenVo getDetalleOrden(Long idOrden){
		  
			DetalleOrdenVo det = new DetalleOrdenVo();
	       
	       OrdenVo orden =ordenDao.consult(idOrden);
	       EmpresaEntity empresa= empresaDao.consult(orden.getEmpresa());
	       AeronaveEntity nave = aeronaveDao.consult(orden.getIdAeronave());
	      
	     //  ox.setAccionesDiscrepancia(acciones);C:/Users/Lenovo/Desktop/OTs/
	       det.setFolioOrden(orden.getFolio());
	       det.setFecha(orden.getFechaApertura());
	       det.setContacto(empresa.getNombreContacto());
	       det.setCorreo(empresa.getEmail());
	       det.setNombreEmpresa(empresa.getNombreEmpresa());
	       det.setMatricula(nave.getMatricula());
	       det.setModelo(nave.getModelo());
	       det.setNoSerie(nave.getNumeroSerie());
	       det.setTiempoVuelo(Integer.toString(nave.getTiempovuelo()));
	       det.setAterrizaje(nave.getAterrizaje());
	              
		return det;	
		  
		  
	  }
	  
	  public List<String> getAccionesDiscrepancia(Long idOrden){
		  List<DiscrepanciaEntity> dis = discrepanciaDao.getByOrden(idOrden);
		  List<String> acciones = new ArrayList<String>();
	       for(DiscrepanciaEntity d : dis) {	    	 
	    	   String accion =d.getAccion();
	    	   acciones.add(accion);
	       }		    
		return acciones;
		  
	  }
}

	    
