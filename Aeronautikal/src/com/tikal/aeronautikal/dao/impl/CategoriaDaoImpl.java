package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.CategoriaDao;
import com.tikal.aeronautikal.entity.Categoria;

@Service("categoriaDao")
public class CategoriaDaoImpl implements CategoriaDao {
	
	 public  void save(Categoria c) {    	
	        ofy().save().entity(c).now();
	    }

	    
	    public void delete(Categoria c) {
	    	 System.out.println("si esta en daoimpl eliminando"+c);
	        ofy().delete().entity(c).now();
	        System.out.println("eliminando...");
	    }

	   @Override
		public void update(Categoria c) {
		   System.out.print("Categoria:"+c.getId());
		   Categoria old = this.consult(c.getId());
		System.out.print("old:"+old);
			if (old != null) {
				old.setClave(c.getClave());
				old.setDescripcion(c.getDescripcion());				
			}
			ofy().save().entity(old);
	   }

	    
	   @Override
		public Categoria consult(Long id) {
		   System.out.println("si esta en daoimpl consultando la condicion.."+id);
	      return ofy().load().type(Categoria.class).id(id).now();
			
		}

		@Override
		public void findAll(Categoria c) {
			// TODO Auto-generated method stub
			
		}
	   
		public List<Categoria> getAll() {
			return ofy().load().type(Categoria.class).list();
		}


}