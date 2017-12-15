package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.googlecode.objectify.cmd.Query;
import com.tikal.aeronautikal.dao.ContactoDao;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.entity.ContactoEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;
import com.tikal.aeronautikal.exception.ObjectNotFoundException;

@Service("contactoDao")

public class ContactoDaoImpl implements ContactoDao {
	
	public void save(ContactoEntity c) {   
		System.out.println("si lega aqui");
        ofy().save().entity(c).now();
    }
	public List<ContactoEntity> getAll() {
		return ofy().load().type(ContactoEntity.class).list();
	}


	public void findAll() {
	// TODO Auto-generated method stub
	
	}

	@Override
	public void delete(ContactoEntity c) {
		// TODO Auto-generated method stub@Override
		ofy().delete().entity(c).now();
			//object.setActivo(false);
			//update(c);
		
		
	}


	@Override
	public void update(ContactoEntity c) {
		// TODO Auto-generated method stub
		ContactoEntity old = this.consult(c.getId());
//		if (old != null) {
////			//old.setDireccion(e.getDireccion());
//			old.setNombre(c.getNombre());
//			old.setTelefono(c.getTelefono());
//			old.setCorreoElectronico(c.getCorreoElectronico());
			
			ofy().save().entity(c);
		
	}

		
	


	@Override
	public ContactoEntity consult(Long id) {
		System.out.println("aqui esta consultando la entidad que va a borrar" );
       return ofy().load().type(ContactoEntity.class).id(id).now();
		
	}


}
