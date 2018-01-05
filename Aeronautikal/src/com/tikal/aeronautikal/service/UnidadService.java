package com.tikal.aeronautikal.service;

import java.util.List;

import com.tikal.aeronautikal.entity.Unidad;


public interface UnidadService {
	
	  public void save(Unidad u);

	    public void delete(Unidad u);
	    
	    public void update(Unidad u);
	    
	    public Unidad consult(Long id);
	    
	    public List<Unidad> getAll();

}
