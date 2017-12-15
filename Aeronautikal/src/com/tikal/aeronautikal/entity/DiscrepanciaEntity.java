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

public class DiscrepanciaEntity implements BaseEntity{

    
    @Id private Long folio;
    @Index private Long folioOrden;
    @Index private String taller;
	@Index private String seccion;
	@Index private String descripcion;
	@Index private String accion;	
	private String fechaApertura;
	private Long folio_componente;
	private Integer numero_piezas;
	//private List<Componente> losComponentes;
	private Empleado removidoPor;
	private Empleado instaladoPor;
	private Empleado originadoPor;
	
	@Load
	private List<Ref<EventoEntity>> eventos;
	
//	@Container OrdenEntity orden;
	
	
	public DiscrepanciaEntity() {
		this.eventos = new ArrayList<Ref<EventoEntity>>();
	}
	
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
//	public List<Componente> getLosComponentes() {
//		return losComponentes;
//	}
//	public void setLosComponentes(List<Componente> losComponentes) {
//		this.losComponentes = losComponentes;
//	}
	
	public Empleado getRemovidoPor() {
		return removidoPor;
	}
	
	public Long getFolio_componente() {
		return folio_componente;
	}
	public void setFolio_componente(Long folio_componente) {
		this.folio_componente = folio_componente;
	}
	public Integer getNumero_piezas() {
		return numero_piezas;
	}
	public void setNumero_piezas(Integer numero_piezas) {
		this.numero_piezas = numero_piezas;
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

	public List<Ref<EventoEntity>> getEventos() {
		return eventos;
	}

	public void setEventos(List<Ref<EventoEntity>> eventos) {
		this.eventos = eventos;
	}
	
    
	
    
}
