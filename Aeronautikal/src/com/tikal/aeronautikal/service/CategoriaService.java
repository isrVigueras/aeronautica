package com.tikal.aeronautikal.service;

import java.util.List;

import com.tikal.aeronautikal.entity.Categoria;



public interface CategoriaService {
	
	  public void save(Categoria c);

	    public void delete(Categoria c);
	    
	    public void update(Categoria c);
	    
	    public Categoria consult(Long id);
	    
	    public List<Categoria> getAll();

}
