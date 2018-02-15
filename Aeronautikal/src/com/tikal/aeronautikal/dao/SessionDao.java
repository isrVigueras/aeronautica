package com.tikal.aeronautikal.dao;


import com.tikal.aeronautikal.entity.SessionEntity;

public interface SessionDao {

	public void save(SessionEntity s);

	public void delete(SessionEntity s);

	public void update(SessionEntity s);
	
	public SessionEntity consult(String id);
	
	public SessionEntity getByName(String nameUser);
}
