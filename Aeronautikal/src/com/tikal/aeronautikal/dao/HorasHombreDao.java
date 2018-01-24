package com.tikal.aeronautikal.dao;

import java.util.List;

import com.tikal.aeronautikal.entity.HorasHombre;



public interface HorasHombreDao {

	public void save(HorasHombre h);

	public void delete(HorasHombre h);

	public void update(HorasHombre h);
	
	public HorasHombre consult(Long id);	
    
	public void findAll(HorasHombre h);
	
	public List<HorasHombre> getAll();

}
