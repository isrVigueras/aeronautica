package com.tikal.aeronautikal.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.EmpresaDao;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.service.EmpresaService;
@Service ("empresaService")

public class EmpresaServiceImpl implements EmpresaService {
	
	  @Autowired
	    protected EmpresaDao empresaDao;

	    public <T extends BaseEntity> void save(T object) {
	    	System.out.println("si entra a EmpresaServiceImpl");
	        empresaDao.save(object);
	    }
	    
	    public <T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions) {
	        return empresaDao.getUniqueEntity(clazz, conditions);
	    }

}
