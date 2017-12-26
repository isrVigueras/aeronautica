package com.tikal.aeronautikal.service;

import java.util.List;


import com.tikal.aeronautikal.entity.EventoEntity;


public interface EventoService {

	
	 public void save(EventoEntity c);
	  
	 public EventoEntity consult(Long id);
	  
	 public List<EventoEntity> getAll();
	 
	 public void delete(EventoEntity c);
	 
	 public List<EventoEntity> getByDiscrepancia(Long id);
}
