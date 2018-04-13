package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Iterator;
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
		return ofy().load().type(RequisicionEntity.class).filter("estatus", "ABIERTA").list();
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

			ofy().save().entity(r);
		
		}

		
	


	@Override
	public RequisicionEntity consult(Long folio) {
		System.out.println("aqui esta consultando la requisicion" );
       return ofy().load().type(RequisicionEntity.class).id(folio).now();
		
	}
	
	
	@Override
	public Long getByComDis(Long idComDis) {
		System.out.println("aqui esta consultando la requisicion por ComDis" );
		RequisicionEntity r = ofy().load().type(RequisicionEntity.class).filter("idComDis", idComDis).first().now();
		System.out.println("req para borrar" +r);		
		if (ofy().load().type(RequisicionEntity.class).filter("idComDis", idComDis).first().now() == null){
			Long id=Long.parseLong("0000000000");
			return id;
		}
       return r.getFolio();
		
	}
	
	@Override
	public List<RequisicionEntity> getByComponente(Long id) {
		// TODO Auto-generated method stub
		List<RequisicionEntity> reqs = ofy().load().type(RequisicionEntity.class).filter("folio_componente", id).filter("estatus", "ABIERTA").list();
		return reqs;
	}
	@Override
	public Integer getPendientes(Long idComponente) {
		// TODO Auto-generated method stub
		Integer suma=0;
		List<RequisicionEntity> reqs = ofy().load().type(RequisicionEntity.class).filter("folio_componente", idComponente).filter("estatus","ABIERTA").list();		
		for(RequisicionEntity r : reqs) {
            suma= suma+r.getNumero_piezas();
        }
		return suma;
		
	}


}
