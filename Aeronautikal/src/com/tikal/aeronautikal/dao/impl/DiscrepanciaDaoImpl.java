package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.tikal.aeronautikal.dao.DiscrepanciaDao;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;



@Service ("discrepanciaDao")
public class DiscrepanciaDaoImpl implements DiscrepanciaDao {
	public void save(DiscrepanciaEntity d) {   
		System.out.println("si lega aqui");
        ofy().save().entity(d).now();
    }
	public List<DiscrepanciaEntity> getAll() {
		return ofy().load().type(DiscrepanciaEntity.class).list();
	}


	public void findAll() {
	// TODO Auto-generated method stub
	
	}

	@Override
	public void delete(DiscrepanciaEntity d) {
		// TODO Auto-generated method stub@Override
		ofy().delete().entity(d).now();
			//object.setActivo(false);
			//update(c);
		
		
	}


	@Override
	public void update(DiscrepanciaEntity d) {
		// TODO Auto-generated method stub
		DiscrepanciaEntity old = this.consult(d.getId());
//		if (old.getEventos().isEmpty()) {
//			old.setEventos(d.getEventos());
//		}else{
//		   for(EventoEntity e : d.getEventos()) {
//				old.getEventos().add(e);
//		   }
//		   System.out.println("la lista de eventos nueva es:"+old.getEventos());
//		   d.setEventos(old.getEventos());
//		}
		
		
		ofy().save().entity(d).now();
		 
	}


	@Override
	public DiscrepanciaEntity consult(Long id) {
		System.out.println("aqui esta consultando la discrepancia" );
       return ofy().load().type(DiscrepanciaEntity.class).id(id).now();
		
	}
	
	@Override
	public List<DiscrepanciaEntity> getByOrden(Long folioOrden) {
		// TODO Auto-generated method stub
		List<DiscrepanciaEntity> dis = ofy().load().type(DiscrepanciaEntity.class).filter("folioOrden", folioOrden).list();
		return dis;
	}

	

}
