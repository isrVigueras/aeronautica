package com.tikal.aeronautikal.dao;

import java.util.List;

import com.tikal.aeronautikal.entity.Unidad;



public interface UnidadDao {
	
	public void save(Unidad u);

	public void delete(Unidad u);

	public void update(Unidad u);
	
	public Unidad consult(Long id);	
    
	public void findAll(Unidad u);
	
	public List<Unidad> getAll();


}
