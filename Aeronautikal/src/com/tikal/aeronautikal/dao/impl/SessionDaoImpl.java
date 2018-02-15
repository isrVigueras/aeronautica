package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.SessionDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.SessionEntity;

@Service("sessionDao")

public class SessionDaoImpl implements SessionDao {
	
	 public void save(SessionEntity s) {    
		 System.out.println("DAOsession.id:"+s.getIdSession());
		 System.out.println("DAOsession.username:"+s.getNameUser());
	        ofy().save().entity(s).now();
	    }

	    
	    public void delete(SessionEntity s) {
	    	 System.out.println("si esta en daoimpl session eliminando"+s);
	        ofy().delete().entity(s).now();
	        System.out.println("eliminando...");
	    }

	   @Override
		public void update(SessionEntity s) {
		   System.out.print("session:"+s.getIdSession());
		SessionEntity old = this.consult(s.getId());
		System.out.print("old:"+old);
			if (old != null) {
				old.setEstatus(s.getEstatus());
				old.setNameUser(old.getNameUser());
					
			}

				ofy().save().entity(old);
	   }

	    
	   @Override
		public SessionEntity consult(String id) {
		   System.out.println("si esta en daoimpl consultando la Session.."+id);
	      return ofy().load().type(SessionEntity.class).id(id).now();
			
		}



}
