package com.tikal.aeronautikal.service.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.dao.ComponenteDao;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.service.ComponenteService;

@Service ("componenteService")
public class ComponenteServiceImpl implements ComponenteService{

	
	@Autowired
    protected ComponenteDao componenteDao;
	
		@Override
		public void save(ComponenteEntity c) {
		// TODO Auto-generated method stub
		componenteDao.save(c);
		
		}
	
		@Override
		public List<ComponenteEntity> getAll(){
			// TODO Auto-generated method stub
	    		return ofy().load().type(ComponenteEntity.class).list();
	    		// TODO Auto-generated method stub
	    		
	    	
		}

		@Override
		public void consult(Long id) {
			// TODO Auto-generated method stub
			
		}

}
