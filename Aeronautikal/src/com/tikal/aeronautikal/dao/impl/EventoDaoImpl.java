package com.tikal.aeronautikal.dao.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.EventoDao;

import com.tikal.aeronautikal.entity.EventoEntity;




@Service("eventoDao")
public class EventoDaoImpl implements EventoDao {

	
		
		public void save(EventoEntity c) {   
			System.out.println("si lega aqui");
	        ofy().save().entity(c).now();
	    }
		public List<EventoEntity> getAll() {
			return ofy().load().type(EventoEntity.class).list();
		}


		public void findAll() {
		// TODO Auto-generated method stub
		
		}

		@Override
		public void delete(EventoEntity c) {
			// TODO Auto-generated method stub@Override
			ofy().delete().entity(c).now();
				//object.setActivo(false);
				//update(c);
			
			
		}


		@Override
		public void update(EventoEntity c) {
			// TODO Auto-generated method stub
			System.out.println(" evento nuevo"+c.getIdEvento() );
			EventoEntity old = this.consult(c.getIdEvento());
			System.out.println(" evento"+old );
			if (old != null) {
				old.setCosto(c.getCosto());
				old.setDuracion(c.getDuracion());
				old.setNombreEvento(c.getNombreEvento());
				ofy().save().entity(old);
			}
				System.out.println("guarda los nuevos datos del evento" );
		}

			
		
		@Override
		public EventoEntity consult(Long id) {
			System.out.println("consultando el evento " );
	       return ofy().load().type(EventoEntity.class).id(id).now();
			
		}
		
		
		@Override
		public List<EventoEntity> getByDiscrepancia(Long idDiscrepancia) {
			// TODO Auto-generated method stub
			List<EventoEntity> evs = ofy().load().type(EventoEntity.class).filter("idDiscrepancia", idDiscrepancia).list();
			return evs;
		}

	}

