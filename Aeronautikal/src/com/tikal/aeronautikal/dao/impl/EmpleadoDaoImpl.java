package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.EmpleadoDao;
import com.tikal.aeronautikal.entity.EmpleadoEntity;

@Service ("empleadoDao")
public class EmpleadoDaoImpl implements EmpleadoDao{
	
	 public  void save(EmpleadoEntity e) {    	
	        ofy().save().entity(e).now();
	    }

	    
	    public void delete(EmpleadoEntity e) {
	    	 System.out.println("si esta en daoimpl eliminando empleado"+e);
	        ofy().delete().entity(e).now();
	        System.out.println("eliminando...");
	    }

	   @Override
		public void update(EmpleadoEntity e) {
		   System.out.print("empleado:"+e.getId());
		   System.out.print("empleado:"+e.getApellidoMaterno());
		   EmpleadoEntity old = this.consult(e.getId());
		   System.out.print("old:"+old);
			if (old != null) {
				 System.out.print("ejsfffss");
				old.setApellidoMaterno(e.getApellidoPaterno());
				old.setApellidoPaterno(e.getApellidoPaterno());
				old.setIdPuesto(e.getIdPuesto());
				old.setNombre(e.getNombre());
				System.out.print("old.get apmater"+old.getApellidoMaterno());
			}
			ofy().save().entity(old).now();
			
				//ofy().save().entity(e);
	   }

	    
	   @Override
		public EmpleadoEntity consult(Long id) {
		   System.out.println("si esta en daoimpl consultando el empleado.."+id);
	      return ofy().load().type(EmpleadoEntity.class).id(id).now();
			
		}

		@Override
		public void findAll(EmpleadoEntity e) {
			// TODO Auto-generated method stub
			
		}
	   
		@Override
		public List<EmpleadoEntity> getAll(){
			return ofy().load().type(EmpleadoEntity.class).list();
		}


		


	

}
