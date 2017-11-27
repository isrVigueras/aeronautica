package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.googlecode.objectify.cmd.Query;
import com.tikal.aeronautikal.dao.ContactoDao;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.exception.ObjectNotFoundException;

@Service("contactoDao")

public class ContactoDaoImpl implements ContactoDao {
	
	public <T extends BaseEntity> void save(T object) {
		System.out.println("si entra a contacoDaoImpl"+object);
        ofy().save().entity(object).now();
        System.out.println("si sale de conactoDaoImpl");
    }
	
//	 public <T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions) {
//	        Query<T> query = ofy().load().type(clazz);
//	        for (Map.Entry<String, Object> entry : conditions.entrySet()) {
//	            query = query.filter(entry.getKey(), entry.getValue());
//	        }
//	        List<T> list = query.list();
//	        if (CollectionUtils.isEmpty(list)) {
//	            throw new ObjectNotFoundException("Object of class " + clazz.getCanonicalName() +
//	                    " not found by given conditions: " + conditions);
//	        }
//	        if (list.size() > 1) {
//	            throw new ObjectNotFoundException("There are several objects of class " + clazz.getCanonicalName() +
//	                    " found by given conditions: " + conditions);
//	        }
//	        return list.get(0);
//	    }

}
