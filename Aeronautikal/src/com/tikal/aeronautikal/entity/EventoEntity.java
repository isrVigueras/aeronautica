package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

//@Entity
public class EventoEntity {

	//@Id private String idEvento;
	private String idEvento;
	private String nombreEvento;
	private Integer duracion;
	private Integer costo;
//	private String idDiscrepancia;
	
	
	
//	
//	public String getIdDiscrepancia() {
//		return idDiscrepancia;
//	}
//	public void setIdDiscrepancia(String idDiscrepancia) {
//		this.idDiscrepancia = idDiscrepancia;
//	}
//	public String getIdEvento() {
//		return idEvento;
//	}
//	public void setIdEvento(String idEvento) {
//		this.idEvento = idEvento;
//	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public String getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public Integer getCosto() {
		return costo;
	}
	public void setCosto(Integer costo) {
		this.costo = costo;
	}
	
	
}