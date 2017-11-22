package com.tikal.aeronautikal.dao.impl;


import com.googlecode.objectify.cmd.Query;
import com.tikal.aeronautikal.dao.SelectionDao;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.exception.ObjectNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * @author 
 */

@Service("selectionDao")

public class SelectionDaoImpl implements SelectionDao {

    public <T extends BaseEntity> void save(T object) {
    	
        ofy().save().entity(object).now();
    }

    public <T extends BaseEntity> void delete(T object) {
        ofy().delete().entity(object).now();
    }

    public <T extends BaseEntity> List<T> listObjectByPage(Class<T> clazz, int page, int pageSize, String order) {
        return ofy().load().type(clazz).offset((page - 1) * pageSize).limit(pageSize).order(order).list();
    }

    public <T extends BaseEntity> long countAll(Class<T> clazz) {
        return ofy().load().type(clazz).count();
    }

    public <T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions) {
    	System.out.println("ESTOY EN AERO DAOIMPL");
        Query<T> query = ofy().load().type(clazz);
        System.out.println("query: "+query);
        System.out.println("conditions: "+conditions);
        for (Map.Entry<String, Object> entry : conditions.entrySet()) {
            query = query.filter(entry.getKey(), entry.getValue());
            System.out.println("qqqqqqqqqqq: "+query);
        }
        System.out.println("--queryList :"+query.list());
        List<T> list = query.list();
        System.out.println("list: "+list);
        if (CollectionUtils.isEmpty(list)) {
        	 System.out.println("EMPTY");
            throw new ObjectNotFoundException("Object of class " + clazz.getCanonicalName() +
                    " not found by given conditions: " + conditions);
        }
        if (list.size() > 1) {
        	 System.out.println("NO EMPTY");
            throw new ObjectNotFoundException("There are several objects of class " + clazz.getCanonicalName() +
                    " found by given conditions: " + conditions);
        }
        System.out.println("SALI DE AERO DAOIMPL");
        return list.get(0);
    }

}