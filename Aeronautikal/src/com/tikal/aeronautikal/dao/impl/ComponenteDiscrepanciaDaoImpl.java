package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

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
}
