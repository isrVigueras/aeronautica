package com.tikal.aeronautikal.dao.impl;

	
	import com.tikal.aeronautikal.dao.EmpresaDao;
	import com.tikal.aeronautikal.entity.EmpresaEntity;
    import org.springframework.stereotype.Service;
	import java.util.List;	
	import static com.googlecode.objectify.ObjectifyService.ofy;

	/**
	 * @author 
	 */

	@Service("empresaDao")

	public class EmpresaDaoImpl implements EmpresaDao {

	    public void save(EmpresaEntity e) {
	    	
	        ofy().save().entity(e).now();
	    }

		public List<EmpresaEntity> getAll() {
			return ofy().load().type(EmpresaEntity.class).list();
		}


		public List<EmpresaEntity> findAll() {
		// TODO Auto-generated method stub
			return ofy().load().type(EmpresaEntity.class).list();
		
		}

		@Override
		public void delete(EmpresaEntity e) {
			 System.out.println("si esta en daoimpl eliminando"+e);
		        ofy().delete().entity(e).now();
		        System.out.println("eliminando...");
				//object.setActivo(false);
				//update(e);
			
			
		}


		@Override
		public void update(EmpresaEntity e) {
			// TODO Auto-generated method stub
				ofy().save().entity(e).now();
			
		}


		@Override
		public EmpresaEntity consult(Long idEmpresa) {
			
	       return ofy().load().type(EmpresaEntity.class).id(idEmpresa).now();
			
		}
	    
	    
		@Override
		public List<EmpresaEntity> getAllEmpresas() {
			return ofy().load().type(EmpresaEntity.class).list();
		}

}
