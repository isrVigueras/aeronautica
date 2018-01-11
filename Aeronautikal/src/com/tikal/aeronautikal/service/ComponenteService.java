package com.tikal.aeronautikal.service;

import java.util.List;


import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;

public interface ComponenteService {
	
	
	 public void save(ComponenteEntity c);
  
	 public ComponenteEntity consult(Long id);
	  
	 public List<ComponenteEntity> getAll();
	 
	 public List<ComponenteEntity> getAllF();
	 
	 public List<ComponenteEntity> getAll0();
	 
	 public List<ComponenteEntity> getByCategoria(Long idCategoria);
	 
	 public void delete(ComponenteEntity c);
	 
	 public void updateExistencias(Long id, Integer d_cantidad, Integer d_pendientes);


}
