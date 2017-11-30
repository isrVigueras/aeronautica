package com.tikal.aeronautikal.dao;

import java.util.List;
import java.util.Map;

import com.tikal.aeronautikal.entity.AeronaveEntity;
//import com.tikal.aeronautikal.entity.BaseEntity;




public interface AeronaveDao {


	public void save(AeronaveEntity a);

	public void delete(AeronaveEntity a);

	public void update(AeronaveEntity nave);
	
    public AeronaveEntity consult(long numeroSerie);

	public void findAll(AeronaveEntity a);

	
//////	<T extends BaseEntity> List<T> listObjectByPage(Class<T> clazz, int page, int pageSize, String order);

//	<T extends BaseEntity> long countAll(Class<T> clazz);

	//<T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions);


	
}

