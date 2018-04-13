package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.ValeDao;

import com.tikal.aeronautikal.entity.ValeEntity;

@Service("valeDao")
public class ValeDaoImpl implements ValeDao{
		
	@Override
	  public  void save(ValeEntity v) {    
		System.out.println("si esta en daoimpl salvando vale"+v);
	        ofy().save().entity(v).now();
	    }

	    
	    public void delete(ValeEntity v) {
	    	 System.out.println("si esta en daoimpl vale eliminando"+v.getId());
	        ofy().delete().entity(v).now();
	        System.out.println(" vale eliminando...");
	    }

	   @Override
		public void update(ValeEntity v) {
		   System.out.print("consultando vale:"+v.getId());
		   ValeEntity old =consult(v.getId());
		   System.out.print("regresa");
		System.out.print("old:"+old);
			if (old != null) {
				old.setFecha(v.getFecha());
				old.setEstatus(v.getEstatus());
				old.setItems(v.getItems());				
			}

				ofy().save().entity(old);
	   }

	    
	   @Override
		public ValeEntity consult(Long id) {
		   System.out.println("si esta en daoimpl consultando el vale.."+id);
	      return ofy().load().type(ValeEntity.class).id(id).now();
			
		}

		
	   
		public List<ValeEntity> findAll() {
			return ofy().load().type(ValeEntity.class).list();
		}

		
	/*	@Override
		public List<ValeEntity> getByUser(Long idUser) {
			// TODO Auto-generated method stub
			List<ValeEntity> vs = ofy().load().type(ValeEntity.class).filter("idUser", idUser).list();
			return vs;
		}*/
		
		
		@Override
		public List<ValeEntity> getByDiscrepancia(Long idDiscrepancia) {
			// TODO Auto-generated method stub
			List<ValeEntity> vs = ofy().load().type(ValeEntity.class).filter("idDiscrepancia", idDiscrepancia).list();
			return vs;
		}
}
