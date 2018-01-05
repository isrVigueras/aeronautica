package com.tikal.aeronautikal.dao;

import java.util.List;

import com.tikal.aeronautikal.entity.Condicion;


public interface CondicionDao {

	public void save(Condicion c);

	public void delete(Condicion c);

	public void update(Condicion c);
	
	public Condicion consult(Long id);	
    
	public void findAll(Condicion c);
	
	public List<Condicion> getAll();
}
