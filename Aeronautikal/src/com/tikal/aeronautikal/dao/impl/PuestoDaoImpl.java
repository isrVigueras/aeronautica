package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.PuestoDao;
import com.tikal.aeronautikal.entity.Categoria;
import com.tikal.aeronautikal.entity.PuestoEntity;

@Service("puestoDao")
public class PuestoDaoImpl implements PuestoDao{

	 public  void save(PuestoEntity p) {    	
	        ofy().save().entity(p).now();
	    }

	    
	    public void delete(Long id) {
	    	 System.out.println("si esta en daoimpl eliminando"+id);
	    	 PuestoEntity p= this.consult(id);
	        ofy().delete().entity(p).now();
	        System.out.println("eliminando...");
	    }

	   @Override
		public void update(PuestoEntity p) {
		   System.out.print("Categoria:"+p.getId());
		   PuestoEntity old = this.consult(p.getId());
		System.out.print("old:"+old);
			if (old != null) {
				old.setClave(p.getClave());
				old.setDescripcion(p.getDescripcion());				
			}
			ofy().save().entity(old);
	   }

	    
	   @Override
		public PuestoEntity consult(Long id) {
		   System.out.println("si esta en daoimpl consultando el puesto.."+id);
	      return ofy().load().type(PuestoEntity.class).id(id).now();
			
		}

		@Override
		public void findAll(PuestoEntity p) {
			// TODO Auto-generated method stub
			
		}
	   
		public List<PuestoEntity> getAll() {
			return ofy().load().type(PuestoEntity.class).list();
		}

}
