package com.tikal.aeronautikal.entity;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class HorasHombre {

	@Id Long id;
	private Long idOrden;
	private Long idDiscrepancia;
	private String accion;
	private Long idEmpleado;
	private String Empleado;
	private String estatus;
	private Date horaIncio;
	private Date horaFin;
	private Date finParcial;
	private double tiempoTotal;
	private double tiempoParcial;
	
	
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
	
	public String getEmpleado() {
		return Empleado;
	}
	public void setEmpleado(String empleado) {
		Empleado = empleado;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Date getHoraIncio() {
		return horaIncio;
	}
	public void setHoraIncio(Date horaIncio) {
		this.horaIncio = horaIncio;
	}
	public Date getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	public Date getFinParcial() {
		return finParcial;
	}
	public void setFinParcial(Date finParcial) {
		this.finParcial = finParcial;
	}
	public double getTiempoTotal() {
		return tiempoTotal;
	}
	public void setTiempoTotal(double tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	public double getTiempoParcial() {
		return tiempoParcial;
	}
	public void setTiempoParcial(double tiempoParcial) {
		this.tiempoParcial = tiempoParcial;
	}
	
	
	
}
