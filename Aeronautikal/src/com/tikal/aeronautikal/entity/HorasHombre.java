package com.tikal.aeronautikal.entity;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class HorasHombre {

	@Id Long id;
	private Long idOrden;
	private Long idDiscrepancia;
	private String accion;
	@Index private Long idEmpleado;
	private String Empleado;
	@Index private String estatus;
	private Date horaIncio;
	//private Date horaFin;
	private Date inicioParcial;
	//private Date finParcial;
	private long tiempoTotal;
	private long tiempoParcial;
	private String ParcialEnHoras;
	private String tiempoHoras;
	
	
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
	
	public String getParcialEnHoras() {
		return ParcialEnHoras;
	}
	public void setParcialEnHoras(String parcialEnHoras) {
		ParcialEnHoras = parcialEnHoras;
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
	
	public String getTiempoHoras() {
		return tiempoHoras;
	}
	public void setTiempoHoras(String tiempoHoras) {
		this.tiempoHoras = tiempoHoras;
	}
	public long getTiempoTotal() {
		return tiempoTotal;
	}
	public void setTiempoTotal(long tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	public long getTiempoParcial() {
		return tiempoParcial;
	}
	public void setTiempoParcial(long tiempoParcial) {
		this.tiempoParcial = tiempoParcial;
	}
	public Date getInicioParcial() {
		return inicioParcial;
	}
	public void setInicioParcial(Date inicioParcial) {
		this.inicioParcial = inicioParcial;
	}
	
	
	
}
