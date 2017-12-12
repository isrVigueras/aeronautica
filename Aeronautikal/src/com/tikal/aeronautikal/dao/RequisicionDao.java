package com.tikal.aeronautikal.dao;

import java.util.List;

import com.tikal.aeronautikal.entity.RequisicionEntity;



public interface RequisicionDao {
		
		public void save(RequisicionEntity r);
		
		public void delete(RequisicionEntity r);
		
		public void update(RequisicionEntity r); 
		
		public RequisicionEntity consult(Long folio);
		
		public List<RequisicionEntity> getAll();
		
		public Integer getPendientes(Long idComponente);
		
		public List<RequisicionEntity> getByComponente(Long id);

}
