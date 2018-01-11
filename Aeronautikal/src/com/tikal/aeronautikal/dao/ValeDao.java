package com.tikal.aeronautikal.dao;

import java.util.List;

import com.tikal.aeronautikal.entity.EventoEntity;
import com.tikal.aeronautikal.entity.ValeEntity;

public interface ValeDao {
	
	public void save(ValeEntity v);

	public void delete(ValeEntity v);

	public void update(ValeEntity v);
	
	public ValeEntity consult(Long id);
	    
	public List<ValeEntity> findAll();
	
	public List<ValeEntity> getByDiscrepancia(Long id);

}
