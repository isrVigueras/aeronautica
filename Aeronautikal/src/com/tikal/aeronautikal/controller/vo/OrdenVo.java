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
	
	@Id String folio;
	String fechaApertura;	
	Long empresa;          
	Long averonave;
	
	//@Ignore private Empresa laEmpresa;
	
	//@Index @Load Ref<Empresa> refEmpresa;
	
	public String getFolio() {
		return folio;
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
	public Long getAveronave() {
		return averonave;
	}
	public void setAveronave(Long averonave) {
		this.averonave = averonave;
	}		
	
	
	
	
	
	
	
	
	
}
