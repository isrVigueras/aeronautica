package com.tikal.aeronautikal.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import java.util.Map;

import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.entity.AeronaveEntity;
//import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.entity.RequisicionEntity;





public interface AeronaveDao {


	public void save(AeronaveEntity a);

	public void delete(AeronaveEntity a);

	public void update(AeronaveEntity nave);
	
	public AeronaveEntity consult(String numeroAeronave);
	
	//public static AeronaveEntity consult(String numeroSerie);
	
    
	public void findAll(AeronaveEntity a);
	
	public List<AeronaveEntity> getAll();
	
	public List<AeronaveEntity> getAllN();


	
}

