package com.tikal.aeronautikal.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.OrdenDao;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.service.OrdenService;

@Service("ordenService")
public class OrdenServiceImpl implements OrdenService {
	
	  @Autowired
	    protected OrdenDao ordenDao;

	    public <T extends BaseEntity> void save(T object) {
	    	System.out.println("si entra a OrdenServiceImpl");
	        ordenDao.save(object);
	    }
	    
	    public <T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions) {
	        return ordenDao.getUniqueEntity(clazz, conditions);
	    }

}
