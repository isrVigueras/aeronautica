package com.tikal.aeronautikal.service;

import java.util.List;
import java.util.Map;

import com.tikal.aeronautikal.entity.ContactoEntity;



public interface ContactoService {
		
	public void save(ContactoEntity a);

	    public void delete(ContactoEntity a);
	    
	    public void update(ContactoEntity a);
	    
	    public ContactoEntity consult(Long id);
	    
	    public List<ContactoEntity> getAll();

}
