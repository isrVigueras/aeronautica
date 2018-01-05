package com.tikal.aeronautikal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.CondicionDao;
import com.tikal.aeronautikal.entity.Condicion;
import com.tikal.aeronautikal.service.CondicionService;


@Service("condicionService")
public class CondicionServiceImpl implements CondicionService{
	
	  @Autowired
	    protected CondicionDao condicionDao;
	  
	@Override
	public void save(Condicion c) {
		// TODO Auto-generated method stub
		condicionDao.save(c);
	}

	@Override
	public void delete(Condicion c) {  
		// TODO Auto-generated method stub
		condicionDao.delete(c);
	}

	@Override
	public void update(Condicion c) {
		// TODO Auto-generated method stub
		condicionDao.update(c);
	}

	@Override
	public Condicion consult(Long id) {
		// TODO Auto-generated method stub
		return condicionDao.consult(id);
	}

	@Override
	public List<Condicion> getAll() {
		// TODO Auto-generated method stub
		return condicionDao.getAll();
	}
	
	
	

}

