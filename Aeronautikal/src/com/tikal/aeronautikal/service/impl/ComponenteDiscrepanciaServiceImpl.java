package com.tikal.aeronautikal.service.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.ComponenteDiscrepanciaDao;
import com.tikal.aeronautikal.entity.ComponenteDiscrepancia;
import com.tikal.aeronautikal.entity.EventoEntity;
import com.tikal.aeronautikal.service.ComponenteDiscrepanciaService;


@Service ("componenteDiscrepanciaService")
public class ComponenteDiscrepanciaServiceImpl implements ComponenteDiscrepanciaService{

	
	 @Autowired
	    protected ComponenteDiscrepanciaDao componenteDiscrepanciaDao;

	 public  void save(ComponenteDiscrepancia c) {
	    	componenteDiscrepanciaDao.save(c);
	    }

	    public  void delete(ComponenteDiscrepancia c) {
	    	componenteDiscrepanciaDao.delete(c);
	    }
	    
	    public void update(ComponenteDiscrepancia c){
	    	componenteDiscrepanciaDao.update(c);
	    }
	    
	    @Override
		public List<ComponenteDiscrepancia> getByDiscrepancia(Long idDiscrepancia) {
			// TODO Auto-generated method stub}
			return componenteDiscrepanciaDao.getByDiscrepancia(idDiscrepancia);
			//return null;
		}

	    @Override
		public ComponenteDiscrepancia consult(Long id) {
			// TODO Auto-generated method stub
			return ofy().load().type(ComponenteDiscrepancia.class).id(id).now();
			
		}
}
