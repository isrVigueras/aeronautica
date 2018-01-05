package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.UnidadDao;
import com.tikal.aeronautikal.entity.Unidad;

@Service("unidadDao")
public class UnidadDaoImpl implements UnidadDao{
	
	 public  void save(Unidad u) {    	
	        ofy().save().entity(u).now();
	    }

	    
	    public void delete(Unidad u) {
	    	 System.out.println("si esta en daoimpl eliminando"+u);
	        ofy().delete().entity(u).now();
	        System.out.println("eliminando...");
	    }

	   @Override
		public void update(Unidad u) {
		   System.out.print("unidad:"+u.getId());
		   Unidad old = this.consult(u.getId());
		System.out.print("old:"+old);
			if (old != null) {
				old.setClave(u.getClave());
				old.setDescripcion(u.getDescripcion());
				old.setSigla(u.getSigla());
				
			}
			ofy().save().entity(old);
	   }

	    
	   @Override
		public Unidad consult(Long id) {
		   System.out.println("si esta en daoimpl consultando la unidad.."+id);
	      return ofy().load().type(Unidad.class).id(id).now();
			
		}

		@Override
		public void findAll(Unidad u) {
			// TODO Auto-generated method stub
			
		}
	   
		public List<Unidad> getAll() {
			return ofy().load().type(Unidad.class).list();
		}

	}