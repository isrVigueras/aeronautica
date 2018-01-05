package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.CondicionDao;
import com.tikal.aeronautikal.entity.Condicion;

@Service("condicionDao")
public class CondicionDaoImpl implements CondicionDao{
	
	 public  void save(Condicion c) {    	
	        ofy().save().entity(c).now();
	    }

	    
	    public void delete(Condicion c) {
	    	 System.out.println("si esta en daoimpl eliminando"+c);
	        ofy().delete().entity(c).now();
	        System.out.println("eliminando...");
	    }

	   @Override
		public void update(Condicion c) {
		   System.out.print("condicion:"+c.getId());
		   Condicion old = this.consult(c.getId());
		System.out.print("old:"+old);
			if (old != null) {
				old.setClave(c.getClave());
				old.setDescripcion(c.getDescripcion());				
			}
			ofy().save().entity(old);
	   }

	    
	   @Override
		public Condicion consult(Long id) {
		   System.out.println("si esta en daoimpl consultando la condicion.."+id);
	      return ofy().load().type(Condicion.class).id(id).now();
			
		}

		@Override
		public void findAll(Condicion c) {
			// TODO Auto-generated method stub
			
		}
	   
		public List<Condicion> getAll() {
			return ofy().load().type(Condicion.class).list();
		}


}
