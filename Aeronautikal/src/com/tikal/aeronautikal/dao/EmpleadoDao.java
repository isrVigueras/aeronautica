package com.tikal.aeronautikal.dao;


import java.util.List;

import com.tikal.aeronautikal.entity.EmpleadoEntity;

public interface EmpleadoDao {

	public void save(EmpleadoEntity e);

	public void delete(EmpleadoEntity e);

	public void update(EmpleadoEntity e);
	
	public EmpleadoEntity consult(Long id);
	    
	public void findAll(EmpleadoEntity e);
	
	//public void getByNAme(String Empleado);
	
	public List<EmpleadoEntity> getAll();
	
}
