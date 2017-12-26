package com.tikal.aeronautikal.service.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tikal.aeronautikal.dao.EventoDao;

import com.tikal.aeronautikal.entity.EventoEntity;

import com.tikal.aeronautikal.service.EventoService;

@Service ("eventoService")
public class EventoServiceImpl implements EventoService{
	

	

	@Autowired
    protected EventoDao eventoDao;
	
		@Override
		public void save(EventoEntity c) {
		// TODO Auto-generated method stub
		eventoDao.save(c);
		
		}
	
		@Override
		public List<EventoEntity> getAll(){
			// TODO Auto-generated method stub
	    		return ofy().load().type(EventoEntity.class).list();
	    		// TODO Auto-generated method stub
	    		
	    	
		}

		@Override
		public EventoEntity consult(Long folio) {
			// TODO Auto-generated method stub
			return ofy().load().type(EventoEntity.class).id(folio).now();
			
		}
		
		@Override
		public void delete(EventoEntity c) {
		// TODO Auto-generated method stub
		eventoDao.delete(c); 
		
		}

		@Override
		public List<EventoEntity> getByDiscrepancia(Long id) {
			// TODO Auto-generated method stub}
			return eventoDao.getByDiscrepancia(id);
			//return null;
		}

}
