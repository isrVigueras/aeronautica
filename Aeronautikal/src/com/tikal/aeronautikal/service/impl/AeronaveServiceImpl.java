package com.tikal.aeronautikal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.BaseEntity;
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

    public <T extends BaseEntity> void save(T object) {
    	aeronaveDao.save(object);
    }

    public <T extends BaseEntity> void delete(T object) {
    	aeronaveDao.delete(object);
    }
    
    public void update(AeronaveEntity a){
    	aeronaveDao.update(a);
    }

//    public <T extends BaseEntity> List<T> listObjectByPage(Class<T> clazz, int page, int pageSize, String order) {
//        return selectionDao.listObjectByPage(clazz, page, pageSize, order);
//    }

 //   public <T extends BaseEntity> long countAll(Class<T> clazz) {
 //       return selectionDao.countAll(clazz);
//    }

    public <T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions) {
    	 System.out.println("ESTOY EN AERO SERVICEIMPL");
    return aeronaveDao.getUniqueEntity(clazz, conditions);
        
    }

	@Override
	public <T extends BaseEntity> void update(T object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public  AeronaveEntity consult(long numeroSerie) {
		return ofy().load().type(AeronaveEntity.class).id(numeroSerie).now();
		// TODO Auto-generated method stub
		
	}
}
