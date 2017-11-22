package com.tikal.aeronautikal.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.ContactoDao;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.service.ContactoService;

@Service ("contactoService")
public class ContactoServiceImpl implements ContactoService {
	
	 @Autowired
	    protected ContactoDao contactoDao;

	    public <T extends BaseEntity> void save(T object) {
	    	System.out.println("si entra a ContactoServiceImpl");
	        contactoDao.save(object);
	    }
	    
	    public <T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions) {
	
	    	return contactoDao.getUniqueEntity(clazz, conditions);
	    }

}
