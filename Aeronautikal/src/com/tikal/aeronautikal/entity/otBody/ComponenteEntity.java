package com.tikal.aeronautikal.entity.otBody;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.model.otBody.ComponenteStatus;

@Entity

public class ComponenteEntity{
	
	@Id private Long id;
	private String d_componente;
	private String d_descripcion;
	private String d_parte;
	private Integer d_cantidad;
	private Integer d_pendientes;
	private String d_requisicion;
	private String fechaApertura;
	private String d_vale;
	
	
	
	
	public String getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getD_componente() {
		return d_componente;
	}
	public void setD_componente(String d_componente) {
		this.d_componente = d_componente;
	}
	
	public String getD_descripcion() {
		return d_descripcion;
	}
	public void setD_descripcion(String d_descripcion) {
		this.d_descripcion = d_descripcion;
	}
	public String getD_parte() {
		return d_parte;
	}
	public void setD_parte(String d_parte) {
		this.d_parte = d_parte;
	}
	public Integer getD_cantidad() {
		return d_cantidad;
	}
	public void setD_cantidad(Integer d_cantidad) {
		this.d_cantidad = d_cantidad;
	}
	public Integer getD_pendientes() {
		return d_pendientes;
	}
	public void setD_pendientes(Integer d_pendientes) {
		this.d_pendientes = d_pendientes;
	}
	public String getD_requisicion() {
		return d_requisicion;
	}
	public void setD_requisicion(String d_requisicion) {
		this.d_requisicion = d_requisicion;
	}
	public String getD_vale() {
		return d_vale;
	}
	public void setD_vale(String d_vale) {
		this.d_vale = d_vale;
	}
	
	
	
	
}
