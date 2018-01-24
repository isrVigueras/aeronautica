package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.ComponenteDiscrepanciaDao;
import com.tikal.aeronautikal.entity.ComponenteDiscrepancia;


@Service ("componenteDiscrepanciaDao")
public class ComponenteDiscrepanciaDaoImpl implements ComponenteDiscrepanciaDao {

	
	 public  void save(ComponenteDiscrepancia c) {    	
	        ofy().save().entity(c).now();
	    }

	    
	    public void delete(ComponenteDiscrepancia a) {
	    	 System.out.println("si esta en daoimpl eliminando "+a);
	        ofy().delete().entity(a).now();
	        System.out.println("eliminando...");
	    }

	   @Override
		public void update(ComponenteDiscrepancia c) {

				ofy().save().entity(c);
	   }
	   
	   @Override
		public List<ComponenteDiscrepancia> getByDiscrepancia(Long idDiscrepancia) {
			// TODO Auto-generated method stub
			List<ComponenteDiscrepancia> comps = ofy().load().type(ComponenteDiscrepancia.class).filter("auto","NO")				
					.filter("idDiscrepancia", idDiscrepancia).list(); //.filter("auto", Boolean.FALSE)
			return comps;
		}
	   
		@Override
		public ComponenteDiscrepancia consult(Long id) {
			System.out.println("consultando el comdis " );
	       return ofy().load().type(ComponenteDiscrepancia.class).id(id).now();
			
		}
}
