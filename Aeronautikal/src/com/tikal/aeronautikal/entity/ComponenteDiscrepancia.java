package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class ComponenteDiscrepancia {
	@Id private Long id;
	@Index private Long idComponente;
	@Index private Long idDiscrepancia;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(Long idComponente) {
		this.idComponente = idComponente;
	}
	public Long getIdDiscrepancia() {
		return idDiscrepancia;
	}
	public void setIdDiscrepancia(Long idDiscrepancia) {
		this.idDiscrepancia = idDiscrepancia;
	}
	

}
