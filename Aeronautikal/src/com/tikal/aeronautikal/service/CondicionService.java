package com.tikal.aeronautikal.service;

import java.util.List;

import com.tikal.aeronautikal.entity.Condicion;

public interface CondicionService {
	
	  public void save(Condicion c);

	    public void delete(Condicion c);
	    
	    public void update(Condicion c);
	    
	    public Condicion consult(Long id);
	    
	    public List<Condicion> getAll();

}
