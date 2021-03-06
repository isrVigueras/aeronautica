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
	Long aeronave;
	@Index String estatus;
	String accionGeneral;
		
	//@Ignore private Empresa laEmpresa;
	
	//@Index @Load Ref<Empresa> refEmpresa;
	
	
	
	
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
	public Long getAeronave() {
		return aeronave;
	}
	public void setAeronave(Long aeronave) {
		this.aeronave = aeronave;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getAccionGeneral() {
		return accionGeneral;
	}
	public void setAccionGeneral(String accionGeneral) {
		this.accionGeneral = accionGeneral;
	}
		
	
	
	
	
	
	
	
	
	
}
