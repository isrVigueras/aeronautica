package com.tikal.aeronautikal.service;

import java.util.List;
import java.util.Map;

import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.entity.EmpresaEntity;

public interface EmpresaService {
	
 	
	  <T extends BaseEntity> void save(T object);
	  
	//  <T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions);

	  public List<EmpresaEntity> getAllEmpresas();

}
