package com.tikal.aeronautikal.controller.vo;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.tikal.aeronautikal.entity.EventoEntity;
import com.tikal.aeronautikal.entity.otBody.ComponenteEntity;


@Entity
public class DetalleDiscrepanciaVo {
	@Id private Long id;
	private Long idOrden;
	private String folioOrden;
	private String fechaOrden;
	private String nombreEmpresa;
	private String matricula;
	private String modelo;
	private String noSerie;
	private String taller;
	private String seccion;
	private String Descripcion;
	private String Accion;
	private List<ComponenteEntity> componentes;
	private List<EventoEntity> eventos;
	private Long telefono;
	
	
	
	public Long getTelefono() {
		return telefono;
	}
	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}
	public Long getIdOrden() {
		return idOrden;
	}
	public void setIdOrden(Long idOrden) {
		this.idOrden = idOrden;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFolioOrden() {
		return folioOrden;
	}
	public void setFolioOrden(String folioOrden) {
		this.folioOrden = folioOrden;
	}
	public String getFechaOrden() {
		return fechaOrden;
	}
	public void setFechaOrden(String fechaOrden) {
		this.fechaOrden = fechaOrden;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNoSerie() {
		return noSerie;
	}
	public void setNoSerie(String noSerie) {
		this.noSerie = noSerie;
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
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getAccion() {
		return Accion;
	}
	public void setAccion(String accion) {
		Accion = accion;
	}
	public List<ComponenteEntity> getComponentes() {
		return componentes;
	}
	public void setComponentes(List<ComponenteEntity> componentes) {
		this.componentes = componentes;
	}
	public List<EventoEntity> getEventos() {
		return eventos;
	}
	public void setEventos(List<EventoEntity> eventos) {
		this.eventos = eventos;
	}
	

	
	
}
