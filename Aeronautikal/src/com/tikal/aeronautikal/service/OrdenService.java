package com.tikal.aeronautikal.service;

import java.util.List;
import java.util.Map;

import com.tikal.aeronautikal.controller.vo.OrdenVo;



public interface OrdenService {
	
	 	
	  public void save(OrdenVo o);
	  
	  public void consult(Long folio);
	  
	  public List<OrdenVo> getAll();


}
