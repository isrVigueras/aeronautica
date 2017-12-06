package com.tikal.aeronautikal.service;

import java.util.List;

import com.tikal.aeronautikal.entity.DiscrepanciaEntity;


public interface DiscrepanciaService {
	
	 public void save(DiscrepanciaEntity c);
	  
	 public DiscrepanciaEntity consult(Long folio);
	  
	 public List<DiscrepanciaEntity> getAll();
	 
	 public List<DiscrepanciaEntity> getByOrden(Long Folio);


}
