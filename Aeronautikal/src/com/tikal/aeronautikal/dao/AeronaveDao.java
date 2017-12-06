package com.tikal.aeronautikal.dao;

import java.util.List;
import java.util.Map;

import com.tikal.aeronautikal.entity.AeronaveEntity;
//import com.tikal.aeronautikal.entity.BaseEntity;





public interface AeronaveDao {


	public void save(AeronaveEntity a);

	public void delete(AeronaveEntity a);

	public void update(AeronaveEntity nave);
	
    public static AeronaveEntity consult(String numeroSerie) {
		// TODO Auto-generated method stub
		return null;
	}
    
	public void findAll(AeronaveEntity a);
	
	public List<AeronaveEntity> getAll();


	
}

