package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.googlecode.objectify.cmd.Query;
import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.OrdenDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.exception.ObjectNotFoundException;




@Service("ordenDao")

public class OrdenDaoImpl implements OrdenDao{
	
	public void save(OrdenVo o) {
		System.out.println("si entra a OrdenDaoImpl con este objeto : "+o);
        ofy().save().entity(o).now();
        System.out.println("aqui ya guardo el objeto con objectify en  OrdenDaoImpl");
    }
	

	
	
	public List<OrdenVo> getAll() {
		return ofy().load().type(OrdenVo.class).list();
	}


	public void findAll() {
	// TODO Auto-generated method stub
	
	}

	@Override
	public void delete(OrdenVo o) {
		// TODO Auto-generated method stub@Override
		
			//o.setActivo(false);
			//update(o);
		
		
	}


	@Override
	public void update(OrdenVo o) {
		// TODO Auto-generated method stub
		OrdenVo old = this.consult(o.getFolio());
		if (old != null) {
//			//old.setDireccion(e.getDireccion());
			old.setA_matricula(o.getA_matricula());
			old.setA_t_aterrizaje(o.getA_t_aterrizaje());
			old.setA_t_vuelo(o.getA_t_vuelo());
			old.setCon_correo(o.getCon_correo());
			old.setCon_nombre(o.getCon_correo());
			old.setCon_telefono(o.getCon_telefono());
			old.setEmpresa(o.getEmpresa());
			old.setFechaApertura(o.getFechaApertura());
			old.setModelo(o.getModelo());
			old.setN_serie(o.getN_serie());
			ofy().save().entity(old);
		
		}

		
	}


	@Override
	public OrdenVo consult(Long folio) {
		
       return ofy().load().type(OrdenVo.class).id(folio).now();
		
	}




		
}
