package com.tikal.aeronautikal.controller.vo;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
@Entity
public class ValePdfVo {
	
	@Id Long id;
	private String nombreArchivo;
	private String fechaVale;
	private String matricula;
	private String noSerie;
	private String noOrden;
	private Long noDiscrepancia;
	private List<ItemVo> items;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getFechaVale() {
		return fechaVale;
	}
	public void setFechaVale(String fechaVale) {
		this.fechaVale = fechaVale;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNoSerie() {
		return noSerie;
	}
	public void setNoSerie(String noSerie) {
		this.noSerie = noSerie;
	}
	public String getNoOrden() {
		return noOrden;
	}
	public void setNoOrden(String noOrden) {
		this.noOrden = noOrden;
	}
	public Long getNoDiscrepancia() {
		return noDiscrepancia;
	}
	public void setNoDiscrepancia(Long noDiscrepancia) {
		this.noDiscrepancia = noDiscrepancia;
	}
	public List<ItemVo> getItems() {
		
		return items;
	}
	public void setItems(List<ItemVo> items) {
		this.items = items;
	}
	
}
