package com.tikal.aeronautikal.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.tikal.aeronautikal.model.Empleado;
import com.tikal.aeronautikal.model.otBody.Componente;
import com.tikal.aeronautikal.model.otBody.Seccion;
import com.tikal.aeronautikal.model.otBody.Taller;


 
@Entity

public class DiscrepanciaEntity {

    
    @Id Long id;
    private String folio;
    @Index private Long folioOrden;
    @Index private String taller;
	@Index private String seccion;
	@Index private String descripcion;
	@Index private String accion;	
	private String fechaApertura;
	@Index private String estatus;

	
	
	

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	
	public String getFolio() {
		return folio;
	}



	public void setFolio(String folio) {
		this.folio = folio;
	}



	public Long getFolioOrden() {
		return folioOrden;
	}
	public void setFolioOrden(Long folioOrden) {
		this.folioOrden = folioOrden;
	}
	
	public String getTaller() {
		return taller;
	}
	public void setTaller(String taller) {
		this.taller = taller;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}



	public String getEstatus() {
		return estatus;
	}



	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

//public List<EventoEntity> getEventos() {
//	if(this.eventos==null) {
//		this.eventos= new ArrayList<EventoEntity>();
//	}
//		return eventos;
//	}
	
	
    
	
    
}
