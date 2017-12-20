package com.tikal.aeronautikal.controller.vo;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.model.Empresa;



@Entity
public class OrdenVo {
	@Id Long id;
    String folio;
	String fechaApertura;	
	Long empresa;          
	String nombreEmpresa;
	String aeronave;
	Long idAeronave;
	
	//@Ignore private Empresa laEmpresa;
	
	//@Index @Load Ref<Empresa> refEmpresa;
	
	
	
	public Long getIdAeronave() {
		return idAeronave;
	}
	public void setIdAeronave(Long idAeronave) {
		this.idAeronave = idAeronave;
	}
	public String getFolio() {
		return folio;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public Long getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}
	public String getAeronave() {
		return aeronave;
	}
	public void setAeronave(String aeronave) {
		this.aeronave = aeronave;
	}
		
	
	
	
	
	
	
	
	
	
}
