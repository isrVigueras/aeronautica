package com.tikal.aeronautikal.dao;



	import com.tikal.aeronautikal.entity.EmpresaEntity;

	import java.util.List;


	public interface EmpresaDao {

		public void save(EmpresaEntity e);
		
		public void delete(EmpresaEntity c);
		
		public void update(EmpresaEntity c); 
		
		public EmpresaEntity consult(Long id);
		
		public List<EmpresaEntity> getAll();
		
		public List<EmpresaEntity> getAllEmpresas();
	}
