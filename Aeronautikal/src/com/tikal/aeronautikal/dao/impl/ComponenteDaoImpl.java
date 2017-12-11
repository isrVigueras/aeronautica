package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.ComponenteDao;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;

@Service("componenteDao")
public class ComponenteDaoImpl implements ComponenteDao{
	
	public void save(ComponenteEntity c) {   
		System.out.println("si lega aqui");
        ofy().save().entity(c).now();
    }
	public List<ComponenteEntity> getAll() {
		return ofy().load().type(ComponenteEntity.class).list();
	}


	public void findAll() {
	// TODO Auto-generated method stub
	
	}

	@Override
	public void delete(ComponenteEntity c) {
		// TODO Auto-generated method stub@Override
		ofy().delete().entity(c).now();
			//object.setActivo(false);
			//update(c);
		
		
	}


	@Override
	public void update(ComponenteEntity c) {
		// TODO Auto-generated method stub
		
			ofy().save().entity(c).now();
		
		}

		
	


	@Override
	public ComponenteEntity consult(Long id) {
		System.out.println("aqui esta consultando el componente " );
       return ofy().load().type(ComponenteEntity.class).id(id).now();
		
	}
	@Override
	public void updateExistencias(Long id, Integer d_cantidad, Integer d_pendientes) {
		// TODO Auto-generated method stub
		ComponenteEntity old = this.consult(id);
		if (old != null) {
//			//old.setDireccion(e.getDireccion());
			old.setD_cantidad(d_cantidad);
			//old.setD_componente(c.getD_componente());
		//	old.setD_parte(c.getD_parte());;
			old.setD_pendientes(d_pendientes);
		//	old.setD_vale(c.getD_vale());
		//	old.setD_requisicion(c.getD_requisicion());
			
			ofy().save().entity(old);
		
		}
		//return null;
	}


}
