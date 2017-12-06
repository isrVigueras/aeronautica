package com.tikal.aeronautikal.entity;

import java.util.Calendar;
import java.util.List;

import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.tikal.aeronautikal.model.Empleado;
import com.tikal.aeronautikal.model.otBody.Componente;
import com.tikal.aeronautikal.model.otBody.Seccion;
import com.tikal.aeronautikal.model.otBody.Taller;
 
@Entity

public class DiscrepanciaEntity implements BaseEntity{

    
    @Id private Long folio;
    @Index private Long folioOrden;
    @Index private Taller taller;
	@Index private Seccion seccion;
	@Index private String descripcion;
	@Index private String accion;	
	private Calendar fechaApertura;
	private List<Componente> losComponentes;
	private Empleado removidoPor;
	private Empleado instaladoPor;
	private Empleado originadoPor;
//	@Container OrdenEntity orden;
	
	public Long getFolio() {
		return folio;
	}
	public void setFolio(Long folio) {
		this.folio = folio;
	}
	
	public Long getFolioOrden() {
		return folioOrden;
	}
	public void setFolioOrden(Long folioOrden) {
		this.folioOrden = folioOrden;
	}
	public Taller getTaller() {
		return taller;
	}
	public void setTaller(Taller taller) {
		this.taller = taller;
	}
	public Seccion getSeccion() {
		return seccion;
	}
	public void setSeccion(Seccion seccion) {
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
	public Calendar getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(Calendar fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public List<Componente> getLosComponentes() {
		return losComponentes;
	}
	public void setLosComponentes(List<Componente> losComponentes) {
		this.losComponentes = losComponentes;
	}
	public Empleado getRemovidoPor() {
		return removidoPor;
	}
	public void setRemovidoPor(Empleado removidoPor) {
		this.removidoPor = removidoPor;
	}
	public Empleado getInstaladoPor() {
		return instaladoPor;
	}
	public void setInstaladoPor(Empleado instaladoPor) {
		this.instaladoPor = instaladoPor;
	}
	public Empleado getOriginadoPor() {
		return originadoPor;
	}
	public void setOriginadoPor(Empleado originadoPor) {
		this.originadoPor = originadoPor;
	}
    
	
    
}
