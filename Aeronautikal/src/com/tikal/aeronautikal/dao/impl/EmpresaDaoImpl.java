package com.tikal.aeronautikal.dao.impl;

	import com.googlecode.objectify.cmd.Query;
import com.tikal.aeronautikal.dao.EmpresaDao;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.entity.EmpresaEntity;

import com.tikal.aeronautikal.exception.ObjectNotFoundException;


import org.springframework.stereotype.Service;
	import org.springframework.util.CollectionUtils;

	import java.util.List;
	import java.util.Map;
	import static com.googlecode.objectify.ObjectifyService.ofy;

	/**
	 * @author 
	 */

	@Service("empresaDao")

	public class EmpresaDaoImpl implements EmpresaDao {

	    public void save(EmpresaEntity e) {
	    	
	        ofy().save().entity(e).now();
	    }

		public List<EmpresaEntity> getAll() {
			return ofy().load().type(EmpresaEntity.class).list();
		}


		public void findAll() {
		// TODO Auto-generated method stub
		
		}

		@Override
		public void delete(EmpresaEntity e) {
			// TODO Auto-generated method stub@Override
			
				//object.setActivo(false);
				update(e);
			
			
		}


		@Override
		public void update(EmpresaEntity e) {
			// TODO Auto-generated method stub
			EmpresaEntity old = this.consult(e.getId());
			if (old != null) {
//				//old.setDireccion(e.getDireccion());
				old.setNickName(e.getNickName());				
				ofy().save().entity(old);
			
			}

			
		}


		@Override
		public EmpresaEntity consult(Long id) {
			
	       return ofy().load().type(EmpresaEntity.class).id(id).now();
			
		}
	    
	    
		@Override
		public List<EmpresaEntity> getAllEmpresas() {
			return ofy().load().type(EmpresaEntity.class).list();
		}

}
