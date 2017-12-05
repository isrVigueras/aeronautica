package com.tikal.aeronautikal.service;

import java.util.List;


import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;

public interface ComponenteService {
	
	
	 public void save(ComponenteEntity c);
  
	 public ComponenteEntity consult(Long id);
	  
	 public List<ComponenteEntity> getAll();
	 
	 public void delete(ComponenteEntity c);


}
