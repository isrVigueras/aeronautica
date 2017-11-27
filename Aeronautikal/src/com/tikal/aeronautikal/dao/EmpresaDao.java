package com.tikal.aeronautikal.dao;


	import com.tikal.aeronautikal.entity.BaseEntity;
	import com.tikal.aeronautikal.entity.EmpresaEntity;
	import java.util.List;
	import java.util.Map;

	public interface EmpresaDao {

		<T extends BaseEntity> void save(T object);
		
	//	<T extends BaseEntity> void delete(T object);
		
	//	<T extends BaseEntity> void update(T object);
		
	//	<T extends BaseEntity> void findAll(T object);
		
	//	<T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions);
		
		//<T extends BaseEntity> getAllEmpresas_();
		
		public List<EmpresaEntity> getAllEmpresas();
	}
