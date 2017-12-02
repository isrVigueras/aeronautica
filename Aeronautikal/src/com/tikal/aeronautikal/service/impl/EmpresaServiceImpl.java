package com.tikal.aeronautikal.service.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.EmpresaDao;
import com.tikal.aeronautikal.entity.EmpresaEntity;
import com.tikal.aeronautikal.service.EmpresaService;

@Service("empresaService")

public class EmpresaServiceImpl implements EmpresaService {
	
	  @Autowired
	    protected EmpresaDao empresaDao;

	  @Override
		public void save(EmpresaEntity e) {
		// TODO Auto-generated method stub
		empresaDao.save(e);
		
		}
	
		@Override
		public List<EmpresaEntity> getAll(){
			// TODO Auto-generated method stub
	    		return ofy().load().type(EmpresaEntity.class).list();
	    		// TODO Auto-generated method stub
	    		
	    	
		}

		@Override
		public void consult(Long id) {
			// TODO Auto-generated method stub
			
		}

	    public List<EmpresaEntity> getAllEmpresas(){
	    	return ofy().load().type(EmpresaEntity.class).list();
	    }
}
