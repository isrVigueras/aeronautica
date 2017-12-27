package com.tikal.aeronautikal.dao;


import java.util.List;

import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;

	public interface ComponenteDao {
	
	public void save(ComponenteEntity c);
	
	public void delete(ComponenteEntity c);
	
	public void update(ComponenteEntity c); 
	
	public ComponenteEntity consult(Long id);
	
	public List<ComponenteEntity> getAll();
	
	public List<ComponenteEntity> getAllF();
	
	public List<ComponenteEntity> getAll0();
	
	public void updateExistencias(Long id, Integer existencias, Integer pendientes);

	public List<ComponenteEntity> getByDiscrepancia(Long idDiscrepancia);

	//void findAll();
	
	
}
