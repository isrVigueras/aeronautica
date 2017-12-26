package com.tikal.aeronautikal.dao;

import java.util.List;


import com.tikal.aeronautikal.entity.EventoEntity;


public interface EventoDao {
	
		
		public void save(EventoEntity e);
		
		public void delete(EventoEntity e);
		
		public void update(EventoEntity e); 
		
		public EventoEntity consult(Long id);
		
		public List<EventoEntity> getAll();
		
		public List<EventoEntity> getByDiscrepancia(Long id);		

}
