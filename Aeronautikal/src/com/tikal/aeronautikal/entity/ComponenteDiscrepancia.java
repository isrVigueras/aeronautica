package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class ComponenteDiscrepancia {
	@Id private Long id;
	@Index private Long idComponente;
	private String nombreComponente;
	@Index private Long idDiscrepancia;
	private Integer cantidad;
	private Integer cantOriginal;
	private Long idVale;
	@Index String auto;
	
	
	
	
	public String getNombreComponente() {
		return nombreComponente;
	}
	public void setNombreComponente(String nombreComponente) {
		this.nombreComponente = nombreComponente;
	}
	public Long getIdVale() {
		return idVale;
	}
	public void setIdVale(Long idVale) {
		this.idVale = idVale;
	}
	public Integer getCantOriginal() {
		return cantOriginal;
	}
	public void setCantOriginal(Integer cantOriginal) {
		this.cantOriginal = cantOriginal;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(Long idComponente) {
		this.idComponente = idComponente;
	}
	public Long getIdDiscrepancia() {
		return idDiscrepancia;
	}
	public void setIdDiscrepancia(Long idDiscrepancia) {
		this.idDiscrepancia = idDiscrepancia;
	}
	public String getAuto() {
		return auto;
	}
	public void setAuto(String auto) {
		this.auto = auto;
	}
	
	

}
