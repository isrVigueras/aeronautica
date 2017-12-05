package com.tikal.aeronautikal.service;

import java.util.List;
import java.util.Map;

import com.tikal.aeronautikal.entity.EmpresaEntity;

public interface EmpresaService {
	  
	  public void save(EmpresaEntity e);
	  
	  public void consult(Long id);
		  
	  public List<EmpresaEntity> getAll();
	  
	  public List<EmpresaEntity> getAllEmpresas();

}