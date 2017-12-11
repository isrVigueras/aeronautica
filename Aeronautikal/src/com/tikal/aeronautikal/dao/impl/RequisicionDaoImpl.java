package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.RequisicionDao;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.RequisicionEntity;


@Service ("requisicionDao")
public class RequisicionDaoImpl implements RequisicionDao {
	
	public void save(RequisicionEntity r) {   
		System.out.println("si lega aqui");
        ofy().save().entity(r).now();
    }
	public List<RequisicionEntity> getAll() {
		return ofy().load().type(RequisicionEntity.class).list();
	}


	public void findAll() {
	// TODO Auto-generated method stub
	
	}

	@Override
	public void delete(RequisicionEntity r) {
		// TODO Auto-generated method stub@Override
		ofy().delete().entity(r).now();
			//object.setActivo(false);
			//update(c);
		
		
	}


	@Override
	public void update(RequisicionEntity r) {
		// TODO Auto-generated method stub
		RequisicionEntity old = this.consult(r.getFolio());
		if (old != null) {
//			//old.setDireccion(e.getDireccion());
			old.setFolio(r.getFolio());
			old.setD_componente(r.getD_componente());
			old.setCantidad(r.getCantidad());
			old.setFechaApertura(r.getFechaApertura());
			ofy().save().entity(old);
		
		}

		
	}


	@Override
	public RequisicionEntity consult(Long folio) {
		System.out.println("aqui esta consultando la requisicion" );
       return ofy().load().type(RequisicionEntity.class).id(folio).now();
		
	}
	
	@Override
	public List<RequisicionEntity> getByComponente(Long id) {
		// TODO Auto-generated method stub
		List<RequisicionEntity> reqs = ofy().load().type(RequisicionEntity.class).filter("idComponente", id).list();
		return reqs;
	}


}
