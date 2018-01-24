package com.tikal.aeronautikal.dao;

import java.util.List;

import com.tikal.aeronautikal.entity.PuestoEntity;



public interface PuestoDao {
	
	public void save(PuestoEntity p);

	public void delete(PuestoEntity p);

	public void update(PuestoEntity p);
	
	public PuestoEntity consult(Long id);	
    
	public void findAll(PuestoEntity p);
	
	public List<PuestoEntity> getAll();

}
