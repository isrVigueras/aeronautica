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
		   EmpleadoEntity old = this.consult(e.getId());
		System.out.print("old:"+old);
			if (old != null) {
//				old.setMarca(nave.getMarca());
//				old.setMatricula(nave.getMatricula());
//				old.setModelo(nave.getModelo());
//				old.setNacionalidad(nave.getNacionalidad());
//				old.setNumeroSerie(nave.getNumeroSerie());
//				old.setTiempovuelo(nave.getTiempovuelo());
//				old.setAterrizaje(nave.getAterrizaje());
//				old.setPlaneador(nave.getPlaneador());
//				old.setMotor1(nave.getMotor1());
//				old.setMotor2(nave.getMotor2());
//				old.setMarcas(nave.getMarcas());
				
			}
//				old.setMatricula(nave.getMatricula());
//				old.setModelo(nave.getModelo());
//				old.setTiempovuelo(nave.getTiempovuelo());
//				old.setAterrizaje(nave.getAterrizaje());
				ofy().save().entity(e);
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
