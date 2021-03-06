package com.tikal.aeronautikal.dao;

import java.util.List;
import java.util.Map;

import com.tikal.aeronautikal.controller.vo.OrdenVo;


public interface OrdenDao {
	
	public void save(OrdenVo o);
	
	public void delete(OrdenVo o);
	
	public void update(OrdenVo o);
	
	public OrdenVo consult(Long id);
	
	public List<OrdenVo> getAll();
	
	public List<OrdenVo> getAllN();
	
	public List<OrdenVo> getAllNac();

	void findAll();



}
