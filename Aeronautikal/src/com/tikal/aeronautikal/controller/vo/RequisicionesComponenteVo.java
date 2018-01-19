package com.tikal.aeronautikal.controller.vo;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.tikal.aeronautikal.entity.RequisicionEntity;

@Entity
public class RequisicionesComponenteVo {
	
	@Id private Long idComponente;
	private String desComponente;
	private String noParte;
	private String descripcion;
	
	
	private List<RequisicionEntity> requisiciones;
	
	
	

	public Long getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(Long idComponente) {
		this.idComponente = idComponente;
	}

	public String getDesComponente() {
		return desComponente;
	}

	public void setDesComponente(String desComponente) {
		this.desComponente = desComponente;
	}

	public List<RequisicionEntity> getRequisiciones() {
		return requisiciones;
	}

	public void setRequisiciones(List<RequisicionEntity> requisiciones) {
		this.requisiciones = requisiciones;
	}

	public String getNoParte() {
		return noParte;
	}

	public void setNoParte(String noParte) {
		this.noParte = noParte;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
