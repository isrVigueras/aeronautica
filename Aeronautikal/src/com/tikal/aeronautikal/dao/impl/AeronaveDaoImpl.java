package com.tikal.aeronautikal.dao.impl;



import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * @author 
 */

@Service("aeronaveDao")

public class AeronaveDaoImpl implements AeronaveDao {

    public  void save(AeronaveEntity a) {    	
        ofy().save().entity(a).now();
    }

    
    public void delete(AeronaveEntity a) {
    	 System.out.println("si esta en daoimpl eliminando"+a);
        ofy().delete().entity(a).now();
        System.out.println("eliminando...");
    }

   @Override
	public void update(AeronaveEntity nave) {
	   System.out.print("nave:"+nave.getId());
	AeronaveEntity old = this.consult(nave.getId());
	System.out.print("old:"+old);
		if (old != null) {
			old.setMarca(nave.getMarca());
			old.setMatricula(nave.getMatricula());
			old.setModelo(nave.getModelo());
			old.setNacionalidad(nave.getNacionalidad());
			old.setNumeroSerie(nave.getNumeroSerie());
			old.setTiempovuelo(nave.getTiempovuelo());
			old.setAterrizaje(nave.getAterrizaje());
			old.setPlaneador(nave.getPlaneador());
			old.setMotor1(nave.getMotor1());
			old.setMotor2(nave.getMotor2());
			old.setMarcas(nave.getMarcas());
			
		}

			ofy().save().entity(old);
   }

    
   @Override
	public AeronaveEntity consult(Long id) {
	   System.out.println("si esta en daoimpl consultando la nave.."+id);
      return ofy().load().type(AeronaveEntity.class).id(id).now();
		
	}

	@Override
	public void findAll(AeronaveEntity a) {
		// TODO Auto-generated method stub
		
	}
   
	public List<AeronaveEntity> getAll() {
		return ofy().load().type(AeronaveEntity.class).list();
	}


	@Override
	public List<AeronaveEntity> getAllN() {
		// TODO Auto-generated method stub
		return null;
	}
}