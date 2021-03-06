package com.tikal.aeronautikal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.service.AeronaveService;


import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import java.util.Map;
/**
 * @author 
 */
@Service("aeronaveService")
public class AeronaveServiceImpl implements AeronaveService {

    @Autowired
    protected AeronaveDao aeronaveDao;

    public  void save(AeronaveEntity a) {
    	aeronaveDao.save(a);
    }

    public  void delete(AeronaveEntity a) {
    	aeronaveDao.delete(a);
    }
    
    public void update(AeronaveEntity a){
    	aeronaveDao.update(a);
    }

	
	public  AeronaveEntity consult(Long id) {
		return aeronaveDao.consult(id);
		//return ofy().load().type(AeronaveEntity.class).id(numeroSerie).now();
		// TODO Auto-generated method stub		
	}
	
	@Override
	public List<AeronaveEntity> getAll(){
		// TODO Auto-generated method stub
		return aeronaveDao.getAll();
    		//return ofy().load().type(AeronaveEntity.class).list();
    		// TODO Auto-generated method stub
    		
    	
	}
}
