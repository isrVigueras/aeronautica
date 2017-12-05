package com.tikal.aeronautikal.service.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.OrdenDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.service.OrdenService;

@Service("ordenService")
public class OrdenServiceImpl implements OrdenService {
	
	  @Autowired
	    protected OrdenDao ordenDao;

	    public void save(OrdenVo o) {
	    	System.out.println("si entra a OrdenServiceImpl");
	        ordenDao.save(o);
	    }
	 
	    public List<OrdenVo> getAll(){
			// TODO Auto-generated method stub
	    		return ofy().load().type(OrdenVo.class).list();
	    		// TODO Auto-generated method stub
	    		
	    	
		}

		@Override
		public OrdenVo consult(Long folio) {
			// TODO Auto-generated method stub
			return ofy().load().type(OrdenVo.class).id(folio).now();
			
		}
	    
		
}
