package com.tikal.aeronautikal.dao.impl;


import com.googlecode.objectify.cmd.Query;
import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
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

public class AeronaveDaoImpl implements AeronaveDao {

    public <T extends BaseEntity> void save(T object) {
    	
        ofy().save().entity(object).now();
    }

   //// public <T extends BaseEntity> void delete(T object) {
  //      ofy().delete().entity(object).now();
  //  }

 //   @Override
	//public void update(AeronaveEntity nave) {
//		AeronaveEntity old = this.consult(nave.getNumeroSerie());
//		if (old != null) {
//			old.setMatricula(nave.getMatricula());
//			old.setModelo(nave.getModelo());
//			old.setTiempovuelo(nave.getTiempovuelo());
//			old.setAterrizaje(nave.getAterrizaje());
//			ofy().save().entity(old);
//		}

////	}
    
    //@Override
//	public AeronaveEntity consult(String noSerie) {
	//	return ofy().load().type(AeonaveEntity.class).id(noSerie).now();
//	}
    public <T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions) {
        Query<T> query = ofy().load().type(clazz);
        for (Map.Entry<String, Object> entry : conditions.entrySet()) {
            query = query.filter(entry.getKey(), entry.getValue());
        }
        List<T> list = query.list();
        if (CollectionUtils.isEmpty(list)) {
            throw new ObjectNotFoundException("Object of class " + clazz.getCanonicalName() +
                    " not found by given conditions: " + conditions);
        }
        if (list.size() > 1) {
            throw new ObjectNotFoundException("There are several objects of class " + clazz.getCanonicalName() +
                    " found by given conditions: " + conditions);
        }
        return list.get(0);
    }
   

}