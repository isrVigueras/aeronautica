package com.tikal.aeronautikal.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.tikal.aeronautikal.controller.vo.DetalleDiscrepanciaVo;
import com.tikal.aeronautikal.controller.vo.ItemVo;
import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.controller.vo.OrdenXlsVo;
import com.tikal.aeronautikal.controller.vo.ValePdfVo;
import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.dao.ComponenteDao;
import com.tikal.aeronautikal.dao.CondicionDao;
import com.tikal.aeronautikal.dao.DiscrepanciaDao;
import com.tikal.aeronautikal.dao.OrdenDao;
import com.tikal.aeronautikal.dao.UnidadDao;
import com.tikal.aeronautikal.dao.ValeDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.ComponenteDiscrepancia;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.EmpresaEntity;
import com.tikal.aeronautikal.entity.ValeEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.formatos.EditaOrdenXls;
import com.tikal.aeronautikal.formatos.GeneraOrdenPdf;
import com.tikal.aeronautikal.formatos.GeneraValePdf;
import com.tikal.aeronautikal.util.AsignadorDeCharset;
import com.tikal.aeronautikal.util.JsonConvertidor;


@Controller
@RequestMapping(value="/vale")
public class ValeController {

	
	 @Autowired
	 @Qualifier("valeDao")
	 ValeDao valeDao;
	 
	 @Autowired
	 @Qualifier("aeronaveDao")
	 AeronaveDao aeronaveDao;
	 
	 @Autowired
	 @Qualifier("discrepanciaDao")
	 DiscrepanciaDao discrepanciaDao;
	 
	 @Autowired
	 @Qualifier("componenteDao")
	 ComponenteDao componenteDao;
	 
	 @Autowired
	 @Qualifier("ordenDao")
	 OrdenDao ordenDao;
	 
	 @Autowired
	 @Qualifier("condicionDao")
	 CondicionDao condicionDao;
	 
	 @Autowired
	 @Qualifier("unidadDao")
	 UnidadDao unidadDao;
	 
	 @RequestMapping(value={"/prueba"},method = RequestMethod.GET)
	   
	   public void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
		   response.getWriter().println("Prueba del mètodo PROBAR en Vale");

	    }
	 
	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addComponenteGet(@ModelAttribute("entry") ValeEntity entry) {
		   System.out.println("si entra a vale controller");   
		   	try {
		   		
		   		//entry.setIdEvento(Long.parseLong("10023131"));
		   		entry.setEstatus("ABIERTO");
		   		//entry.setFecha((Calendar.DATE));
		   		entry.setIdDiscrepancia(Long.parseLong("5348024557502464"));		  
	            System.out.println("si asign/ valor"+entry);
	        } catch (RuntimeException ignored) {
	            // getUniqueEntity should throw exception
	        }
		   System.out.println("vale planchado guardado");	    
	        valeDao.save(entry);   //implementa el dao  
	        return "evento";
		}
	
	 
	 @RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	   public void addEvento(HttpServletResponse response, HttpServletRequest request, @RequestBody String json) throws IOException{
	    	  System.out.println("si entra al add vale por POST"+json);
	        try {
	        	AsignadorDeCharset.asignar(request, response);
	        	 System.out.println("request......."+request);
	        	 System.out.println("request......."+response);
	        	ValeEntity v =(ValeEntity) JsonConvertidor.fromJson(json, ValeEntity.class);
	        	// System.out.println("el nuevo objeto: "+orden );
	        	//pegar el valor de empresa, aeronave y contacato
	        	//cmp.setD_pendientes(50);//aqui va funcion para calcular cuantas piezas pendientes hay de cada componente
	        	//orden.setFolio(1111);
	        	valeDao.save(v);	            
	        } catch (RuntimeException ignored) {
	        	ignored.printStackTrace();
	            // getUniqueEntity should throw exception
	        }
	       
	    }
	 
	   /////////////////////////////////////////////////////////////////////////////////////////**********************
	   @RequestMapping(value = { "/findAll" }, method = RequestMethod.GET, produces = "application/json")
		public void findAllVales(HttpServletResponse response, HttpServletRequest request) throws IOException {
			AsignadorDeCharset.asignar(request, response);
			List<ValeEntity> lista = valeDao.findAll();
			if (lista == null) {
				lista = new ArrayList<ValeEntity>();
			}
			response.getWriter().println(JsonConvertidor.toJson(lista));

		}
	   
	     
	   
	   
	   @RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.POST)
	   public void deleteEvento(HttpServletResponse response, HttpServletRequest request, @PathVariable Long id)
			   throws IOException {
		   System.out.println("si esta en delete vale"+id);
		   valeDao.delete(valeDao.consult(id));
		   System.out.println("vale eliminado....");
		   response.getWriter().println("ok");
		   
	   }
	   
		   
	   
	   
	   @RequestMapping(value = {"/update" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody String json)
				throws IOException {
			AsignadorDeCharset.asignar(request, response);
			ValeEntity v = (ValeEntity) JsonConvertidor.fromJson(json, ValeEntity.class);
			valeDao.update(v);
			response.getWriter().println(JsonConvertidor.toJson(v));
		}
	    
	   
	   @RequestMapping(value = { "/getByDiscrepancia/{idDiscrepancia}" }, method = RequestMethod.GET, produces = "application/json")
		public void findByDiscrepancia(HttpServletResponse response, HttpServletRequest request,
				@PathVariable Long idDiscrepancia) throws IOException {
		   System.out.println("ya entro a vale controller....");
			AsignadorDeCharset.asignar(request, response);
			List<ValeEntity> vs= valeDao.getByDiscrepancia(idDiscrepancia);
			System.out.println("lista de vales:"+vs);
			if (vs==null){
				vs= new ArrayList<ValeEntity>();
			}		
			response.getWriter().println(JsonConvertidor.toJson(vs));
			
		}

	   
	   @RequestMapping(value = { "/generaValePdf/{idVale}" }, method = RequestMethod.POST)
	 		public void generaVale(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idVale) throws IOException {
		   System.out.println("wwwwwwwwwwww");
	 		  ValePdfVo vpdf = getValePdf(idVale);   
	 	        File newPdfFile = new File(vpdf.getNombreArchivo());		 
	 	        if (!newPdfFile.exists()){
	 	            try {
	 	            	newPdfFile.createNewFile();
	 	            } catch (IOException ioe) {
	 	                System.out.println("(Error al crear el fichero nuevo ......)" + ioe);
	 	            }
	 	        }
	        
	 	  
	 	        System.out.println("Empiezo a generar pdf...." );
	 	    	GeneraValePdf generaValePdf = new GeneraValePdf(vpdf);
	 	    	System.out.println("nombre de archivo para edgar:"+vpdf.getNombreArchivo().substring(10) );
	 	    	response.getWriter().println((vpdf.getNombreArchivo().substring(10)));
	 	    	//generaOrdenPdf.GeneraOrdenPdf(new File(ox.getNombreArchivo()));
	 	    	//generaOrdenPdf.GeneraOrdenPdf(ox));
	 		}
 	  
	   
	   @RequestMapping(value = {"/cerrarVale/{idVale}" }, method = RequestMethod.POST)
		public void update(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idVale)
				throws IOException {
			//System.out.println("obj de edgar:"+json);
			AsignadorDeCharset.asignar(request, response);
		//	AeronaveEntity a = (AeronaveEntity) JsonConvertidor.fromJson(json, AeronaveEntity.class);
			ValeEntity v= valeDao.consult(idVale);
			v.setEstatus("CERRADO");
			valeDao.update(v);
			response.getWriter().println("ok");
		}
       
	  
	     
	   public ValePdfVo getValePdf(Long idVale){
		   ValePdfVo vpdf= new ValePdfVo();
		   
		   ValeEntity vale = valeDao.consult(idVale);
		   List<ItemVo> items= new ArrayList<ItemVo>();		   
		   DiscrepanciaEntity dis = discrepanciaDao.consult(vale.getIdDiscrepancia());
		   OrdenVo orden = ordenDao.consult(dis.getFolioOrden());
		   List<ComponenteDiscrepancia> cds = vale.getItems();
	       AeronaveEntity nave = aeronaveDao.consult(orden.getAeronave());
	       vpdf.setFechaVale(vale.getFecha());
	       vpdf.setNombreArchivo("pdf\\Vales\\vale_"+vale.getId()+".pdf");
	       vpdf.setMatricula(nave.getMatricula());
	       vpdf.setNoSerie(nave.getNumeroSerie());
	       vpdf.setNoOrden((orden.getFolio()).replaceAll("[\n\r]",""));
	       
	       vpdf.setNoDiscrepancia(vale.getIdDiscrepancia());
	       
	       for (ComponenteDiscrepancia cd: cds){
	    	   ItemVo item= new ItemVo();
	    	   ComponenteEntity c= componenteDao.consult(cd.getIdComponente()) ;
	    	   item.setNoParte(c.getD_parte());
	    	   item.setNoSerie(c.getNoSerie());
	    	   item.setDescripcion(c.getD_descripcion());
	    	   item.setCondicion(condicionDao.consult(c.getIdCondicion()).getDescripcion());
	    	   item.setCantidad(cd.getCantidad());
	    	   item.setUnidad(unidadDao.consult(c.getIdUnidad()).getDescripcion());
	    	   items.add(item);
	       }
		   vpdf.setItems(items);
		   
		   return vpdf;
		      
		   
	   }
}
