package com.tikal.aeronautikal.dao;



import java.util.List;


import com.tikal.aeronautikal.entity.AeronaveEntity;


public interface AeronaveDao {


	public void save(AeronaveEntity a);

	public void delete(AeronaveEntity a);

	public void update(AeronaveEntity nave);
	
	public AeronaveEntity consult(Long id);
	
	//public static AeronaveEntity consult(String numeroSerie);
	
    
	public void findAll(AeronaveEntity a);
	
	public List<AeronaveEntity> getAll();
	
	public List<AeronaveEntity> getAllN();


	
}

