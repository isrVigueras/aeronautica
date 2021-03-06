package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.googlecode.objectify.cmd.Query;
import com.tikal.aeronautikal.controller.vo.OrdenVo;
import com.tikal.aeronautikal.dao.AeronaveDao;
import com.tikal.aeronautikal.dao.OrdenDao;
import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.entity.RequisicionEntity;
import com.tikal.aeronautikal.entity.ValeEntity;
import com.tikal.aeronautikal.exception.ObjectNotFoundException;





@Service("ordenDao")

public class OrdenDaoImpl implements OrdenDao{
	
	@Autowired
	@Qualifier("aeronaveDao")
	private AeronaveDao aeronaveDao;
	
	public void save(OrdenVo o) {
		System.out.println("si entra a OrdenDaoImpl con este objeto : "+o);
        ofy().save().entity(o).now();
        System.out.println("aqui ya guardo el objeto con objectify en  OrdenDaoImpl");
    }
	

	
	
	public List<OrdenVo> getAll() {
		return ofy().load().type(OrdenVo.class).list();
	}

	public List<OrdenVo> getAllN() {
		
		List<OrdenVo> all= ofy().load().type(OrdenVo.class).list();
		List<OrdenVo> ns= new ArrayList<OrdenVo>();
		for(OrdenVo o : all) {
            aeronaveDao.consult(o.getAeronave());
            if (aeronaveDao.consult(o.getAeronave()).equals("NORTEAMERICANA")){
            	ns.add(o);
            }
        }
		return ns;
	}
	
	public List<OrdenVo> getAllNac() {
		
		List<OrdenVo> all= ofy().load().type(OrdenVo.class).list();
		List<OrdenVo> nacs= new ArrayList<OrdenVo>();
		for(OrdenVo o : all) {
            aeronaveDao.consult(o.getAeronave());
            if (aeronaveDao.consult(o.getAeronave()).equals("NACIONAL")){
            	nacs.add(o);
            }
        }
		return nacs;
	}

	public void findAll() {
	// TODO Auto-generated method stub
	
	}

	@Override
	public void delete(OrdenVo o) {
		// TODO Auto-generated method stub@Override
		  ofy().delete().entity(o).now();
			//o.setActivo(false);
			//update(o);
	
		
	}


	@Override
	public void update(OrdenVo o) {
		// TODO Auto-generated method stub System.out.print("consultando vale:"+v.getId());
		   OrdenVo old =consult(o.getId());
		System.out.print("old:"+old);
			if (old != null) {
				old.setAccionGeneral(o.getAccionGeneral());
				old.setAeronave(o.getAeronave());
				old.setEmpresa(o.getEmpresa());
				
			}
				ofy().save().entity(old);
	

		
		}


	@Override
	public OrdenVo consult(Long id) {
		System.out.println("aqui esta consultando la OT" );
       return ofy().load().type(OrdenVo.class).id(id).now();
		
	}




		
}
