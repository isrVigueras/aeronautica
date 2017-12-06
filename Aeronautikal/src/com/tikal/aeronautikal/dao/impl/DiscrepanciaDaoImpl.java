package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.DiscrepanciaDao;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;


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
		DiscrepanciaEntity old = this.consult(d.getFolio());
		if (old != null) {
//			//old.setDireccion(e.getDireccion());
		    old.setAccion(d.getAccion());
		    old.setDescripcion(d.getDescripcion());
			old.setFechaApertura(d.getFechaApertura());
			old.setInstaladoPor(d.getInstaladoPor());
			old.setOriginadoPor(d.getOriginadoPor());
			old.setRemovidoPor(d.getRemovidoPor());
			old.setSeccion(d.getSeccion());
			old.setTaller(d.getTaller());			
			ofy().save().entity(old);
		
		}

		 
	}


	@Override
	public DiscrepanciaEntity consult(Long folio) {
		System.out.println("aqui esta consultando la entidad que va a borrar" );
       return ofy().load().type(DiscrepanciaEntity.class).id(folio).now();
		
	}
	
	@Override
	public List<DiscrepanciaEntity> getByOrden(Long folio) {
		// TODO Auto-generated method stub
		List<DiscrepanciaEntity> dis = ofy().load().type(DiscrepanciaEntity.class).filter("folioOrden", folio).list();
		return dis;
	}

	

}
