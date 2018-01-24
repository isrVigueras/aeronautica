package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class HorasHombre {

	@Id Long id;
	private Long idOrden;
	private Long idDiscrepancia;
	private String accion;
	private Long idEmpleado;
	private String estatus;
	private String horaIncio;
	private String horaFin;
	private String finParcial;
	private String tiempoTotal;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdOrden() {
		return idOrden;
	}
	public void setIdOrden(Long idOrden) {
		this.idOrden = idOrden;
	}
	public Long getIdDiscrepancia() {
		return idDiscrepancia;
	}
	public void setIdDiscrepancia(Long idDiscrepancia) {
		this.idDiscrepancia = idDiscrepancia;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getHoraIncio() {
		return horaIncio;
	}
	public void setHoraIncio(String horaIncio) {
		this.horaIncio = horaIncio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getFinParcial() {
		return finParcial;
	}
	public void setFinParcial(String finParcial) {
		this.finParcial = finParcial;
	}
	public String getTiempoTotal() {
		return tiempoTotal;
	}
	public void setTiempoTotal(String tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	
	
	
}
