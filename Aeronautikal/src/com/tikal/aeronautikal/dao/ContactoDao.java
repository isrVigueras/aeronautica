package com.tikal.aeronautikal.dao;

import java.util.List;
import java.util.Map;

import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.entity.ContactoEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;

public interface ContactoDao {
	
	public void save(ContactoEntity c);
	
	public void delete(ContactoEntity c);
	
	public void update(ContactoEntity c); 
	
	public ContactoEntity consult(Long id);
	
	public List<ContactoEntity> getAll();


	
}
