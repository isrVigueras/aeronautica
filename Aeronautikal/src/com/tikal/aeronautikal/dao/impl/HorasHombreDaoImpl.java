package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.HorasHombreDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.HorasHombre;
import com.tikal.aeronautikal.entity.Usuario;


@Service("horasHombreDao")
public class HorasHombreDaoImpl implements HorasHombreDao {

	 public  void save(HorasHombre h) {    	
	        ofy().save().entity(h).now();
	    }

	    
	    public void delete(HorasHombre h) {
	    	 System.out.println("si esta en daoimpl eliminando horas hmbre"+h);
	        ofy().delete().entity(h).now();
	        System.out.println("eliminando...");
	    }

	   @Override
		public void update(HorasHombre h) {
		   System.out.print("HorasHombre"+h.getId());
		   HorasHombre old = this.consult(h.getId());
		System.out.print("old:"+old);
			if (old != null) {
				//old.setClave(h.getClave());
				old.setEstatus(h.getEstatus());
				old.setHoraIncio(h.getHoraIncio());
				old.setTiempoTotal(h.getTiempoTotal());
				old.setIdEmpleado(h.getIdEmpleado());

				//old.setDescripcion(h.getDescripcion());				
			}
			ofy().save().entity(old);
	   }

	    
	   
	   
	   @Override
		public HorasHombre consult(Long id) {
		   System.out.println("si esta en daoimpl consultando la horas hombre.."+id);
	      return ofy().load().type(HorasHombre.class).id(id).now();
	     
//		   List<HorasHombre> hh = ofy().load().type(HorasHombre.class).filter("id", id).list();
//			System.out.println("lista de horas hombre con ese id:"+id);
//			if (hh.size() == 0) {
//				return null;
//			}
//			HorasHombre nuevo = hh.get(0);
//			return nuevo;
			
		}

		@Override
		public  List<HorasHombre> findAll() {
			// TODO Auto-generated method stub
			return ofy().load().type(HorasHombre.class).list();
		}
	   
		public List<HorasHombre> getAll() {
			return ofy().load().type(HorasHombre.class).list();
		}


		@Override
		public List<HorasHombre> getByEmpleado(Long idEmpleado) {
			// TODO Auto-generated method stub
			System.out.println(" consultando la horas hombre por empleado.."+idEmpleado);
		    return ofy().load().type(HorasHombre.class).filter("idEmpleado",idEmpleado).list();
		
		}

		@Override
		public List<HorasHombre> getAsignadas() {
			// TODO Auto-generated method stub
			System.out.println(" consultando todas la horas hombre asignadas..");
		   // return ofy().load().type(HorasHombre.class).filter("estatus","ASIGNADA").list();
		    return ofy().load().type(HorasHombre.class).filter("idEmpleado !=",null).list();
		
		}
		
		@Override
		public List<HorasHombre> getNoAsignadas() {
			// TODO Auto-generated method stub
			System.out.println(" consultando todas la horas hombre asignadas..");
		    return ofy().load().type(HorasHombre.class).filter("estatus","NO ASIGNADA").list();
		
		}
		
		
		@Override
		public List<HorasHombre> getAsignadasByEmpleado(Long idEmpleado) {
			// TODO Auto-generated method stub
			System.out.println(" consultando horas hombre asignadas al empleado.."+idEmpleado);
		    return ofy().load().type(HorasHombre.class).filter("idEmpleado",idEmpleado).list();
		
		}
		
}
