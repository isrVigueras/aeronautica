package com.tikal.aeronautikal.dao;

import java.util.List;
import java.util.Map;

import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.model.Aeronave;


public interface AeronaveDao {


	<T extends BaseEntity> void save(T object);

//	<T extends BaseEntity> void delete(T object);

//	<T extends BaseEntity> void update(Aeronave nave);

//	<T extends BaseEntity> void findAll(T object);

//////	<T extends BaseEntity> List<T> listObjectByPage(Class<T> clazz, int page, int pageSize, String order);

//	<T extends BaseEntity> long countAll(Class<T> clazz);

	<T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions);
	
}

