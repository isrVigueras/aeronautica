package com.tikal.aeronautikal.dao;

import java.util.List;

import com.tikal.aeronautikal.entity.Categoria;



public interface CategoriaDao {

	public void save(Categoria c);

	public void delete(Categoria c);

	public void update(Categoria c);
	
	public Categoria consult(Long id);	
    
	public void findAll(Categoria c);
	
	public List<Categoria> getAll();

}
