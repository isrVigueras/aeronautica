package com.tikal.aeronautikal.service.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.ContactoDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.entity.ContactoEntity;
import com.tikal.aeronautikal.service.ContactoService;

@Service ("contactoService")
public class ContactoServiceImpl implements ContactoService {
	
	 @Autowired
	    protected ContactoDao contactoDao;

	 public  void save(ContactoEntity c) {
	    	contactoDao.save(c);
	    }

	    public  void delete(ContactoEntity c) {
	    	contactoDao.delete(c);
	    }
	    
	    public void update(ContactoEntity c){
	    	contactoDao.update(c);
	    }

		
		public  ContactoEntity consult(Long id) {
			return ofy().load().type(ContactoEntity.class).id(id).now();
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public List<ContactoEntity> getAll(){
			// TODO Auto-generated method stub
	    		return ofy().load().type(ContactoEntity.class).list();
	    		// TODO Auto-generated method stub
	    		
	    	
		}
}
