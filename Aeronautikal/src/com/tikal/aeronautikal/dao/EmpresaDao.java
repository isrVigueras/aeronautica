package com.tikal.aeronautikal.dao;


	import com.tikal.aeronautikal.entity.BaseEntity;
	import java.util.List;
	import java.util.Map;

	public interface EmpresaDao {

		<T extends BaseEntity> void save(T object);

		<T extends BaseEntity> void delete(T object);

		<T extends BaseEntity> List<T> listObjectByPage(Class<T> clazz, int page, int pageSize, String order);

		<T extends BaseEntity> long countAll(Class<T> clazz);

		<T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions);
	}
