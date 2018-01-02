package com.tikal.aeronautikal.service.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tikal.aeronautikal.dao.RequisicionDao;

import com.tikal.aeronautikal.entity.RequisicionEntity;
import com.tikal.aeronautikal.service.RequisicionService;


@Service ("requisicionService")
public class RequisicionServiceImpl implements RequisicionService {
	

	@Autowired
    protected RequisicionDao requisicionDao;
	
		@Override
		public void save(RequisicionEntity r) {
		// TODO Auto-generated method stub
		requisicionDao.save(r);
		
		}
	
		@Override
		public List<RequisicionEntity> getAll(){
			// TODO Auto-generated method stub
	    		return ofy().load().type(RequisicionEntity.class).list();
	    		// TODO Auto-generated method stub
	    		
	    	
		}

		@Override
		public RequisicionEntity consult(Long folio) {
			return ofy().load().type(RequisicionEntity.class).id(folio).now();
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(RequisicionEntity r) {
			// TODO Auto-generated method stub
			requisicionDao.delete(r);
		}

		@Override
		public void update(RequisicionEntity r) {
			// TODO Auto-generated method stub
			requisicionDao.update(r);
			
		}

		@Override
		public List<RequisicionEntity> getByComponente(Long id) {
			// TODO Auto-generated method stub
			return requisicionDao.getByComponente(id);
		}

		@Override
		public RequisicionEntity getByComDis(Long idComDis) {
			// TODO Auto-generated method stub
			return requisicionDao.getByComDis(idComDis);
		}

}
