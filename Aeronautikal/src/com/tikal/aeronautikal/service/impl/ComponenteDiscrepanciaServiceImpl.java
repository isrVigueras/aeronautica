package com.tikal.aeronautikal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.ComponenteDiscrepanciaDao;
import com.tikal.aeronautikal.dao.ContactoDao;
import com.tikal.aeronautikal.entity.ComponenteDiscrepancia;
import com.tikal.aeronautikal.entity.ContactoEntity;
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

}
