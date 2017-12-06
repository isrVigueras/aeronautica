package com.tikal.aeronautikal.dao;

import java.util.List;

import com.tikal.aeronautikal.entity.DiscrepanciaEntity;


public interface DiscrepanciaDao {
		
	public void save(DiscrepanciaEntity d);
	
	public void delete(DiscrepanciaEntity d);
	
	public void update(DiscrepanciaEntity d); 
	
	public DiscrepanciaEntity consult(Long folio);
	
	public List<DiscrepanciaEntity> getAll();
	
	public List<DiscrepanciaEntity> getByOrden(Long folio);

}
