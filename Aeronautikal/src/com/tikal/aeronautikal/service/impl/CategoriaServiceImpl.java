package com.tikal.aeronautikal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tikal.aeronautikal.dao.CategoriaDao;

import com.tikal.aeronautikal.entity.Categoria;
import com.tikal.aeronautikal.service.CategoriaService;


@Service ("categoriaService")
public class CategoriaServiceImpl implements CategoriaService {
	

    @Autowired
    protected CategoriaDao categoriaDao;

    public  void save(Categoria c) {
    	categoriaDao.save(c);
    }

    public  void delete(Categoria c) {
    	categoriaDao.delete(c);
    }
    
    public void update(Categoria c){
    	categoriaDao.update(c);
    }

	
	public  Categoria consult(Long id) {
		return categoriaDao.consult(id);
		//return ofy().load().type(AeronaveEntity.class).id(numeroSerie).now();
		// TODO Auto-generated method stub		
	}
	
	@Override
	public List<Categoria> getAll(){
		// TODO Auto-generated method stub
		return categoriaDao.getAll();
    		//return ofy().load().type(AeronaveEntity.class).list();
    		// TODO Auto-generated method stub
    		
    	
	}

}
