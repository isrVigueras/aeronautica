package com.tikal.aeronautikal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.CategoriaDao;
import com.tikal.aeronautikal.dao.UnidadDao;
import com.tikal.aeronautikal.entity.Unidad;
import com.tikal.aeronautikal.service.UnidadService;

@Service ("unidadService")
public class UnidadServiceImpl implements UnidadService{

	  @Autowired
	    protected UnidadDao unidadDao;
	  
	@Override
	public void save(Unidad u) {
		// TODO Auto-generated method stub
		unidadDao.save(u);
	}

	@Override
	public void delete(Unidad u) {
		// TODO Auto-generated method stub
		unidadDao.delete(u);
	}

	@Override
	public void update(Unidad u) {
		// TODO Auto-generated method stub
		unidadDao.update(u);
	}

	@Override
	public Unidad consult(Long id) {
		// TODO Auto-generated method stub
		return unidadDao.consult(id);
	}

	@Override
	public List<Unidad> getAll() {
		// TODO Auto-generated method stub
		return unidadDao.getAll();
	}
	
	
	

}
