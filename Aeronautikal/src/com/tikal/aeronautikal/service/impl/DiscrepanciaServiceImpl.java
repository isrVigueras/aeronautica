package com.tikal.aeronautikal.service.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tikal.aeronautikal.dao.DiscrepanciaDao;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.service.DiscrepanciaService;

@Service ("discrepanciaService")
public class DiscrepanciaServiceImpl implements DiscrepanciaService{
	

	@Autowired
    protected DiscrepanciaDao discrepanciaDao;
	
		@Override
		public void save(DiscrepanciaEntity d) {
		// TODO Auto-generated method stub
		discrepanciaDao.save(d);
		
		}
	
		@Override
		public List<DiscrepanciaEntity> getAll(){
			// TODO Auto-generated method stub
	    		return ofy().load().type(DiscrepanciaEntity.class).list();
	    		// TODO Auto-generated method stub
	    		
	    	
		}

		@Override
		public DiscrepanciaEntity consult(Long id) {
			// TODO Auto-generated method stub
			return ofy().load().type(DiscrepanciaEntity.class).id(id).now();
			
		}

		@Override
		public List<DiscrepanciaEntity> getByOrden(Long folio) {
			// TODO Auto-generated method stub}
			return discrepanciaDao.getByOrden(folio);
			//return null;
		}


}
