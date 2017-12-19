package com.tikal.aeronautikal.dao.impl;


import com.googlecode.objectify.cmd.Query;
import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.exception.ObjectNotFoundException;


import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * @author 
 */

@Service("aeronaveDao")

public class AeronaveDaoImpl implements AeronaveDao {

    public  void save(AeronaveEntity a) {    	
        ofy().save().entity(a).now();
    }

    
    public void delete(AeronaveEntity a) {
    	 System.out.println("si esta en daoimpl eliminando"+a);
        ofy().delete().entity(a).now();
        System.out.println("eliminando...");
    }

   @Override
	public void update(AeronaveEntity nave) {
//	AeronaveEntity old = this.consult(nave.getNumeroSerie());
//		if (old != null) {
//			old.setMatricula(nave.getMatricula());
//			old.setModelo(nave.getModelo());
//			old.setTiempovuelo(nave.getTiempovuelo());
//			old.setAterrizaje(nave.getAterrizaje());
			ofy().save().entity(nave);
   }

    
   @Override
	public AeronaveEntity consult(String numeroAeronave) {
	   System.out.println("si esta en daoimpl consultando la nave que borrara"+numeroAeronave);
      return ofy().load().type(AeronaveEntity.class).id(numeroAeronave).now();
		
	}

	@Override
	public void findAll(AeronaveEntity a) {
		// TODO Auto-generated method stub
		
	}
   
	public List<AeronaveEntity> getAll() {
		return ofy().load().type(AeronaveEntity.class).list();
	}


	@Override
	public List<AeronaveEntity> getAllN() {
		// TODO Auto-generated method stub
		return null;
	}
}