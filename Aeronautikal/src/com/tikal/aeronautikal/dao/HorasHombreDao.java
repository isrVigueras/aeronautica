package com.tikal.aeronautikal.dao;

import java.util.List;

import com.tikal.aeronautikal.entity.HorasHombre;



public interface HorasHombreDao {

	public void save(HorasHombre h);

	public void delete(HorasHombre h);

	public void update(HorasHombre h);
	
	public HorasHombre consult(Long id);	
    
	public List<HorasHombre> findAll();
	
	public List<HorasHombre> getAll();
	
	public List<HorasHombre> getByEmpleado(Long idEmpleado);
	
	public List<HorasHombre> getAsignadas();
	
	public List<HorasHombre> getAsignadasByEmpleado(Long idEmpleado);

}
