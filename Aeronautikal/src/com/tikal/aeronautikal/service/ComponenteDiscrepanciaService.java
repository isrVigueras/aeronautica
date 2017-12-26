package com.tikal.aeronautikal.service;

import java.util.List;

import com.tikal.aeronautikal.entity.ComponenteDiscrepancia;
import com.tikal.aeronautikal.entity.EventoEntity;


public interface ComponenteDiscrepanciaService {
	
	public void save(ComponenteDiscrepancia c);

    public void delete(ComponenteDiscrepancia c);
    
    public void update(ComponenteDiscrepancia c);
    
    public ComponenteDiscrepancia consult(Long id);
    
    public List<ComponenteDiscrepancia> getByDiscrepancia(Long idDiscrepancia);
}
