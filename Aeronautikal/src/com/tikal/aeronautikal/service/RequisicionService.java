package com.tikal.aeronautikal.service;

import java.util.List;
import com.tikal.aeronautikal.entity.RequisicionEntity;



public interface RequisicionService {
	
	 public void save(RequisicionEntity r);
	  
	 public RequisicionEntity consult(Long folio);
	 
	 public RequisicionEntity getByComDis(Long idComDis);
	 
	 public List<RequisicionEntity> getAll();
	 
	 public void delete(RequisicionEntity r);
	    
	 public void update(RequisicionEntity r);
	 
	 public List<RequisicionEntity> getByComponente(Long id);
	    
}
