package com.tikal.aeronautikal.entity;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.tikal.aeronautikal.controller.vo.ComDisVo;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;

@Entity
public class ValeEntity {
	
	@Id private Long id;
	private String fecha;
	@Index private Long idDiscrepancia;
	private List<ComponenteDiscrepancia> items;
	@Index private String estatus;
	private ComponenteEntity componente ;  /// solo aplica para emitir vale automatico cuando se despacha requisicion
								/// solo es un componente
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Long getIdDiscrepancia() {
		return idDiscrepancia;
	}
	public void setIdDiscrepancia(Long idDiscrepancia) {
		this.idDiscrepancia = idDiscrepancia;
	}
	public List<ComponenteDiscrepancia> getItems() {
		if(this.items==null) {
			this.items= new ArrayList<ComponenteDiscrepancia>();
		}
		return items;
	}
	public void setItems(List<ComponenteDiscrepancia> items) {
		this.items = items;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public ComponenteEntity getComponente() {
		return componente;
	}
	public void setComponente(ComponenteEntity componente) {
		this.componente = componente;
	}
	
	
	
	
	
}
