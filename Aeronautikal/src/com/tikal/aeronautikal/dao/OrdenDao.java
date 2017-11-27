package com.tikal.aeronautikal.dao;

import java.util.Map;

import com.tikal.aeronautikal.entity.BaseEntity;


public interface OrdenDao {
	
	<T extends BaseEntity> void save(T object);
	
	//<T extends BaseEntity> void delete(T object);
	
	//<T extends BaseEntity> void update(T object);
	
	//<T extends BaseEntity> void findAll(T object);
	
	//<T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions);


}
