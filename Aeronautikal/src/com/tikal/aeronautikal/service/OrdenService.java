package com.tikal.aeronautikal.service;

import java.util.Map;

import com.tikal.aeronautikal.entity.BaseEntity;

public interface OrdenService {
	
	 	
	  <T extends BaseEntity> void save(T object);
	  
	 // <T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions);

}
