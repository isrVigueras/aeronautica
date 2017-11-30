package com.tikal.aeronautikal.dao;


import java.util.List;

import com.tikal.aeronautikal.controller.vo.OrdenVo;

import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;

public interface ComponenteDao {
	
	public void save(ComponenteEntity c);
	
	public void delete(ComponenteEntity c);
	
	public void update(ComponenteEntity c); 
	
	public ComponenteEntity consult(Long id);
	
	public List<ComponenteEntity> getAll();

	//void findAll();
	
	
}
