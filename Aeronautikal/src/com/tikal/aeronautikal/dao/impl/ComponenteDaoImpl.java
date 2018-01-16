package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.ComponenteDao;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.entity.DiscrepanciaEntity;
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

	public List<ComponenteEntity> getAllF() {                   
		return ofy().load().type(ComponenteEntity.class).filter("d_cantidad >", 0).list();
		
	}
	
	public List<ComponenteEntity> getAll0() {                   
		return ofy().load().type(ComponenteEntity.class).filter("d_cantidad", 0).list();
		
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
			System.out.println("guarda los nuevos datos" );
		}

		
	
	@Override
	public ComponenteEntity consult(Long id) {
		System.out.println("consultando el componente " );
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

	@Override
	public List<ComponenteEntity> getByDiscrepancia(Long idDiscrepancia) {
		// TODO Auto-generated method stub
		//List<ComponenteEntity> comps = ofy().load().type(ComponenteEntity.class).filter("folioOrden", folioOrden).list();
		return null;
	}
	@Override
	public List<ComponenteEntity> getByCategoria(Long idCategoria) {
		// TODO Auto-generated method stub
		System.out.println("consultando componentes por categoria " );
		return ofy().load().type(ComponenteEntity.class).filter("idCategoria", idCategoria).list();
		
	}
	@Override
	public List<ComponenteEntity> getMaxMin() {
		// TODO Auto-generated method stub
		List<ComponenteEntity> min = new ArrayList<ComponenteEntity>();
		List<ComponenteEntity> todos = ofy().load().type(ComponenteEntity.class).list();
		for (ComponenteEntity c :todos){
			if (c.getD_cantidad()<=c.getMinimo()){
				System.out.println("El componente ya esta en alerta de mínimo" );
				min.add(c);
			}
			
		}
		
		return min;
	}
}
