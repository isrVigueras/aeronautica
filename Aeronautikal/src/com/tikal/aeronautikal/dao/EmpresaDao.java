package com.tikal.aeronautikal.dao;



	import com.tikal.aeronautikal.entity.EmpresaEntity;
	import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
	import java.util.List;
	import java.util.Map;

	public interface EmpresaDao {

		public void save(EmpresaEntity e);
		
		public void delete(EmpresaEntity c);
		
		public void update(EmpresaEntity c); 
		
		public EmpresaEntity consult(Long id);
		
		public List<EmpresaEntity> getAll();
		
		public List<EmpresaEntity> getAllEmpresas();
	}
