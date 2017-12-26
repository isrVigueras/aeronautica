package com.tikal.aeronautikal.dao;

import java.util.List;

import com.tikal.aeronautikal.entity.ComponenteDiscrepancia;
import com.tikal.aeronautikal.entity.EventoEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;



public interface ComponenteDiscrepanciaDao {
	
	public void save(ComponenteDiscrepancia c);
	
	public void delete(ComponenteDiscrepancia c);
	
	public void update(ComponenteDiscrepancia c); 
	
	public ComponenteDiscrepancia consult(Long id);
	
	public List<ComponenteDiscrepancia> getByDiscrepancia(Long idDiscrepancia);

}
