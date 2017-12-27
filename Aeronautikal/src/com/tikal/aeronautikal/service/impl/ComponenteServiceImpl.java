package com.tikal.aeronautikal.service.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.dao.ComponenteDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
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
		public List<ComponenteEntity> getAllF(){
			// TODO Auto-generated method stub
	    		return ofy().load().type(ComponenteEntity.class).filter("d_cantidad >", 0).list();
	    		// TODO Auto-generated method stub
	    		
	    	
		}

		@Override
		public ComponenteEntity consult(Long folio) {
			// TODO Auto-generated method stub
			return ofy().load().type(ComponenteEntity.class).id(folio).now();
			
		}
		
		@Override
		public void delete(ComponenteEntity c) {
		// TODO Auto-generated method stub
		componenteDao.delete(c); 
		
		}

		@Override
		public void updateExistencias(Long id, Integer d_cantidad, Integer d_pendientes) {
			// TODO Auto-generated method stub
			componenteDao.updateExistencias(id, d_cantidad, d_pendientes);
		}

}
