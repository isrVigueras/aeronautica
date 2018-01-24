package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.HorasHombreDao;
import com.tikal.aeronautikal.entity.HorasHombre;


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
				old.setFinParcial(h.getFinParcial());
				old.setHoraFin(h.getHoraFin());
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
			
		}

		@Override
		public void findAll(HorasHombre h) {
			// TODO Auto-generated method stub
			
		}
	   
		public List<HorasHombre> getAll() {
			return ofy().load().type(HorasHombre.class).list();
		}


}
