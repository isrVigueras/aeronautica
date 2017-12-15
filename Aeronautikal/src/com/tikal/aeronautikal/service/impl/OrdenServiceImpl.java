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

	   
	 
	    public  void save(OrdenVo a) {
	    	ordenDao.save(a);
	    }

	    public  void delete(OrdenVo a) {
	    	ordenDao.delete(a);
	    }
	    
	    public void update(OrdenVo a){
	    	ordenDao.update(a);
	    }

		
		public  OrdenVo consult(String folio) {
			return ordenDao.consult(folio);
					
			// TODO Auto-generated method stub
			// ofy().load().type(OrdenVo.class).id(numeroSerie).now();
		}
		
		@Override
		public List<OrdenVo> getAll(){
			return ordenDao.getAll();
			// TODO Auto-generated method stub
	    		//return ofy().load().type(OrdenVo.class).list();
	    		// TODO Auto-generated method stub
	    		
	    	
		}

		@Override
		public List<OrdenVo> getAllN(){
			return ordenDao.getAllN();
		}

		
		
}
