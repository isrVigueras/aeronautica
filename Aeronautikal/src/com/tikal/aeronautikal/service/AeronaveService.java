package com.tikal.aeronautikal.service;

import java.util.List;
import java.util.Map;

import com.tikal.aeronautikal.entity.AeronaveEntity;




/**
 * Business service for data access
 *
 * @author tikal
 */
public interface AeronaveService {

    public void save(AeronaveEntity a);

    public void delete(AeronaveEntity a);
    
    public void update(AeronaveEntity a);
    
    public AeronaveEntity consult(String numeroAeronave);
    
    public List<AeronaveEntity> getAll();

  
}